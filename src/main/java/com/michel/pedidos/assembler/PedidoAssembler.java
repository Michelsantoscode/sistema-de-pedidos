package com.michel.pedidos.assembler;

import com.michel.pedidos.api.model.PedidoModel;
import com.michel.pedidos.api.model.input.PedidoInput;
import com.michel.pedidos.domain.model.Pedido;
import com.michel.pedidos.domain.model.StatusPedido;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PedidoAssembler {

    private final ModelMapper modelMapper;
    private final UsuarioAssembler usuarioAssembler;

    public PedidoModel toModel(Pedido pedido) {

        PedidoModel model = new PedidoModel();

        model.setId(pedido.getId());
        model.setProduto(pedido.getProduto().getNome());
        model.setStatus(StatusPedido.valueOf(pedido.getStatus().name()));

        if (pedido.getUsuario() != null) {
            model.setUsuario(usuarioAssembler.toModel(pedido.getUsuario()));
        }

        return model;
    }

    public Pedido toEntity(PedidoInput input) {
        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.CRIADO);
        return pedido;
    }
}
