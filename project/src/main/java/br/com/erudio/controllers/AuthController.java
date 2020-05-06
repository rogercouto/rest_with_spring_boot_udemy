package br.com.erudio.controllers;

import static org.springframework.http.ResponseEntity.ok;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.repositories.UserRepository;
import br.com.erudio.security.AccountCredentialsVO;
import br.com.erudio.security.jwt.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(
			value = "/signin",
			produces = { "application/json", "application/xml", "application/x-yaml" }, 
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> signin(@RequestBody AccountCredentialsVO account){
		var authentication = new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword());
		authenticationManager.authenticate(authentication);
		var user = userRepository.findByUsername(authentication.getName());
		var token = "";
		if (user != null) {
			token = tokenProvider.createToken(user.getUsername(), user.getRoles());
		}else {
			throw new UsernameNotFoundException(String.format("username %s not found", authentication.getName()));
		}
		Map<Object, Object> model = new HashMap<>();
		model.put("username", user.getUsername());
		model.put("token", token);
		return ok(model);
	}
	
}
