package br.com.jwtauth.util;

import java.security.NoSuchAlgorithmException;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import javax.crypto.spec.SecretKeySpec;
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
	private static final String KEY_SECRET = "example-jwt-auth-key-secret";
	private static final long serialVersionUID = -531843526886721468L;
    private static final long EXPIRATION_TIME = 5 * 60 * 1000L;

    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, generateSecretKey())
                .compact();
    }

    public static String validateTokenAndGetUsername(String token) {
        try {
        	return Jwts.parser()
                .setSigningKey(generateSecretKey())
                .parseClaimsJws(token)
                .getBody().getSubject();
        } catch(JwtException | IllegalArgumentException e) {
            return null;
        }
    }
    
    private static String generateSecretKey() {
    	try {
    		byte[] key = MessageDigest.getInstance("SHA-256").digest(KEY_SECRET.getBytes(StandardCharsets.UTF_8));
        	return Base64.getEncoder().encodeToString(new SecretKeySpec(key, 0, key.length, "AES").getEncoded());
    	} catch(NoSuchAlgorithmException e) {
    		return null;
    	}
    }
    
}