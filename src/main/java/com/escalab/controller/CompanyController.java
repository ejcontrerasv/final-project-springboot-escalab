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
import com.escalab.model.Company;
import com.escalab.service.ICompanyService;

@RestController
@RequestMapping("/api/v1/companies")
//@CrossOrigin
public class CompanyController {
	
	@Autowired
	private ICompanyService service;
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	//@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DBA')")
	@GetMapping
	public ResponseEntity<List<Company>> listar(){
		List<Company> lista = service.listar();
		return new ResponseEntity<List<Company>>(lista, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listarId')")
	@GetMapping("/{id}")
	public ResponseEntity<Company> listarPorId(@PathVariable("id") Integer id){
		Company company = service.leerPorId(id);
		if (company.getIdCompany() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO [" + id + "]");
		}
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}
	
	//nivel 1
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	@GetMapping("/pageable")
	public ResponseEntity<Page<Company>> listarCompany(Pageable pageable){
		Page<Company> companies = service.listarPageable(pageable);
		return new ResponseEntity<Page<Company>>(companies, HttpStatus.OK);
	}
	
	//nivel 2
	@PreAuthorize("@authServiceImpl.tieneAcceso('registrar')")
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Company company){
		Company comp = service.registrar(company);
		// companies/4
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(comp.getIdCompany())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('modificar')")
	@PutMapping
	public ResponseEntity<Company> modificar(@Valid @RequestBody Company company){
		Company comp = service.modificar(company);
		return new ResponseEntity<Company>(comp, HttpStatus.OK);
	}
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('eliminar')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
		Company comp = service.leerPorId(id);
		if (comp.getIdCompany() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO [" + id + "]");
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
