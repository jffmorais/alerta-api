package br.alfa.alertaapi.models;

public class NotificationData {
	
	private int alertEmissionId;
	private int alertId;
	
	public NotificationData(int alertEmissionId, int alertId) {
		super();
		this.alertEmissionId = alertEmissionId;
		this.alertId = alertId;
	}
	
	public int getAlertEmissionId() {
		return alertEmissionId;
	}
	public void setAlertEmissionId(int alertEmissionId) {
		this.alertEmissionId = alertEmissionId;
	}
	public int getAlertId() {
		return alertId;
	}
	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}

}
