package br.com.techtaste.microsservice.service;

import br.com.techtaste.microsservice.dto.AutorizacaoDto;
import br.com.techtaste.microsservice.dto.EmailDto;
import br.com.techtaste.microsservice.dto.PedidoRequestDto;
import br.com.techtaste.microsservice.dto.PedidoResponseDto;
import br.com.techtaste.microsservice.model.ItemPedido;
import br.com.techtaste.microsservice.model.Pedido;
import br.com.techtaste.microsservice.model.Status;
import br.com.techtaste.microsservice.producer.UsuarioProducer;
import br.com.techtaste.microsservice.repository.ItemPedidoRepository;
import br.com.techtaste.microsservice.repository.PedidoRepository;
import br.com.techtaste.microsservice.utils.AutorizacaoPagamentoClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;

    private AutorizacaoPagamentoClient autorizacaoPagamentoClient;

    private UsuarioProducer usuarioProducer;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository, AutorizacaoPagamentoClient autorizacaoPagamentoClient, UsuarioProducer usuarioProducer) {
        this.pedidoRepository = pedidoRepository;
        this.autorizacaoPagamentoClient = autorizacaoPagamentoClient;
        this.usuarioProducer = usuarioProducer;
    }

    public PedidoResponseDto cadastrandoPedido(PedidoRequestDto pedidoRequestDto) {

        Pedido pedido = new Pedido();
        BeanUtils.copyProperties(pedidoRequestDto, pedido);
        Status status = Status.AGUARDANDO_PAGAMENTO;
        pedido.setStatus(status);
        // 🔥 Associando os itens ao pedido
        List<ItemPedido> itens = pedidoRequestDto.itens().stream().map(itemDto -> {
            ItemPedido item = new ItemPedido();
            BeanUtils.copyProperties(itemDto, item);
            item.setPedido(pedido);
            return item;
        }).collect(Collectors.toList());

        pedido.setItens(itens); // ⚠️ Adicionando os itens ao pedido
        pedido.setData(LocalDate.now());
        pedido.calcularTotal();

        pedidoRepository.save(pedido);



        status = obterStatusPagamento(pedido.getId().toString());
        pedido.setStatus(status);
        pedidoRepository.save(pedido);

        return new PedidoResponseDto(pedido);

    }

    public List<PedidoResponseDto> listarPedidos() {
        return pedidoRepository.findAll().stream().map(p -> new PedidoResponseDto(p)).collect(Collectors.toList());
    }

    private Status obterStatusPagamento(String id) {
        AutorizacaoDto autorizacaoDto = autorizacaoPagamentoClient.obterAutorizacao(id);
        if(autorizacaoDto.status().equals("autorizado")) {
            return Status.PREPARANDO;
        }
        return Status.RECUSADO;
    }

    public PedidoResponseDto cadastrandoPedido(PedidoRequestDto pedidoDto, Boolean comErro) {
        Pedido pedido = new Pedido();
        BeanUtils.copyProperties(pedidoDto, pedido);
        Status status = Status.AGUARDANDO_PAGAMENTO;
        pedido.setStatus(status);
        // 🔥 Associando os itens ao pedido
        List<ItemPedido> itens = pedidoDto.itens().stream().map(itemDto -> {
            ItemPedido item = new ItemPedido();
            BeanUtils.copyProperties(itemDto, item);
            item.setPedido(pedido);
            return item;
        }).collect(Collectors.toList());

        pedido.setItens(itens); // ⚠️ Adicionando os itens ao pedido
        pedido.setData(LocalDate.now());
        pedido.calcularTotal();

        pedidoRepository.save(pedido);

        status = comErro ? Status.ERRO_CONSULTA_PGTO: obterStatusPagamento(pedido.getId().toString());
        pedido.setStatus(status);
        pedidoRepository.save(pedido);

        usuarioProducer.enviarEmail(new EmailDto(pedido));
        return new PedidoResponseDto(pedido);
    }


}
