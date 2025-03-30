package br.com.techtaste.microsservice.repository;

import br.com.techtaste.microsservice.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
}
