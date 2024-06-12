package br.com.belemtech.springjpasistemavenda.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.belemtech.springjpasistemavenda.dto.InformacaoPedidoDTO;
import br.com.belemtech.springjpasistemavenda.dto.InformacoesPedidoDTO;
import br.com.belemtech.springjpasistemavenda.dto.PedidoDTO;
import br.com.belemtech.springjpasistemavenda.entity.ItemPedido;
import br.com.belemtech.springjpasistemavenda.entity.Pedido;
import br.com.belemtech.springjpasistemavenda.exception.RegraNegocioException;
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

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return service.obterPedidoCompleto(id)
                    .map(p -> converter(p))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    private InformacoesPedidoDTO converter(Pedido pedido) {
        return InformacoesPedidoDTO.builder()
        .codigo(pedido.getId())
        .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        .cpf(pedido.getCliente().getCpf())
        .nomeCliente(pedido.getCliente().getNome())
        .total(pedido.getTotal())
        .items(converter(pedido.getItens()))
        .build();
    }

    private List<InformacaoPedidoDTO> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream().map(item -> InformacaoPedidoDTO.builder()
                .descricaoProduto(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPreco())
                .quantidade(item.getQuantidade())
                .build()
        ).collect(Collectors.toList()); 
    }
    

}
