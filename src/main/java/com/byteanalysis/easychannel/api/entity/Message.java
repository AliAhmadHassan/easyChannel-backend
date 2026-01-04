package com.byteanalysis.easychannel.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "tb_message")
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private To to;
	
	@ManyToOne
	private From from;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	private String text;
	
	private Boolean incoming;
	
	@ManyToOne
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public To getTo() {
		return to;
	}

	public void setTo(To to) {
		this.to = to;
	}

	public From getFrom() {
		return from;
	}

	public void setFrom(From from) {
		this.from = from;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getIncoming() {
		return incoming;
	}

	public void setIncoming(Boolean incoming) {
		this.incoming = incoming;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
