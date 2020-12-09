package com.escalab.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.escalab.model.Document;

public interface IDocumentRepo extends JpaRepository<Document, Integer> {
	
	@Query("from Document d where d.documentType.idType = :idDocumentType and d.status.idStatus = :idStatus")
	List<Document> listarDocumentPorTypeAndUserId(@Param("idDocumentType") Integer idDocumentType, @Param("idStatus") Integer idStatus);
	
	@Query("from Document d where d.usuario.idUsuario = :idUsuario")
	List<Document> listarDocumentPorUsuario(@Param("idUsuario") Integer idUsuario);
	
	@Query(value = "select * from fn_listarResumen()", nativeQuery = true)
	List<Object[]> listarResumen();
}
