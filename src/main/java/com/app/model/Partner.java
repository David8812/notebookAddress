package com.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "partner")
public class Partner extends Family implements Serializable {
	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "company", length = 45)
	private String company;

	@Column(name = "area", length = 45)
	private String area;

	@Column(name = "business_sector", length = 45)
	@NotNull
	@NotBlank
	private String rubro;

	public Partner() {
		super();
	}

	public Partner(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String domicilio,
			Long id, String company, String area, String rubro) {
		super(nombre, apellidoPaterno, apellidoMaterno, telefono, domicilio);
		this.id = id;
		this.company = company;
		this.area = area;
		this.rubro = rubro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("[company: %s, area: %s, rubro: %s]", company, area, rubro);
	}

}
