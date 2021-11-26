package br.alfa.alertaapi.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ALERT_EMISSION")
@Table(name = "ALERT_EMISSION")
public class AlertEmission {
	@Id
	@GeneratedValue
	private int id;
	private LocalDateTime begin;
	private LocalDateTime end;
	@Column(name = "USER_id")
	private int userId;
	@Column(name = "ALERT_id")
	private int alertId;
	@Column(name = "hitted_users")
	private int hittedUsers;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getBegin() {
		return begin;
	}
	public void setBegin(LocalDateTime begin) {
		this.begin = begin;
	}
	public LocalDateTime getEnd() {
		return end;
	}
	public void setEnd(LocalDateTime end) {
		this.end = end;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAlertId() {
		return alertId;
	}
	public void setAlertId(int alertId) {
		this.alertId = alertId;
	}
	public int getHittedUsers() {
		return hittedUsers;
	}
	public void setHittedUsers(int hittedUsers) {
		this.hittedUsers = hittedUsers;
	}
}
