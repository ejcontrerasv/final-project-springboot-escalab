package com.escalab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.escalab.model.Usuario;

public interface IUsuarioService  extends ICRUD<Usuario>{
	
	Page<Usuario> listarPageable(Pageable pageable);
}
