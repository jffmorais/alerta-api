package br.alfa.alertaapi.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.alfa.alertaapi.models.NotificationReq;
import br.alfa.alertaapi.models.NotificationRes;

@Service
public class NotificationService {
	
	Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	private Environment env;

	public NotificationService(Environment env) {
		this.env = env;
	}
	
	public NotificationRes sendNotification(NotificationReq reqBody) {
		
		List<String> segments = new ArrayList<String>();
		segments.add("Subscribed Users");
		String endpoint = this.env.getProperty("onesignal.endpoint");
		String auth = this.env.getProperty("onesignal.auth");
		reqBody.setApp_id(this.env.getProperty("onesignal.appId"));
		reqBody.setIncluded_segments(segments);
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", "Basic " + auth);
			HttpEntity<NotificationReq> request = new HttpEntity<NotificationReq>(reqBody, headers);
			NotificationRes response = restTemplate.postForObject(endpoint, request, NotificationRes.class);
			return response;
		} catch (Exception e) {
			logger.error("Onesignal service exception: " + e.getMessage());
		}
		return null;
	}
}
