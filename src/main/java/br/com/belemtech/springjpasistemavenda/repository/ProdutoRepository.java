package br.com.belemtech.springjpasistemavenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.belemtech.springjpasistemavenda.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
