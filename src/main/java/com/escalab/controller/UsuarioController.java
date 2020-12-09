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
import com.escalab.model.Usuario;
import com.escalab.service.IUsuarioService;

@RestController
@RequestMapping("/api/v1/users")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService service;
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	//@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DBA')")
	@GetMapping
	public ResponseEntity<List<Usuario>> listar(){
		List<Usuario> lista = service.listar();
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarId')")
	@GetMapping("/list/{id}")
	public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Integer id){
		Usuario user = service.leerPorId(id);
		if (user.getIdUsuario() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO [" + id + "]");
		}
		return new ResponseEntity<Usuario>(user, HttpStatus.OK);
	}
	
	//nivel 1
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	@GetMapping("/pageable")
	public ResponseEntity<Page<Usuario>> listarUsuarios(Pageable pageable){
		Page<Usuario> users = service.listarPageable(pageable);
		return new ResponseEntity<Page<Usuario>>(users, HttpStatus.OK);
	}
	
	//nivel 2
	@PreAuthorize("@authServiceImpl.tieneAcceso('registrar')")
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Usuario user){
		Usuario us = service.registrar(user);
		// companies/4
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(us.getIdUsuario())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('modificar')")
	@PutMapping
	public ResponseEntity<Usuario> modificar(@Valid @RequestBody Usuario user){
		Usuario us = service.modificar(user);
		return new ResponseEntity<Usuario>(us, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('eliminar')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Usuario user = service.leerPorId(id);
		if (user.getIdUsuario() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO [" + id + "]");
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
