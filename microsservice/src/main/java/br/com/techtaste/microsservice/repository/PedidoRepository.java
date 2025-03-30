package br.com.techtaste.microsservice.repository;

import br.com.techtaste.microsservice.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
