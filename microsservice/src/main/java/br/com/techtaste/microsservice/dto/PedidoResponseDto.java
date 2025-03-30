package br.com.techtaste.microsservice.dto;

import br.com.techtaste.microsservice.model.ItemPedido;
import br.com.techtaste.microsservice.model.Pedido;
import br.com.techtaste.microsservice.model.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDto(UUID id,
                                Status status,
                                String cpf,
                                List<ItemPedido> itens,
                                BigDecimal valorTotal,
                                LocalDate data) {
    public PedidoResponseDto(Pedido pedido){
        this(pedido.getId(), pedido.getStatus(), pedido.getCpf(), pedido.getItens(), pedido.getValorTotal(), pedido.getData());
    }
}
