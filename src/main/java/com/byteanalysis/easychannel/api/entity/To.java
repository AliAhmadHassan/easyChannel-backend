package com.byteanalysis.easychannel.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="tb_to")
public class To {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=50)
	private String toKey;
	
	@ManyToOne
	private ToType toType;
	
	@ManyToOne
	private User userPreferred;
	
	@ManyToOne
	private Situation situation;
	
	@Column(length=100)
	private String name;
	
	@Column(length=15)
	private String identity;
	
	@Column(length=50)
	private String contractNumber;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToKey() {
		return toKey;
	}

	public void setToKey(String toKey) {
		this.toKey = toKey;
	}

	public ToType getToType() {
		return toType;
	}

	public void setToType(ToType toType) {
		this.toType = toType;
	}

	public User getUserPreferred() {
		return userPreferred;
	}

	public void setUserPreferred(User userPreferred) {
		this.userPreferred = userPreferred;
	}

	public Situation getSituation() {
		return situation;
	}

	public void setSituation(Situation situation) {
		this.situation = situation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
}
