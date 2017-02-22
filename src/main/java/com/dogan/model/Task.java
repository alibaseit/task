package com.dogan.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "t_tasks")
@DynamicUpdate
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
	//@JsonSerialize(using = DateSerializer.class)
	private Date dateCreated;
	
	private boolean finished;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Date getDateCreated() {;
		return dateCreated;
	}

	public void setDateCreated(Date date) {
		this.dateCreated = date;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", description=" + description + ", dateCreated=" + dateCreated
				+ ", finished=" + finished + "]";
	}

}
