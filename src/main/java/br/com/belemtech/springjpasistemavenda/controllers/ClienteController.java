package br.com.belemtech.springjpasistemavenda.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.belemtech.springjpasistemavenda.entity.Cliente;
import br.com.belemtech.springjpasistemavenda.repository.ClienteRepository;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteRepository clientes;

    public ClienteController(ClienteRepository clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/busca/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getByClienteId(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }

        return ResponseEntity.notFound().build();
    }

}
