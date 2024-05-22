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
			clientes.salvar(new Cliente("Ivanildo"));
			clientes.salvar(new Cliente("Renata"));

			List<Cliente> todosClientes = clientes.obterTodos();

			todosClientes.forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaSistemaVendaApplication.class, args);
	}

}
