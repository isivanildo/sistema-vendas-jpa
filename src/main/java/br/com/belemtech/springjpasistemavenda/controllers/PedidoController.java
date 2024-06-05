package br.com.belemtech.springjpasistemavenda.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.belemtech.springjpasistemavenda.dto.PedidoDTO;
import br.com.belemtech.springjpasistemavenda.entity.Pedido;
import br.com.belemtech.springjpasistemavenda.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer Save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.Salvar(dto);

        return pedido.getId();
    } 

}
