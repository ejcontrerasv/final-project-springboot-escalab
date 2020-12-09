package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.Status;
import com.escalab.repo.IStatusRepo;
import com.escalab.service.IStatusService;

@Service
public class StatusServiceImpl implements IStatusService {
	
	@Autowired
	private IStatusRepo repo;
	
	@Override
	public List<Status> listar() {
		return repo.findAll();
	}
	
	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}

	@Override
	public Status registrar(Status obj) {
		return repo.save(obj);
	}

	@Override
	public Status modificar(Status obj) {
		return repo.save(obj);
	}

	@Override
	public Status leerPorId(Integer id) {
		Optional<Status> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Status();
	}
}
