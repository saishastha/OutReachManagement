package com.outreach.management.collections;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "EventReports")
public class EventReportCollections {

	@JsonProperty("eventId")
	private String eventId;

	@JsonProperty("month")
	private String month;

	@JsonProperty("baseLocation")
	private String baseLocation;

	@JsonProperty("beneficiaryName")
	private String beneficiaryName;

	@JsonProperty("venueAddress")
	private String venueAddress;

	@JsonProperty("councilName")
	private String councilName;

	@JsonProperty("project")
	private String project;

	@JsonProperty("category")
	private String category;

	@JsonProperty("eventName")
	private String eventName;

	@JsonProperty("eventDescription")
	private String eventDescription;

	@JsonProperty("eventDate")
	private String eventDate;

	@JsonProperty("totalNoVolunteers")
	private String totalNoVolunteers;

	@JsonProperty("totalVolunteersHours")
	private String totalVolunteersHours;

	@JsonProperty("totalTravelHours")
	private String totalTravelHours;

	@JsonProperty("overallVolunteeringHours")
	private String overallVolunteeringHours;

	@JsonProperty("livesImpacted")
	private String livesImpacted;

	@JsonProperty("activityType")
	private String activityType;

	@JsonProperty("status")
	private String status;

	@JsonProperty("pocId")
	private String pocId;

	@JsonProperty("pocName")
	private String pocName;

	@JsonProperty("pocContactNumber")
	private String pocContactNumber;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventDate() {
		return eventDate;
	}

	public String getStatus() {
		return status;
	}

	public String getPocId() {
		return pocId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPocId(String pocId) {
		this.pocId = pocId;
	}

}
