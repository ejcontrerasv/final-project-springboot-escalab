package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.escalab.model.DocumentType;
import com.escalab.repo.IDocumentTypeRepo;
import com.escalab.service.IDocumentTypeService;

@Service
public class DocumentTypeServiceImpl implements IDocumentTypeService{
	
	@Autowired
	private IDocumentTypeRepo repo;
	
	@Override
	public DocumentType registrar(DocumentType obj) {
		return repo.save(obj);
	}
	
	@Override
	public DocumentType modificar(DocumentType obj) {
		return repo.save(obj);
	}
	
	@Override
	public List<DocumentType> listar() {
		return repo.findAll();
	}
	
	@Override
	public DocumentType leerPorId(Integer id) {
		Optional<DocumentType> op = repo.findById(id);
		return op.isPresent() ? op.get() : new DocumentType();
	}
	
	@Override
	public Page<DocumentType> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}
	
}
