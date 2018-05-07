package com.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "usuario")
public class User implements Serializable {
	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username", length = 45, unique = true)
	@NotBlank
	private String userName;

	@Column(name = "password", length = 45)
	@NotBlank
	private String password;

	private String confirmPassword;

	@Column(name = "roll", length = 45)
	@NotBlank
	private String roll;

	public User() {
	}

	public User(String userName, String password, String roll) {
		this.userName = userName;
		this.password = password;
		this.roll = roll;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean equals(Object o) {
		User u = (User) o;
		return this.userName.equals(u.getUserName()) && this.password.equals(u.getPassword());
	}

	@Override
	public String toString() {
		return String.format("[Id: %d, Nombre Usuario: %s, Password: %s, Roll:%s]", id, userName, password, roll);
	}

}
