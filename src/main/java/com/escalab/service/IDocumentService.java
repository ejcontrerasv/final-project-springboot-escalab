package com.escalab.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.escalab.dto.DocumentResumenDTO;
import com.escalab.model.Document;

public interface IDocumentService {
	
	Document guardar(Document document);
	boolean eliminar(Integer id);
	Document modificar(Document document); 
	Document leerPorId(Integer id);
	byte[] leerDocument(Integer idDocument);
	Page<Document> listarPageable(Pageable pageable);
	List<Document> listarDocumentPorUsuario(Integer idUsuario);
	byte[] generarReporte();
	List<DocumentResumenDTO> listarResumen();
	
}
