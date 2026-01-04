package com.byteanalysis.easychannel.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class MessageToSend {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private From from;
	
	@ManyToOne
	private To to;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date shippingForecast;
	
	private Boolean sended;
	
	@Column(length=150)
	private String text;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public From getFrom() {
		return from;
	}

	public void setFrom(From from) {
		this.from = from;
	}

	public To getTo() {
		return to;
	}

	public void setTo(To to) {
		this.to = to;
	}

	public Date getShippingForecast() {
		return shippingForecast;
	}

	public void setShippingForecast(Date shippingForecast) {
		this.shippingForecast = shippingForecast;
	}

	public Boolean getSended() {
		return sended;
	}

	public void setSended(Boolean sended) {
		this.sended = sended;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
