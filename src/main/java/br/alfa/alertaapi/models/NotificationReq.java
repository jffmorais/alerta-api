package br.alfa.alertaapi.models;

import java.util.List;

public class NotificationReq {
	
	private String app_id;
	private NotificationContents contents;
	private NotificationHeadings headings;
	private List<String> included_segments;
	private NotificationData data;
	
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public NotificationContents getContents() {
		return contents;
	}
	public void setContents(NotificationContents contents) {
		this.contents = contents;
	}
	public NotificationHeadings getHeadings() {
		return headings;
	}
	public void setHeadings(NotificationHeadings headings) {
		this.headings = headings;
	}
	public List<String> getIncluded_segments() {
		return included_segments;
	}
	public void setIncluded_segments(List<String> included_segments) {
		this.included_segments = included_segments;
	}
	public NotificationData getData() {
		return data;
	}
	public void setData(NotificationData data) {
		this.data = data;
	}
	
}
