package com.boot.clientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages ={"controller"})
public class EntityServiceClientesV1CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntityServiceClientesV1CrudApplication.class, args);
	}

}
