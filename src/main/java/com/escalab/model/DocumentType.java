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
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Informacion de los tipos de documentos")
@Entity
@Table(name = "document_type")
public class DocumentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idType;
	
	@ApiModelProperty(notes = "El nombre del tipo de documento debe tener minimo 3 caracteres", required = true)
	@Size(min = 3, message = "El nombre del tipo de documento debe tener minimo 3 caracteres")
	@Column(name = "name", nullable = false, length = 30)
	private String name;
	
	@ApiModelProperty(notes = "la descripcion debe tener minimo 5 caracteres", required = false)
	@Size(min = 5, message = "la descripcion debe tener minimo 5 caracteres")
	@Column(name = "description", nullable = true, length = 100)
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "id_status", nullable = false, foreignKey = @ForeignKey(name = "FK_type_status"))
	private Status status;

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
