package com.todo.models;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Todo {
	
	@NotNull
	private UUID Id;
	
	@NotEmpty
	@Size(min = 1, max = 150)
	private String title;
	
	@NotEmpty
	@Size(min = 5, max = 350)
	private String description;
	
	private Date createdOn;
	
	private Date modifiedOn;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull
	@Future
	private Date completeBy;
	
	public Todo(){
		Date today = new Date();
		this.Id = UUID.randomUUID();
		this.createdOn = today;
		this.modifiedOn = today;
	}
	
	public Todo(String title, String description, Date completeBy){
		this();
		this.title = title;
		this.description = description;
		this.completeBy = completeBy;
	}
	
	public UUID getId() {
		return Id;
	}
	public void setId(UUID id) {
		Id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}
	public Date getCompleteBy() {
		return completeBy;
	}
	public void setCompleteBy(Date completeBy) {
		this.completeBy = completeBy;
	}

}
