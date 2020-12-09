package com.escalab.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escalab.dto.DocumentResumenDTO;
import com.escalab.exception.ModeloNotFoundException;
import com.escalab.model.Document;
import com.escalab.model.Usuario;
import com.escalab.service.IDocumentService;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {
	
	@Autowired
	private IDocumentService service;
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarId')")
	@GetMapping("/list/{id}")
	public ResponseEntity<List<Document>> listarPorUsuario(@PathVariable("id") Usuario usuario){
		List<Document> lista = service.listarDocumentPorUsuario(usuario.getIdUsuario());
		return new ResponseEntity<List<Document>>(lista,HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('registrar')")
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Document document){
		Document doc = service.guardar(document);
		//documents/1
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(doc.getIdDocument())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('modificar')")
	@PutMapping
	public ResponseEntity<Document> modificar(@Valid @RequestBody Document document){
		Document doc = service.modificar(document);
		return new ResponseEntity<Document>(doc,HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('eliminar')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Document doc = service.leerPorId(id);
		if (doc.getIdDocument() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: [" + id + "]");
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	//nivel 1
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	@GetMapping("/pageable")
	public ResponseEntity<Page<Document>> listarDocumentType(Pageable pageable){
		Page<Document> docs = service.listarPageable(pageable);
		return new ResponseEntity<Page<Document>>(docs, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarResumen")
	public ResponseEntity<List<DocumentResumenDTO>> listarResumen() {
		List<DocumentResumenDTO> consultas = new ArrayList<>();
		consultas = service.listarResumen();
		return new ResponseEntity<List<DocumentResumenDTO>>(consultas, HttpStatus.OK);
	}

}
