package br.com.belemtech.springjpasistemavenda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.belemtech.springjpasistemavenda.entity.Cliente;
import br.com.belemtech.springjpasistemavenda.repository.ClienteRepository;

@SpringBootApplication
public class SpringJpaSistemaVendaApplication {

	@Bean
	public CommandLineRunner commandLine(@Autowired ClienteRepository clientes) {
		return args -> {
			Cliente c = new Cliente("Ivanildo");
			clientes.save(c);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaSistemaVendaApplication.class, args);
	}

}
