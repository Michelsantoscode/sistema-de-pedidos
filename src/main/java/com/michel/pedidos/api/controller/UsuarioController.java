package com.michel.pedidos.api.controller;

import com.michel.pedidos.api.model.UsuarioModel;
import com.michel.pedidos.api.model.input.UsuarioInput;
import com.michel.pedidos.assembler.UsuarioAssembler;
import com.michel.pedidos.domain.model.Usuario;
import com.michel.pedidos.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioAssembler usuarioAssembler;
    private final UsuarioService usuarioService;



    @Operation(summary = "Adicionar usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Recurso nao encontrado")
    })
    @PostMapping
    public ResponseEntity<UsuarioModel> adicionar (@Valid @RequestBody UsuarioInput usuarioInput) {
        Usuario usuario = usuarioAssembler.toEntity(usuarioInput);
        Usuario salvo = usuarioService.adicionar(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioAssembler.toModel(salvo));
    }

    @Operation(summary = "Excluir Usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Recurso nao encontrado")
    })
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Void> excluir(@PathVariable Long usuarioId) {
        usuarioService.excluir(usuarioId);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Listar Usuarios")
    @ApiResponses (value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<UsuarioModel> listar () {
        return usuarioService.listar()
                .stream()
                .map(usuarioAssembler::toModel)
                .toList();
    }


    @Operation(summary = "Buscar Usuario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso nao encontrado")
    })
    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioModel> buscar (@PathVariable Long usuarioId) {
        Usuario usuario = usuarioService.buscar(usuarioId);
        return ResponseEntity.ok(usuarioAssembler.toModel(usuario));
    }


    @Operation(summary = "Atualizar Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso nao encontrado")
    })
    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioModel> atualizar(
            @PathVariable Long usuarioId,
            @RequestBody @Valid UsuarioInput usuarioInput) {

        Usuario usuario = usuarioAssembler.toEntity(usuarioInput);
        Usuario usuarioAtualizado = usuarioService.atualizar(usuarioId, usuario);

        return ResponseEntity.ok(usuarioAssembler.toModel(usuarioAtualizado));
    }

}
