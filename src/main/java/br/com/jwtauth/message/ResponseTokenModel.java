package br.com.jwtauth.message;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import lombok.ToString;
import lombok.Data;

/**
 * Classe model responsável pelo objeto que irá trafegar as informações do access token.
 * 
 * @author Felipe Nascimento
 *
 */

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class ResponseTokenModel implements Serializable {
	private static final long serialVersionUID = 4792638422286454528L;
	private String accessToken;
	private String tokenType;
	private Long expiresIn;
	private String scope;
}