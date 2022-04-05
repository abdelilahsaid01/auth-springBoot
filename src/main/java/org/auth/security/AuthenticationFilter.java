package org.auth.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.auth.SpringApplicationContext;
import org.auth.request.UtilisateurLoginRequest;
import org.auth.response.UtilisateurResponse;
import org.auth.service.impl.UtilisateurServiceImpl;
import org.auth.shared.dto.UtilisateurDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	

	// ========================================================================================================

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		try {

			UtilisateurLoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), UtilisateurLoginRequest.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getLogin(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	  @Override
	    protected void successfulAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res,
	                                            FilterChain chain,
	                                            Authentication auth) throws IOException, ServletException {
	        
	        String login = ((User) auth.getPrincipal()).getUsername(); 
	        UtilisateurServiceImpl userService = (UtilisateurServiceImpl)SpringApplicationContext.getBean("utilisateurServiceImpl");
	        UtilisateurDto userDto = userService.findUtilisateurByLogin(login);  
	        Gson g = new Gson();
	    	ModelMapper modelMapper = new ModelMapper();
	    	UtilisateurResponse userResponse = modelMapper.map(userDto, UtilisateurResponse.class);
	        String profil = g.toJson(userDto.getProfil());
	        
	        String token = Jwts.builder()
	                .setSubject(login)
	                .claim("id", userDto.getUtilisateurId())
	                .claim("profil", profil)
	                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
	                .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET )
	                .compact();
	       
	        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	        res.addHeader("Content-Type", "application/json");
	        res.addHeader("utilisateur_id", userResponse.getUtilisateurId());
	        userResponse.setToken(token);
	        res.getWriter().write(g.toJson(userResponse));

	    }  
}
