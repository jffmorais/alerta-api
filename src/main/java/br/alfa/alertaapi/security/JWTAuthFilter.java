package br.alfa.alertaapi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.alfa.alertaapi.models.MyUserDetails;
import br.alfa.alertaapi.models.User;

public class JWTAuthFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	private Environment env;

	public JWTAuthFilter(AuthenticationManager authenticationManager, Environment env) {
		this.authenticationManager = authenticationManager;
		this.env = env;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			User usuario = new ObjectMapper().readValue(request.getInputStream(), User.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getUserName(),
					usuario.getPassword(), new ArrayList<>()));
		} catch (JsonParseException e) {
			throw new RuntimeException("Falha ao autenticar usuario", e);
		} catch (JsonMappingException e) {
			throw new RuntimeException("Falha ao autenticar usuario", e);
		} catch (IOException e) {
			throw new RuntimeException("Falha ao autenticar usuario", e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		MyUserDetails usuarioData = (MyUserDetails) authResult.getPrincipal();
		String token = JWT.create().withSubject(usuarioData.getId())
				.withExpiresAt(new Date(System.currentTimeMillis() + Integer.parseInt(this.env.getProperty("jwt.timeout"))))
				.sign(Algorithm.HMAC512(this.env.getProperty("jwt.sign")));
		response.getWriter().write(token);
		response.getWriter().flush();
	}
}
