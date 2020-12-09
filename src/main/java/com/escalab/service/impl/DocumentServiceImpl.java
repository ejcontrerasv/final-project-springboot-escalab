package com.escalab.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.escalab.dto.DocumentResumenDTO;
import com.escalab.model.Document;
import com.escalab.repo.IDocumentRepo;
import com.escalab.service.IDocumentService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class DocumentServiceImpl implements IDocumentService{
	
	@Autowired
	private IDocumentRepo repo;

	@Override
	public Document guardar(Document document) {
		return repo.save(document);
	}

	@Override
	public byte[] leerDocument(Integer idDocument) {
		Optional<Document> op = repo.findById(idDocument);
		return op.isPresent() ? op.get().getValue() : new byte[0];
	}
	
	@Override
	public Page<Document> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	@Override
	public List<Document> listarDocumentPorUsuario(Integer idUsuario) {
		return repo.listarDocumentPorUsuario(idUsuario);
	}

	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}
	
	@Override
	public Document modificar(Document document) {
		return repo.save(document);
	}
	
	@Override
	public List<DocumentResumenDTO> listarResumen() {
		List<DocumentResumenDTO> consultas = new ArrayList<>();
		
		repo.listarResumen().forEach(x -> {
			DocumentResumenDTO dr = new DocumentResumenDTO();
			dr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			dr.setTipoDocumento(Integer.parseInt(String.valueOf(x[1])));
			dr.setUsuario(Integer.parseInt(String.valueOf(x[2])));
			consultas.add(dr);
		});
		return consultas;
	}
	
	@Override
	public byte[] generarReporte() {
		byte[] data = null;
		
		try {
			File file = new ClassPathResource("/reports/documents.jasper").getFile();
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), null , new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@Override
	public Document leerPorId(Integer id) {
		Optional<Document> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Document();
	}
}
