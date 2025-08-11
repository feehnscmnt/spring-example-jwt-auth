package br.com.jwtauth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import lombok.ToString;
import lombok.Data;

/**
 * Classe responsável pela configuração de novos atributos no application.properties.
 * 
 * @author Felipe Nascimento
 *
 */

@Data
@ToString
@Configuration
@EqualsAndHashCode
@ConfigurationProperties("jwt-auth")
public class SpringExampleJwtAuthProperties implements Serializable {
	private static final long serialVersionUID = 5886861985674420505L;
	private String clientSecret;
	private String clientId;
}