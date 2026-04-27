package com.michel.pedidos.api.model;

import com.michel.pedidos.domain.model.StatusPedido;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {


    private Long id;
    private String produto;
    private StatusPedido status;
    private UsuarioModel usuario;

}
