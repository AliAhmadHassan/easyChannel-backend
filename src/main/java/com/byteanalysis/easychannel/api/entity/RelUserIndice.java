package com.byteanalysis.easychannel.api.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="tb_rel_user_indice")
public class RelUserIndice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Indice indice;
	
	private BigDecimal value;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Indice getIndice() {
		return indice;
	}

	public void setIndice(Indice indice) {
		this.indice = indice;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
