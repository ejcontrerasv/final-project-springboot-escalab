package com.escalab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.escalab.model.Company;

public interface ICompanyService extends ICRUD<Company>{
	
	Page<Company> listarPageable(Pageable pageable);
}
