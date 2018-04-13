package com.nelioalves.cursomcapi;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.nelioalves.cursomcapi.domain.Categoria;
import com.nelioalves.cursomcapi.repositories.CategoriaRepository;

@SpringBootApplication
public class MyApiApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(MyApiApplication.class, args);
	}

	/*Executa alguma ação quando a aplicação iniciar*/
	@Override
	public void run(String... arg0) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		repository.save(Arrays.asList(cat1, cat2));
	}
}
