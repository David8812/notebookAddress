package com.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "family")
@Inheritance(strategy = InheritanceType.JOINED)
public class Family implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", length = 45)
	@NotEmpty
	private String nombre;

	@Column(name = "father_lastName", length = 45)
	@NotEmpty
	private String apellidoPaterno;

	@Column(name = "mother_lastname", length = 45)
	@NotEmpty
	private String apellidoMaterno;

	@Column(name = "phone", length = 45)
	@NotEmpty
	private String telefono;

	@Column(name = "address", length = 45)
	private String domicilio;

	public Family() {
	}

	public Family(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String domicilio) {
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.telefono = telefono;
		this.domicilio = domicilio;
	}

	@Override
	public String toString() {
		return String.format("[nombre: %s, apellidoPaterno: %s, apellidoMaterno: %s, telefono: %s, domicilio: %s]",
				nombre, apellidoPaterno, apellidoMaterno, telefono, domicilio);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String appelidoMaterno) {
		this.apellidoMaterno = appelidoMaterno;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
