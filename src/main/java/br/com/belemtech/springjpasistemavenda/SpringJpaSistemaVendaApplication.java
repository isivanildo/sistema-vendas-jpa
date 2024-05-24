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
			clientes.save(new Cliente("Ivanildo"));
			clientes.save(new Cliente("Renata"));

			boolean exists = clientes.existsByNome("Ivanildo33");

			System.out.println("Existe um cliente com nome Ivanildo " + exists);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaSistemaVendaApplication.class, args);
	}

}
