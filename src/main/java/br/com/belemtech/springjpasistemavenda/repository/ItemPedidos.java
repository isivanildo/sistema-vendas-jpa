package br.com.belemtech.springjpasistemavenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.belemtech.springjpasistemavenda.entity.ItemPedido;

public interface ItemPedidos extends JpaRepository<ItemPedido, Integer> {

}
