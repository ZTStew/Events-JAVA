package com.stewart.Events.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="messages")
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1,max=255,message="Message must be between 1-255 characters.")
	private String text;
	
	
	private Date createdAt;
	private Date updatedAt;
	
	@PrePersist
	public void OnCreate() { createdAt = new Date(); }
	@PreUpdate
	public void OnUpdate() { updatedAt = new Date(); }
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="event_id")
	private Event event;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	public Message() {}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
