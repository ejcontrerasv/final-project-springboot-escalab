package com.escalab.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Informacion de los usuarios")
@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	@ManyToOne
	@JoinColumn(name = "id_company", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_company"))
	private Company company;
	
	@ApiModelProperty(notes = "El nombre del usuario debe tener minimo 3 caracteres", required = true)
	@Size(min = 3, message = "El nombre del usuario debe tener minimo 3 caracteres")
	@Column(name = "username", nullable = false, length = 50)
	private String username;
	
	@ApiModelProperty(notes = "Password asociada al usuario", required = true)
	@Column(name = "clave", nullable = false)
	private String password;
	
	@ApiModelProperty(notes = "El rut del usuario debe tener minimo 7 caracteres", required = true)
	@Size(min = 7, message = "El rut del usuario debe tener minimo 7 caracteres")
	@Column(name = "rut", nullable = false, length = 10)
	private String rut;
	
	@ApiModelProperty(notes = "E-mail del usuario", required = false)
	@Email
	@Column(name = "email", nullable = true, length = 50)
	private String email;
	
	@ApiModelProperty(notes = "La direccion debe tener al menos 3 caracteres", required = false)
	@Size(min = 3, message = "La direccion debe tener al menos 3 caracteres")
	@Column(name = "direction", nullable = true, length = 50)
	private String direction;
	
	@ApiModelProperty(notes = "El telefono debe tener al menos 6 digitos", required = false)
	@Size(min = 6, message = "El telefono debe tener al menos 6 digitos")
	@Column(name = "telephone", nullable = true, length = 15)
	private String telephone;
	
	@ManyToOne
	@JoinColumn(name = "id_status", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_status"))
	private Status status;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "IdRol"))
	private List<Rol> roles;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String name) {
		this.username = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

}
