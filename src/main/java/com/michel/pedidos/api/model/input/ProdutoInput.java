package com.michel.pedidos.api.model.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoInput {

    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotNull
    private BigDecimal preco;

    @NotNull
    private Long quantidade;


}
