package com.techgeeknext.security.google.oauth2.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.techgeeknext.security.google.oauth2.model.Users;
import com.techgeeknext.security.google.oauth2.repository.UserRepository;

@Component("oauth2authSuccessHandler")
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();;
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;


	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
		String firstName = null;
		if (userRepository.findByUsername(authentication.getName()) == null) {
			firstName = authenticationToken.getPrincipal().getAttributes().get("given_name").toString();
			String email = authenticationToken.getPrincipal().getAttributes().get("email").toString();
			Users user = new Users(email, firstName, " ", email, encoder.encode("pass123"), true);
			userRepository.save(user);
		}
		this.redirectStrategy.sendRedirect(request, response,firstName!=null?"/hello?name="+firstName:"/hello");
	}
}
