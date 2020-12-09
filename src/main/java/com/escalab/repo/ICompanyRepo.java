package com.escalab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.model.Company;

public interface ICompanyRepo extends JpaRepository<Company, Integer>{

}
