package br.alfa.alertaapi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.alfa.alertaapi.models.Alert;
import br.alfa.alertaapi.models.AlertEmission;
import br.alfa.alertaapi.models.NotificationContents;
import br.alfa.alertaapi.models.NotificationData;
import br.alfa.alertaapi.models.NotificationHeadings;
import br.alfa.alertaapi.models.NotificationReq;
import br.alfa.alertaapi.models.NotificationRes;
import br.alfa.alertaapi.repository.AlertEmissionRepositoty;
import br.alfa.alertaapi.repository.AlertRepository;

@Service
public class AlertaService {
	
	@Autowired
	private AlertRepository alertRepo;
	
	@Autowired
	private AlertEmissionRepositoty alertEmissionRepo;
	
	@Autowired
	private NotificationService notificationServ;

	public List<Alert> userAlerts(int id){
		return alertRepo.findUserAlerts(id);
	}
	
	public Alert newAlert(Alert alerta) {
		alerta.setCreatedAt(LocalDateTime.now());
		return alertRepo.save(alerta);
	}
	
	public AlertEmission emiteAlerta(AlertEmission alert) {
		Alert alertObj = alertRepo.findAlertById(alert.getAlertId());
		NotificationReq reqBody = new NotificationReq();
		reqBody.setContents(new NotificationContents(alertObj.getDescription()));
		reqBody.setHeadings(new NotificationHeadings(alertObj.getName()));
		reqBody.setData(new NotificationData(alert.getId(), alert.getAlertId()));
		NotificationRes notification = notificationServ.sendNotification(reqBody);
		if(notification == null) {
			return null;
		}
		alert.setBegin(LocalDateTime.now());
		alert.setHittedUsers(Integer.parseInt(notification.getRecipients()));
		return alertEmissionRepo.save(alert);
	}
	
	public AlertEmission cancelaAlerta(AlertEmission alert) {
		alert.setEnd(LocalDateTime.now());
		return alertEmissionRepo.save(alert);
	}
}
