package br.com.techtaste.microsservice.dto;

import br.com.techtaste.microsservice.model.ItemPedido;

import java.util.List;

public record PedidoRequestDto(String cpf, List<ItemPedido> itens) {
}
