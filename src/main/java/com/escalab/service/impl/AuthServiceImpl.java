package com.escalab.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {
	
	public boolean tieneAcceso(String path) {
		boolean rpta = false;
		
		String metodoRol = "";
		
		switch (path){
			case "listar":
			case "listarId":
				metodoRol = "ADMIN, USER, DBA";
				break;
			case "modificar":
				metodoRol = "ADMIN,DBA";
				break;
			case "eliminar":
				metodoRol = "ADMIN, DBA";
				break;
			case "registrar":
				metodoRol = "ADMIN, DBA, USER";
				break;
		}
		
		String metodoRoles[] = metodoRol.split(",");
		
		Authentication usuarioLogueado = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println(usuarioLogueado.getName());
		
		for (GrantedAuthority auth : usuarioLogueado.getAuthorities())  {
			String rolUser = auth.getAuthority();
			
			System.out.println(rolUser);
			
			for (String rolMet : metodoRoles) {
				if (rolUser.equalsIgnoreCase(rolMet)) {
					rpta = true;
				}
			}
		}
		
		return rpta;
	}

}
