package br.com.belemtech.springjpasistemavenda.service;

import java.util.Optional;

import br.com.belemtech.springjpasistemavenda.dto.PedidoDTO;
import br.com.belemtech.springjpasistemavenda.entity.Pedido;
import br.com.belemtech.springjpasistemavenda.enums.StatusPedido;

public interface PedidoService {

    Pedido Salvar (PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
