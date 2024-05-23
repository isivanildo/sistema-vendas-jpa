package br.com.belemtech.springjpasistemavenda;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.belemtech.springjpasistemavenda.entity.Cliente;
import br.com.belemtech.springjpasistemavenda.repository.Clientes;

@SpringBootApplication
public class SpringJpaSistemaVendaApplication {

	@Bean
	public CommandLineRunner init(Clientes clientes) {
		return args -> {
			System.out.println("Salvando clientes");
			clientes.salvar(new Cliente("Ivanildo"));
			clientes.salvar(new Cliente("Renata"));

			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("Buscando clientes");
			clientes.buscarPorNome("Re").forEach(System.out::println);

			System.out.println("Deletando clientes");
			clientes.obterTodos().forEach(c -> {
				clientes.deletar(c);
			});

			System.out.println("Buscando todos os clientes");
			todosClientes = clientes.obterTodos();
			if (todosClientes.isEmpty()) {
				System.out.println("Nenhum cliente encontrado");
			} else {
				todosClientes.forEach(System.out::println);
			}

			System.out.println("Atualizando clientes");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " Atualizada");
				clientes.atualizar(c);
			});

			todosClientes.forEach(System.out::println);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaSistemaVendaApplication.class, args);
	}

}
