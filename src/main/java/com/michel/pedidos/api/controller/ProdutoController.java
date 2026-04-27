package com.michel.pedidos.api.controller;

import com.michel.pedidos.api.model.PedidoModel;
import com.michel.pedidos.api.model.ProdutoModel;
import com.michel.pedidos.api.model.input.ProdutoInput;
import com.michel.pedidos.assembler.ProdutoAssembler;
import com.michel.pedidos.domain.model.Produto;
import com.michel.pedidos.domain.repository.ProdutoRepository;
import com.michel.pedidos.domain.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoAssembler produtoAssembler;
    private final ProdutoService produtoService;
    private final ModelMapper modelMapper;


    @Operation(summary = "Adicionar produto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Recurso nao encontrado")
    })
    @PostMapping
    public ResponseEntity<ProdutoModel> adicionar(@Valid @RequestBody ProdutoInput produtoInput) {
        Produto produto = modelMapper.map(produtoInput, Produto.class);
        Produto novoProduto = produtoService.adicionarProdutos(produto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoAssembler.toModel(novoProduto));
    }


    @Operation(summary = "Excluir produto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Recurso nao encontrado")
    })
    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> excluir(@PathVariable Long produtoId) {
        produtoService.excluirProdutos(produtoId);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Listar produtos")
    @ApiResponses (value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<ProdutoModel> listar () {
        return produtoService.listarProdutos()
                .stream()
                .map(produtoAssembler::toModel)
                .toList();
    }

    @Operation(summary = "Buscar produto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso nao encontrado")
    })
    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoModel> buscar (@PathVariable Long produtoId) {
        Produto produto = produtoService.buscarProdutos(produtoId);
        return ResponseEntity.ok(produtoAssembler.toModel(produto));
    }


}
