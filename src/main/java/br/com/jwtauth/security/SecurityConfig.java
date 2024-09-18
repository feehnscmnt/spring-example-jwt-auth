package br.com.jwtauth.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import br.com.jwtauth.filter.JwtTokenFilter;
import org.springframework.http.HttpMethod;
import org.mindrot.jbcrypt.BCrypt;
import java.io.Serializable;
import java.util.Base64;

/**
 * Classe responsável pelas configurações de segurança básica da aplicação.
 * 
 * @author Felipe Nascimento
 *
 */

@Configuration
public class SecurityConfig implements Serializable {
	private String apiUsername = new String(Base64.getDecoder().decode("U3ByaW5nLUV4YW1wbGUtSldULUF1dGg="));
	private String apiPassword = new String(Base64.getDecoder().decode("SldULUF1dGgtU3ByaW5nLUV4YW1wbGU="));
	private String apiRole = new String(Base64.getDecoder().decode("VVNFUg=="));
	private static final long serialVersionUID = 7108217250842338994L;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtTokenFilter jwtTokenFilter) throws Exception {
		httpSecurity
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth -> auth
				
			.antMatchers(HttpMethod.GET, "/v1/auth").permitAll()
			
			.anyRequest().authenticated().and()
			.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class));
	
	    return httpSecurity.build();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.inMemoryAuthentication()
	        .withUser(apiUsername)
	        .password(BCrypt.hashpw(apiPassword, BCrypt.gensalt()))
	        .roles(apiRole);
	}
	
	@Bean
	JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}
	
}