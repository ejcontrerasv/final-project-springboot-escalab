package com.escalab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.escalab.model.DocumentType;

public interface IDocumentTypeService extends ICRUD<DocumentType>{
	
	Page<DocumentType> listarPageable(Pageable pageable);
}
