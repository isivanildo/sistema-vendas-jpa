package br.com.belemtech.springjpasistemavenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.belemtech.springjpasistemavenda.entity.Cliente;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);
    boolean existsByNome(String nome);

}
