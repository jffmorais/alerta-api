package br.alfa.alertaapi.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.alfa.alertaapi.models.User;
import br.alfa.alertaapi.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	@PostMapping("/cadastrar")
	public ResponseEntity<User> cadastrar(@RequestBody User usuario){
		logger.info("Olar 2");
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		return ResponseEntity.ok(userRepository.save(usuario));
	}
	
	@GetMapping("/login")
	public ResponseEntity<Boolean> login(@RequestParam String userName, @RequestParam String password){
		
		logger.info("Olar 3");
		
		Optional<User> optUser = userRepository.findByUserName(userName);
		if(optUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
		}
		
		User usuario = optUser.get();
		boolean valid = encoder.matches(password, usuario.getPassword());
		
		HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
		return ResponseEntity.status(status).body(valid);
	}
}
