package com.byteanalysis.easychannel.api.entity;

import javax.persistence.*;

import com.byteanalysis.easychannel.api.security.enums.ProfileEnum;

@Entity(name="tb_user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=50)
	private String username;
	
	@Column(length=100)
	private String name;
	
	@Column(length=255)
	private String password;
	
	private ProfileEnum profile;
	
	@ManyToOne
	private Group group;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ProfileEnum getProfile() {
		return profile;
	}

	public void setProfile(ProfileEnum profile) {
		this.profile = profile;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
}
