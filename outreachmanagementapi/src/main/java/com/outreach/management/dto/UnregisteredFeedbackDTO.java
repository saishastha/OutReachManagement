package com.outreach.management.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnregisteredFeedbackDTO implements Serializable {

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUnregisteredReason() {
		return unregisteredReason;
	}

	public void setUnregisteredReason(String unregisteredReason) {
		this.unregisteredReason = unregisteredReason;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1438262088903709116L;

	private String eventId;

	private String email;

	private String unregisteredReason;

}
