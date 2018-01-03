package com.jinzhuang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UID")
	private Long id;

	@Column(name = "USERNAME")
	@Size(max = 20, min = 3, message = "{user.name.invalid}")
	private String userName;
	
	@Column(name = "PHONE", unique = true)
	private String phone;

	@Column(name = "PASSWORD")
	private String password; //

	@Column(name = "ROLENAME")
	private String roleName; //

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + userName + ", phone=" + phone + ", password=" + password
				+ ", roleName=" + roleName + "]";
	}

	// Getter and Setter methods
	// ...
}