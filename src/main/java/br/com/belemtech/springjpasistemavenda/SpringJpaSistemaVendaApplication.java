package br.com.belemtech.springjpasistemavenda;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.belemtech.springjpasistemavenda.entity.Cliente;
import br.com.belemtech.springjpasistemavenda.entity.Pedido;
import br.com.belemtech.springjpasistemavenda.repository.Clientes;
import br.com.belemtech.springjpasistemavenda.repository.Pedidos;

@SpringBootApplication
public class SpringJpaSistemaVendaApplication {

	@Bean
	public CommandLineRunner init(@Autowired Clientes clientes, @Autowired Pedidos pedidos) {
		return args -> {
			System.out.println("Salvando clientes");
			Cliente cliente = new Cliente("Ivanildo");
			clientes.save(cliente);
			
			Pedido p = new Pedido();
			p.setCliente(cliente);
			p.setDataPedido(LocalDate.now());
			p.setTotal(BigDecimal.valueOf(10.00));
			
			pedidos.save(p);

			// Cliente cliente = clientes.findClienteFetchPedidos(cli.getId());
			// System.out.println(cliente);
			// System.out.println(cliente.getPedidos());

			pedidos.findByCliente(cliente).forEach(System.out::println);

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaSistemaVendaApplication.class, args);
	}

}
