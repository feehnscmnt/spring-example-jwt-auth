package br.com.jwtauth.filter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletException;
import br.com.jwtauth.util.JwtUtils;
import javax.servlet.FilterChain;
import java.io.Serializable;
import java.io.IOException;

/**
 * Classe responsável pelo filtro de segurança da API.
 * 
 * @author Felipe Nascimento
 * 
 */

public class JwtTokenFilter extends OncePerRequestFilter implements Serializable {
	private static final long serialVersionUID = 6127823280264977630L;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain filterChain) throws ServletException, IOException {
		String header = req.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(req, resp);
            return;
        }

        String token = header.replace("Bearer ", "");
        String username = JwtUtils.validateTokenAndGetUsername(token);

        if (username != null) {
            SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(username, null, null));
        }

        filterChain.doFilter(req, resp);
	}
	
}