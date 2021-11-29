package br.alfa.alertaapi.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.alfa.alertaapi.models.Alert;
import br.alfa.alertaapi.models.AlertEmission;
import br.alfa.alertaapi.repository.AlertRepository;
import br.alfa.alertaapi.services.AlertaService;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api")
public class AlertaController {
	Logger logger = LoggerFactory.getLogger(AlertaController.class);

	@Autowired
	private AlertaService alertServ;
	
	@GetMapping("/alertas")
	public ResponseEntity<List<Alert>> listarAlertas(@RequestParam int id){
		Optional<List<Alert>> listaAlertas = Optional.ofNullable(alertServ.userAlerts(id));
		if (listaAlertas.isPresent()) {
			return ResponseEntity.ok(listaAlertas.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/novo-alerta")
	public ResponseEntity<Alert> newAlert(@RequestBody Alert alerta) {
		Optional<Alert> alertaCriado = Optional.ofNullable(alertServ.newAlert(alerta));
		if (alertaCriado.isPresent()) {
			return ResponseEntity.ok(alertaCriado.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/emite-alerta")
	public ResponseEntity<AlertEmission> newAlertEmission(@RequestBody AlertEmission alerta) {
		Optional<AlertEmission> alertaCriado = Optional.ofNullable(alertServ.emiteAlerta(alerta));
		if (alertaCriado.isPresent()) {
			return ResponseEntity.ok(alertaCriado.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/alerta")
	public ResponseEntity<Alert> pegarAlertas(@RequestParam int id){
		logger.info("ALERTA ID: " + id);
		Optional<Alert> alerta = Optional.ofNullable(alertServ.getAlert(id));
		if (alerta.isPresent()) {
			logger.info("DEU TUDO CERTO");
			return ResponseEntity.ok(alerta.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
