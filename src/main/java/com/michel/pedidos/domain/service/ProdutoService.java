package com.michel.pedidos.domain.service;

import com.michel.pedidos.domain.exception.EntidadeNaoEncontrada;
import com.michel.pedidos.domain.exception.NegocioException;
import com.michel.pedidos.domain.model.Produto;
import com.michel.pedidos.domain.repository.PedidoRepository;
import com.michel.pedidos.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;

    public List<Produto> listarProdutos () {
       return produtoRepository.findAll();
    }

    public Produto buscarProdutos (Long produtoId) {
       return produtoRepository.findById(produtoId)
                .orElseThrow(() -> new EntidadeNaoEncontrada("Produto nao encontrado"));
    }

    @Transactional
    public void excluirProdutos (Long produtoId) {
        pedidoRepository.deleteByProdutoId(produtoId);
        produtoRepository.deleteById(produtoId);
    }

    public void excluir(Long produtoId) {
        if (!produtoRepository.existsById(produtoId)) {
            throw new EntidadeNaoEncontrada("Produto não encontrado");
        }
        produtoRepository.deleteById(produtoId);
    }

    @Transactional
    public Produto adicionarProdutos (Produto produtos) {
        return produtoRepository.save(produtos);
    }


}
