package br.com.jwtauth.message;

import org.springframework.http.HttpStatus;
import java.io.Serializable;

/**
 * Classe model para tratamento das mensagens dos responses da aplicação.
 * 
 * @author Felipe Nascimento
 *
 */

public class ResponseMessages implements Serializable {
	private static final long serialVersionUID = -7424247256482415017L;
	private String statusMessage;
	private HttpStatus statusRequest;
	private int statusCode;
	
	/**
	 * Construtor da classe parametrizado.
	 * 
	 * @param statusMessage - mensagem do status da requisição
	 * @param statusRequest - status da requisição
	 * @param statusCode - código do status da requisição
	 * 
	 */
	public ResponseMessages(String statusMessage, HttpStatus statusRequest, int statusCode) {
		this.statusMessage = statusMessage;
		this.statusRequest = statusRequest;
		this.statusCode = statusCode;
	}
	
	/**
	 * Retorna o atributo statusMessage.
	 * 
	 * @return o status da mensagem do tipo {@link String}.
	 * 
	 */
	public String getStatusMessage() {
		return statusMessage;
	}
	
	/**
	 * Especifica o atributo statusMessage.
	 * .
	 * @param statusMessage {@link String} referente ao status da mensagem que será setado.
	 * 
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	/**
	 * Retorna o atributo statusRequest.
	 * 
	 * @return o status da requisição do tipo {@link HttpStatus}.
	 * 
	 */
	public HttpStatus getStatusRequest() {
		return statusRequest;
	}
	
	/**
	 * Especifica o atributo statusRequest.
	 * .
	 * @param statusRequest {@link HttpStatus} referente ao status da requisição que será setado.
	 * 
	 */
	public void setStatusRequest(HttpStatus statusRequest) {
		this.statusRequest = statusRequest;
	}
	
	/**
	 * Retorna o atributo statusCode.
	 * 
	 * @return o código do status da requisição do tipo int.
	 * 
	 */
	public int getStatusCode() {
		return statusCode;
	}
	
	/**
	 * Especifica o atributo statusCode.
	 * .
	 * @param statusCode int referente ao código do status da requisição que será setado.
	 * 
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}