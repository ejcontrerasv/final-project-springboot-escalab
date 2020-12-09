package com.escalab.model;

import java.time.LocalDateTime;

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

@ApiModel(description = "Informacion de los documentos")
@Entity
@Table(name = "document")
public class Document {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDocument;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_document_usuario"))
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_status", nullable = false, foreignKey = @ForeignKey(name = "FK_document_status"))
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "id_document_type", nullable = false, foreignKey = @ForeignKey(name = "FK_document_type"))
	private DocumentType documentType;
	
	@ApiModelProperty(notes = "El nombre del documento debe tener minimo 3 caracteres", required = true)
	@Size(min = 3, message = "El nombre del documento debe tener minimo 3 caracteres")
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@ApiModelProperty(notes = "La extension debe tener minimo 2 caracteres",required = false)
	@Size(min = 2, message = "La extension debe tener minimo 2 caracteres")
	@Column(name = "filetype", nullable = true, length = 5)
	private String filetype;
	
	private LocalDateTime date;
	
	@Column(name = "contenido")
	private byte[] value;

	public Integer getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(Integer idDocument) {
		this.idDocument = idDocument;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public byte[] getValue() {
		return value;
	}

	public void setValue(byte[] value) {
		this.value = value;
	}
}
