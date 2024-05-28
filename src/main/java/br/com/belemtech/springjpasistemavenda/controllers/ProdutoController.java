package br.com.belemtech.springjpasistemavenda.controllers;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.belemtech.springjpasistemavenda.entity.Produto;
import br.com.belemtech.springjpasistemavenda.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoRepository produtos;

    public ProdutoController(ProdutoRepository produtos) {
        this.produtos = produtos;
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Produto buscaProdutoId(@PathVariable Integer id) {
        return produtos.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não cadastrado."));
    }

    @GetMapping("/filtro")
    public List<Produto> buscaProdutoFiltro(Produto filtro) {

        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Produto> example = Example.of(filtro, matcher);

        List<Produto> prod = produtos.findAll(example);
         
        return prod;
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvar(@RequestBody Produto produto) {
        return produtos.save(produto);
    }

    @PutMapping("/atualizar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody Produto produto) {

        produtos.findById(id)
        .map(prod -> {
            produto.setId(prod.getId());
            produtos.save(produto);
            return prod;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));         
        
    }

    @DeleteMapping("/excluir/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void Excluir(@PathVariable Integer id) {
        produtos.findById(id)
        .map(prod -> {
            produtos.delete(prod);
            return prod;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado."));
    }

}
