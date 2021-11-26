package br.alfa.alertaapi.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "ALERT")
@Table(name = "ALERT")
public class Alert {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String description;
	private boolean active;
	@Column(name = "USER_id")
	private int userId;
	private String district;
	@Column(name = "threat_level")
	private int threatLevel;
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getThreatLevel() {
		return threatLevel;
	}
	public void setThreatLevel(int threatLevel) {
		this.threatLevel = threatLevel;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "Alert [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active
				+ ", userId=" + userId + ", district=" + district + ", threatLevel=" + threatLevel + ", createdAt="
				+ createdAt + "]";
	}
	
}
