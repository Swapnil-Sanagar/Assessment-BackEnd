package com.example.Demo.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="trainingPrograms")
public class TrainingProgram {
	
	@Id
	private String id;
	
	@NotBlank
    private String traineeName;
	
	private Date scheduledDate;
	
	@NotBlank
	private String skill;
	
	private String statusVal = "Pending";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTraineeName() {
		return traineeName;
	}

	public void setTraineeName(String traineeName) {
		this.traineeName = traineeName;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getStatusVal() {
		return statusVal;
	}

	public void setStatusVal(String status) {
		this.statusVal = status;
	}
	
	
}
