package br.com.jwtauth.message;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import lombok.Data;

/**
 * Classe model para tratamento das mensagens dos responses da aplicação.
 * 
 * @author Felipe Nascimento
 *
 */

@Data
@AllArgsConstructor
public class ResponseMessagesModel implements Serializable {
	private static final long serialVersionUID = -7424247256482415017L;
	private String statusMessage;
	private HttpStatus statusRequest;
	private Integer statusCode;
}