package br.com.jwtauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.jwtauth.config.SpringExampleJwtAuthProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import br.com.jwtauth.message.ResponseMessagesModel;
import br.com.jwtauth.message.ResponseTokenModel;
import org.springframework.http.ResponseEntity;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.apache.logging.log4j.Logger;
import lombok.RequiredArgsConstructor;
import br.com.jwtauth.util.JwtUtils;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.Date;

/**
 * Classe controller responsável pela autenticação.
 * 
 * @author Felipe Nascimento
 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class JwtAuthController implements Serializable {
	private static final Logger LOG = LogManager.getLogger(JwtAuthController.class.getName());
	private final SpringExampleJwtAuthProperties springExampleJwtAuthProperties;
	private static final long serialVersionUID = -2735566812964752958L;

	/**
	 * Método responsável pela autenticação.
	 * 
	 * @param clientId - {@link RequestParam} / {@link String} - clientId da API
	 * @param clientSecret - {@link RequestParam} / {@link String} - clientSecret da API
	 * 
	 * @return token JWT para uso dos outros endpoints
	 * 
	 */
	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public ResponseEntity<Object> auth(@RequestParam String clientId, @RequestParam String clientSecret) {
		
		LOG.info("Autenticando o usuário {} para gerar o Token JWT...", clientId);
		
		if (Objects.equals(clientId, springExampleJwtAuthProperties.getClientId()) && Objects.equals(clientSecret, springExampleJwtAuthProperties.getClientSecret())) {
			
			var iat = Date.from(Instant.now());
			var exp = 5 * 60 * 1000L;
			var scope = "read write";
			var tokenType = "Bearer";
			
			var accessToken = JwtUtils.generateToken(clientId, scope, iat, exp);
			
			LOG.info("Token JWT gerado com sucesso.");
			
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseTokenModel(accessToken, tokenType, exp, scope));
	        
		}
		
		LOG.error("As credenciais de autenticação estão inválidas.");
		
		return ResponseEntity.status(HttpStatus.OK)
			.body(new ResponseMessagesModel("As credenciais de autenticação estão inválidas.", HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value()));
		
	}
	
	/**
	 * Método responsável pela autenticação.
	 * 
	 * @return objeto - {@link Object} - com a mensagem de desautorização
	 * 
	 */
	@GetMapping("/test")
	public ResponseEntity<Object> test() {
		
		LOG.info("Você está autenticado.");
		
		return ResponseEntity.status(HttpStatus.OK)
			.body(new ResponseMessagesModel("Você está autenticado.", HttpStatus.ACCEPTED, HttpStatus.ACCEPTED.value()));
		
	}
	
}