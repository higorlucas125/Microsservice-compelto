package br.com.techtaste.microsservice.dto;

import br.com.techtaste.microsservice.model.Pedido;

public record EmailDto(String cpf, String pedidoId, String status) {

    public EmailDto(Pedido pedido){
        this(pedido.getCpf(),pedido.getId().toString(),pedido.getStatus().toString());
    }
}
