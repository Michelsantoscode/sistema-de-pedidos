package com.michel.pedidos.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInput {

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long produtoId;


}
