package br.com.techtaste.microsservice.controller;

import br.com.techtaste.microsservice.dto.PedidoRequestDto;
import br.com.techtaste.microsservice.dto.PedidoResponseDto;
import br.com.techtaste.microsservice.model.Pedido;
import br.com.techtaste.microsservice.service.PedidoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @PostMapping
    @CircuitBreaker(name = "verificaAutorizacao",fallbackMethod = "erroAoCadastrarPedido")
    public ResponseEntity<PedidoResponseDto> cadastrarPedido(@RequestBody PedidoRequestDto pedidoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrandoPedido(pedidoDto,false));
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> listarPedidos() {
        return ResponseEntity.ok(service.listarPedidos());
    }

    public ResponseEntity<PedidoResponseDto> erroAoCadastrarPedido(@RequestBody @Valid PedidoRequestDto pedidoDto, Exception e) {
        return  ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrandoPedido(pedidoDto, true));
    }
}
