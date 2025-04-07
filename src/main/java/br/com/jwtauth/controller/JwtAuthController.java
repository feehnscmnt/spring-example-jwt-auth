package br.com.jwtauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import br.com.jwtauth.message.ResponseMessages;
import org.springframework.http.HttpStatus;
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
		
		if (Objects.equals(usuario, "JWT-Auth")) {
			
			return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseMessages(JwtUtils.generateToken(usuario), HttpStatus.CREATED, HttpStatus.CREATED.value()));
	        
		} else {
			
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
		
		return ResponseEntity.status(HttpStatus.OK)
			.body(new ResponseMessages("Você está autenticado.", HttpStatus.ACCEPTED, HttpStatus.ACCEPTED.value()));
		
	}
	
}