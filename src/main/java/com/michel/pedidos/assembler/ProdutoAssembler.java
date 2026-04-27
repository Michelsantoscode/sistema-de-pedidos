package com.michel.pedidos.assembler;

import com.michel.pedidos.api.model.ProdutoModel;
import com.michel.pedidos.api.model.input.ProdutoInput;
import com.michel.pedidos.domain.model.Produto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProdutoAssembler {

    private final ModelMapper modelMapper;

    public Produto toEntity (ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public ProdutoModel toModel (Produto produto) {
        return modelMapper.map(produto, ProdutoModel.class);
    }

}
