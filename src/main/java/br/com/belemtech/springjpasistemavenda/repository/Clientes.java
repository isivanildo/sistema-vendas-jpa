package br.com.belemtech.springjpasistemavenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.belemtech.springjpasistemavenda.entity.Cliente;
import jakarta.transaction.Transactional;

public interface Clientes extends JpaRepository<Cliente, Integer> {

    @Query(value = "select * from cliente where nome like '%:nome%'", nativeQuery = true)
    List<Cliente> buscarPornome(@Param("nome") String nome);
    
    @Query(value = "delete from Cliente c where c.nome =:nome")
    @Modifying
    @Transactional
    void deleteByName(String nome);

    boolean existsByNome(String nome);

    @Query("select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}
