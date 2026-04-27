package com.michel.pedidos.commom;

import com.michel.pedidos.api.model.UsuarioModel;
import com.michel.pedidos.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(Usuario.class, UsuarioModel.class);
        modelMapper.createTypeMap(UsuarioModel.class, Usuario.class);

        return modelMapper;
    }

}
