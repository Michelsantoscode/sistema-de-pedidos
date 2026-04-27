package com.michel.pedidos.domain.service;

import com.michel.pedidos.api.model.input.PedidoInput;
import com.michel.pedidos.domain.exception.EntidadeNaoEncontrada;
import com.michel.pedidos.domain.exception.NegocioException;
import com.michel.pedidos.domain.model.Pedido;
import com.michel.pedidos.domain.model.Produto;
import com.michel.pedidos.domain.model.StatusPedido;
import com.michel.pedidos.domain.model.Usuario;
import com.michel.pedidos.domain.repository.PedidoRepository;
import com.michel.pedidos.domain.repository.ProdutoRepository;
import com.michel.pedidos.domain.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;

    public List<Pedido> listarPedidos () {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPedidos (Long pedidosId) {
        return pedidoRepository.findById(pedidosId)
                .orElseThrow(()-> new EntidadeNaoEncontrada("Pedido nao encontrado"));
    }

    @Transactional
    public void excluirPedidos (Long pedidoId) {
        pedidoRepository.deleteById(pedidoId);
    }

    @Transactional
    public Pedido adicionarPedidos(PedidoInput input) {

        Produto produto = produtoRepository.findById(input.getProdutoId())
                .orElseThrow(() -> new EntidadeNaoEncontrada("Produto não encontrado"));

        Usuario usuario = usuarioRepository.findById(input.getUsuarioId())
                .orElseThrow(() -> new EntidadeNaoEncontrada("Usuário não encontrado"));

        Pedido pedido = new Pedido();

        pedido.setProduto(produto);
        pedido.setUsuario(usuario);
        pedido.setStatus(StatusPedido.CRIADO);

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido pagar (Long pedidoId) {
        Pedido pedido = buscarPedidos(pedidoId);

        if (pedido.getStatus() != StatusPedido.CRIADO) {
            throw new NegocioException("Pedido nao pode ser pago");
        }

        pedido.setStatus(StatusPedido.PAGO);
        pedidoRepository.save(pedido);
        return pedido;
    }

    @Transactional
    public Pedido enviar (Long pedidoId) {
        Pedido pedido = buscarPedidos(pedidoId);

        if (pedido.getStatus() != StatusPedido.PAGO) {
            throw new NegocioException("Pedido nao pode ser enviado");
        }

        pedido.setStatus(StatusPedido.ENVIADO);
        pedidoRepository.save(pedido);
        return pedido;
    }

    @Transactional
    public Pedido entregar (Long pedidoId) {
        Pedido pedido = buscarPedidos(pedidoId);

        if (pedido.getStatus() != StatusPedido.ENVIADO) {
            throw new NegocioException("Pedido nao pode ser entregue");
        }

        pedido.setStatus(StatusPedido.ENTREGUE);
        return pedidoRepository.save(pedido);

    }


}
