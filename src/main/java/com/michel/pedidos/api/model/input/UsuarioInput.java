package com.michel.pedidos.api.model.input;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {

    private String nome;
    private String cpf;

    @Email
    private String email;


}
