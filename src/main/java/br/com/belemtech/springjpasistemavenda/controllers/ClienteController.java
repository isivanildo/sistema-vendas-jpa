package br.com.belemtech.springjpasistemavenda.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.belemtech.springjpasistemavenda.entity.Cliente;
import br.com.belemtech.springjpasistemavenda.repository.ClienteRepository;
import org.springframework.web.bind.annotation.PutMapping;


@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteRepository clientes;

    public ClienteController(ClienteRepository clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/consultar/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getByClienteId(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/salvar")
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clientes.save(cliente);

        return ResponseEntity.ok().body(clienteSalvo);
    }

    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Cliente> deletar(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()) {
            clientes.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();        
    }

    @PutMapping("atualizar/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return clientes.findById(id)
        .map(cli -> {
            cliente.setId(cli.getId());
            clientes.save(cliente);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/consultas")
    public ResponseEntity<List<Cliente>> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                                .withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Cliente> example = Example.of(filtro, matcher);

        List<Cliente> lista = clientes.findAll(example);

        return ResponseEntity.ok(lista);
    }

}
