package com.escalab.dto;

public class DocumentResumenDTO {
	
	private Integer cantidad;
	private Integer tipoDocumento;
	private Integer usuario;
	
	public DocumentResumenDTO(Integer cantidad, Integer tipoDocumento, Integer usuario) {
		super();
		this.cantidad = cantidad;
		this.tipoDocumento = tipoDocumento;
		this.usuario = usuario;
	}

	public DocumentResumenDTO() {
		
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}
	
	

}
