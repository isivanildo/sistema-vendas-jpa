package br.com.belemtech.springjpasistemavenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.belemtech.springjpasistemavenda.entity.Cliente;
import br.com.belemtech.springjpasistemavenda.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
