package br.alfa.alertaapi.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.alfa.alertaapi.models.Alert;
import br.alfa.alertaapi.repository.AlertRepository;

@RestController
@RequestMapping("/api")
public class HomeResource {
	
	Logger logger = LoggerFactory.getLogger(HomeResource.class);

	@Autowired
	private AlertRepository alertRepo;
	
	@GetMapping("/")
	public String home() {
		return ("<h1>Welcome</h1>");
	}
	
	@GetMapping("/user")
	public String user() {
		logger.info("Olar");
		return ("<h1>Welcome user</h1>");
	}
	
	@GetMapping("/admin")
	public String admin() {
		return ("<h1>Welcome Admin</h1>");
	}
	
	@GetMapping("/alertas")
	public ResponseEntity<List<Alert>> listarAlertas(){
		return ResponseEntity.ok(alertRepo.findAll());
	}
}
