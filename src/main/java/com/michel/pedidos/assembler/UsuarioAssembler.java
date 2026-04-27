package com.michel.pedidos.assembler;

import com.michel.pedidos.api.model.UsuarioModel;
import com.michel.pedidos.api.model.input.UsuarioInput;
import com.michel.pedidos.domain.model.Usuario;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UsuarioAssembler {

    private final ModelMapper modelMapper;

    public UsuarioModel toModel (Usuario usuario) {
        return modelMapper.map(usuario, UsuarioModel.class);
    }

     public Usuario toEntity(UsuarioInput usuarioInput) {
            return modelMapper.map(usuarioInput, Usuario.class);
        }
    }
