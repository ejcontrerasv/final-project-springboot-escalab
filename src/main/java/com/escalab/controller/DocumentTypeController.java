package com.escalab.controller;

import java.net.URI;
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

import com.escalab.exception.ModeloNotFoundException;
import com.escalab.model.DocumentType;
import com.escalab.service.IDocumentTypeService;

@RestController
@RequestMapping("/api/v1/documents/types")
public class DocumentTypeController {
	
	@Autowired
	private IDocumentTypeService service;
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	@GetMapping
	public ResponseEntity<List<DocumentType>> listar(){
		List<DocumentType> lista = service.listar();
		return new ResponseEntity<List<DocumentType>>(lista, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('registrar')")
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody DocumentType documentType){
		DocumentType doc = service.registrar(documentType);
		//documents/types/1
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(doc.getIdType())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('modificar')")
	@PutMapping
	public ResponseEntity<DocumentType> modificar(@Valid @RequestBody DocumentType documentType){
		DocumentType obj = service.modificar(documentType);
		return new ResponseEntity<DocumentType>(obj, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('eliminar')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		DocumentType obj = service.leerPorId(id);
		if (obj.getIdType() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: [" + id +"]");
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	//nivel 1
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarId')")
	@GetMapping("/pageable")
	public ResponseEntity<Page<DocumentType>> listarDocumentType(Pageable pageable){
		Page<DocumentType> docs = service.listarPageable(pageable);
		return new ResponseEntity<Page<DocumentType>>(docs, HttpStatus.OK);
	}

}
