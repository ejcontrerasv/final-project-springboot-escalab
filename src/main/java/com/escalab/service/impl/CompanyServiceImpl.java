package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.escalab.model.Company;
import com.escalab.repo.ICompanyRepo;
import com.escalab.service.ICompanyService;

@Service
public class CompanyServiceImpl implements ICompanyService{
	
	@Autowired
	private ICompanyRepo repo;
	
	@Override
	public Company registrar(Company obj) {
		return repo.save(obj);
	}
	
	@Override
	public Company modificar(Company obj) {
		return repo.save(obj);
	}
	
	@Override
	public List<Company> listar() {
		return repo.findAll();
	}
	
	@Override
	public Company leerPorId(Integer id) {
		Optional<Company> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Company();
	}
	
	@Override
	public Page<Company> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

}
