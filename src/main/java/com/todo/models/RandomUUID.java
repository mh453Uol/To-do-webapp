package com.todo.models;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// Specifiying order
@JsonPropertyOrder({
	"timeStamp",
	"id"
})
public class RandomUUID {
	private UUID id;
	
	@JsonProperty("timeStamp") //change variable name to timeStamp
	private Date createOn;
	
	public RandomUUID() {
		this.id = UUID.randomUUID();
		this.createOn = new Date();
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Date getCreateOn() {
		return createOn;
	}
	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}
	
	
}
