package com.escalab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Informacion de las empresas")
@Entity
@Table(name = "company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCompany;
	
	@ApiModelProperty(notes = "Razon Social debe tener minimo 3 caracteres")
	@Size(min = 3, message = "Razon Social debe tener minimo 3 caracteres")
	@Column(name = "razon_social", nullable = false, length = 70)
	private String razonSocial;
	
	@ApiModelProperty(notes = "taxPayerId debe tener minimo 7 caracteres")
	@Size(min = 7,max = 10, message = "taxPayerId debe tener minimo 7 caracteres")
	@Column(name = "tax_payer_id", nullable = false)
	private String taxPayerId;
	
	@ApiModelProperty(notes = "correo debe tener minimo 6 caracteres")
	@Email
	@Column(name = "email", nullable = true, length = 50)
	private  String email;
	
	@ApiModelProperty(notes = "telefono debe tener minimo 6 caracteres")
	@Size(min = 6, max = 10, message = "telefono debe tener minimo 6 caracteres")
	@Column(name = "telephone", nullable = true, length = 10)
	private String telephone;
	
	@ApiModelProperty(notes = "Direccion debe tener minimo 3 caracteres")
	@Size(min = 3, max = 20, message = "Direccion debe tener minimo 3 caracteres")
	@Column(name = "direction", nullable = true, length = 10)
	private String direction;
	
	@ManyToOne
	@JoinColumn(name = "id_status", nullable = false, foreignKey = @ForeignKey(name = "FK_company_status"))
	private Status status;

	public Integer getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Integer idCompany) {
		this.idCompany = idCompany;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTaxPayerId() {
		return taxPayerId;
	}

	public void setTaxPayerId(String taxPayerId) {
		this.taxPayerId = taxPayerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telepone) {
		this.telephone = telepone;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
}
