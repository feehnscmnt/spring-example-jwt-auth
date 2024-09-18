package br.com.jwtauth;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import java.io.Serializable;

/**
 * Classe responsável pela inicialização da aplicação.
 * 
 * @author Felipe Nascimento
 * 
 */

@SpringBootApplication
@ComponentScan(basePackages = { "br.com.jwtauth.controller", "br.com.jwtauth.security" })
public class SpringExampleJwtAuthApplication implements Serializable {
	private static final long serialVersionUID = -2754524501283439149L;

	public static void main(String[] args) {
		SpringApplication.run(SpringExampleJwtAuthApplication.class, args);
	}

}