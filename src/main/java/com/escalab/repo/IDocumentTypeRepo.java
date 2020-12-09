package com.escalab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.model.DocumentType;

public interface IDocumentTypeRepo extends JpaRepository<DocumentType, Integer>{

}
