package br.com.jwtauth.util;

import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.LogManager;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.SecretKeySpec;
import org.apache.logging.log4j.Logger;
import io.jsonwebtoken.JwtException;
import java.security.MessageDigest;
import io.jsonwebtoken.Jwts;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

/**
 * Classe responsável pelo tratamento do token JWT.
 * 
 * @author Felipe Nascimento
 * 
 */

public class JwtUtils implements Serializable {
	private static final String KEY_SECRET = new String(Base64.getDecoder().decode("ZXhhbXBsZS1qd3QtYXV0aC1rZXktc2VjcmV0"));
	private static final Logger LOG = LogManager.getLogger(JwtUtils.class.getName());
	private static final long serialVersionUID = -531843526886721468L;
    
    /**
     * Método responsável pela geração do token JWT.
     * 
     * @param clientId - client id da API
     * @param scope - escopo do access token
     * @param iat - data de emissão do access token
     * @param exp - tempo de expiração do access token
     * 
     * @return token JWT gerado
     * 
     */
    public static String generateToken(String clientId, String scope, Date iat, Long exp) {
    	
    	return Jwts
        	.builder()
            .setSubject(clientId)
            .claim("scope", scope)
            .setIssuedAt(iat)
            .setExpiration(new Date(System.currentTimeMillis() + exp))
            .signWith(SignatureAlgorithm.HS256, generateSecretKey())
            .compact();
        
    }
    
    /**
     * Método responsável por validar o token JWT e obter o usuário da API.
     * 
     * @param token - token JWT
     * 
     * @return nome do usuário da API
     * 
     */
    public static String validateTokenAndGetUsername(String token) {
    	
        try {
        	
        	return Jwts
        		.parser()
                .setSigningKey(generateSecretKey())
                .parseClaimsJws(token)
                .getBody().getSubject();
        	
        } catch (JwtException | IllegalArgumentException e) {
        	
            LOG.error("Houve erro ao tentar validar e obter o usuário. Exception: {}", e.getMessage());
            
        }
        
        return null;
        
    }
    
    /**
     * Método responsável por gerar a chave secreta responsável pela assinatura dos tokens.
     *  
     * @return chave secreta
     * 
     */
    private static String generateSecretKey() {
    	
    	try {
    		
    		byte[] key = MessageDigest.getInstance("SHA-256").digest(KEY_SECRET.getBytes(StandardCharsets.UTF_8));
        	return Base64.getEncoder().encodeToString(new SecretKeySpec(key, 0, key.length, "AES").getEncoded());
        	
    	} catch (NoSuchAlgorithmException e) {
    		
    		LOG.error("Houve erro ao tentar gerar a chave secreta. Exception: {}", e.getMessage());
    		
    	}
    	
    	return null;
    	
    }
    
}