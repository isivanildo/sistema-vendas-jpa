package br.com.belemtech.springjpasistemavenda.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.belemtech.springjpasistemavenda.dto.ItemPedidoDTO;
import br.com.belemtech.springjpasistemavenda.dto.PedidoDTO;
import br.com.belemtech.springjpasistemavenda.entity.Cliente;
import br.com.belemtech.springjpasistemavenda.entity.ItemPedido;
import br.com.belemtech.springjpasistemavenda.entity.Pedido;
import br.com.belemtech.springjpasistemavenda.entity.Produto;
import br.com.belemtech.springjpasistemavenda.exception.RegraNegocioException;
import br.com.belemtech.springjpasistemavenda.repository.ClienteRepository;
import br.com.belemtech.springjpasistemavenda.repository.ItemPedidosRepository;
import br.com.belemtech.springjpasistemavenda.repository.PedidoRepository;
import br.com.belemtech.springjpasistemavenda.repository.ProdutoRepository;
import br.com.belemtech.springjpasistemavenda.service.PedidoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository repository;    
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidosRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido Salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clienteRepository.findById(idCliente)
        .orElseThrow(() -> new RegraNegocioException("Código do cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedido = converterItems(pedido, dto.getItems());

        repository.save(pedido);
        itemPedidoRepository.saveAll(itemPedido);
        pedido.setItens(itemPedido);
        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possível salvar um pedido sem item");            
        }

        return items
              .stream()
              .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository.findById(idProduto)
                    .orElseThrow(() -> new RegraNegocioException("Código do produto inválido: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);

                    return itemPedido;
              }).collect(Collectors.toList());

    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

}
