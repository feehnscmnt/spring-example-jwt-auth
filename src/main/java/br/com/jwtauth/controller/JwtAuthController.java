package br.com.jwtauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import br.com.jwtauth.message.ResponseMessages;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.apache.logging.log4j.Logger;
import br.com.jwtauth.util.JwtUtils;
import java.io.Serializable;
import java.util.Objects;

/**
 * Classe controller responsável pela autenticação.
 * 
 * @author Felipe Nascimento
 *
 */

@RestController
@RequestMapping("/v1")
public class JwtAuthController implements Serializable {
	private static final Logger LOG = LogManager.getLogger(JwtAuthController.class.getName());
	private static final long serialVersionUID = -2735566812964752958L;

	/**
	 * Método responsável pela autenticação.
	 * 
	 * @param usuario - {@link RequestParam} / {@link String} - usuario que será autenticado
	 * 
	 * @return token JWT para uso dos outros endpoints
	 * 
	 */
	@GetMapping("/auth")
	public ResponseEntity<Object> auth(@RequestParam String usuario) {
		
		LOG.info("Autenticando o usuário {} para gerar o Token JWT...", usuario);
		
		if (Objects.equals(usuario, "JWT-Auth")) {
			
			LOG.info("Token JWT gerado com sucesso.");
			
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages(JwtUtils.generateToken(usuario), HttpStatus.CREATED, HttpStatus.CREATED.value()));
	        
		} else {
			
			LOG.error("As credenciais de autenticação estão inválidas.");
			
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages("As credenciais de autenticação estão inválidas.", HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value()));
			
		}
		
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
			.body(new ResponseMessages("Você está autenticado.", HttpStatus.ACCEPTED, HttpStatus.ACCEPTED.value()));
		
	}
	
}