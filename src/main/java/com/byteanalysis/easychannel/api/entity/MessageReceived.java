package com.byteanalysis.easychannel.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="tb_message_received")
public class MessageReceived {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private To to;
	
	@ManyToOne
	private From from;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date receivedDate;
	
	private Boolean readed;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date readedDate;
	
	@ManyToOne
	private User user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Boolean getReaded() {
		return readed;
	}

	public void setReaded(Boolean readed) {
		this.readed = readed;
	}

	public Date getReadedDate() {
		return readedDate;
	}

	public void setReadedDate(Date readedDate) {
		this.readedDate = readedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
