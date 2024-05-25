package br.com.belemtech.springjpasistemavenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.belemtech.springjpasistemavenda.entity.ItemPedido;

public interface ItemPedidosRepository extends JpaRepository<ItemPedido, Integer> {

}
