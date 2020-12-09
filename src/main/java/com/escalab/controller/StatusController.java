package com.escalab.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import com.escalab.model.Status;
import com.escalab.service.IStatusService;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {
	
	@Autowired
	private IStatusService service;
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Status>> listar(){
		List<Status> lista = service.listar();
		return new ResponseEntity<List<Status>>(lista,HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('registrar')")
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Status status){
		Status obj = service.registrar(status);
		//status/1
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getIdStatus())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('modificar')")
	@PutMapping
	public ResponseEntity<Status> modificar(@Valid @RequestBody Status status){
		Status obj = service.modificar(status);
		return new ResponseEntity<Status>(obj, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('eliminar')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Status obj = service.leerPorId(id);
		if (obj.getIdStatus() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: [" + id +"]");
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
