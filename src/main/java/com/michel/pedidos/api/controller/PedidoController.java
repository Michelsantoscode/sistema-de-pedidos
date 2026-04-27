package com.michel.pedidos.api.controller;

import com.michel.pedidos.api.model.PedidoModel;
import com.michel.pedidos.api.model.input.PedidoInput;
import com.michel.pedidos.assembler.PedidoAssembler;
import com.michel.pedidos.domain.model.Pedido;
import com.michel.pedidos.domain.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoAssembler pedidoAssembler;
    private final PedidoService pedidoService;


    @Operation(summary = "Adicionar pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Recurso nao encontrado")
    })
    @PostMapping
    public ResponseEntity<PedidoModel> adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        Pedido salvo = pedidoService.adicionarPedidos(pedidoInput);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoAssembler.toModel(salvo));
    }

    @Operation(summary = "Excluir pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "404", description = "Recurso nao encontrado")
    })
    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<Void> excluir(@PathVariable Long pedidoId) {
        pedidoService.excluirPedidos(pedidoId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar pedidos")
    @ApiResponses (value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<PedidoModel> listar () {
        return pedidoService.listarPedidos()
                .stream()
                .map(pedidoAssembler::toModel)
                .toList();
    }

    @Operation(summary = "Buscar pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisicao invalida"),
            @ApiResponse(responseCode = "404", description = "Recurso nao encontrado")
    })
    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoModel> buscar (@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.buscarPedidos(pedidoId);
        return ResponseEntity.ok(pedidoAssembler.toModel(pedido));
    }

    @Operation(summary = "Pagar pedido")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @PostMapping("/{pedidoId}/pagar")
    public ResponseEntity <PedidoModel> pagar (@PathVariable Long pedidoId) {
       Pedido pedido = pedidoService.pagar(pedidoId);
       return ResponseEntity.ok(pedidoAssembler.toModel(pedido));
    }

    @Operation(summary = "Enviar pedido")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @PostMapping("/{pedidoId}/enviar")
    public ResponseEntity<PedidoModel>  enviar (@PathVariable Long pedidoId) {
        Pedido pedido = pedidoService.enviar(pedidoId);
        return ResponseEntity.ok(pedidoAssembler.toModel(pedido));
    }

    @Operation(summary = "Entregar pedido")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @PostMapping("/{pedidoId}/entregar")
    public ResponseEntity<PedidoModel> entregar (@PathVariable Long pedidoId) {
       Pedido pedido = pedidoService.entregar(pedidoId);
       return ResponseEntity.ok(pedidoAssembler.toModel(pedido));
    }

}
