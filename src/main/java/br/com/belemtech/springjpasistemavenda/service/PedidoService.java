package br.com.belemtech.springjpasistemavenda.service;

import br.com.belemtech.springjpasistemavenda.dto.PedidoDTO;
import br.com.belemtech.springjpasistemavenda.entity.Pedido;

public interface PedidoService {

    Pedido Salvar (PedidoDTO dto);

}
