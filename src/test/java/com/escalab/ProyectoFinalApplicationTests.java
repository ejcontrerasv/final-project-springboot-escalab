package com.escalab;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.escalab.model.Company;
import com.escalab.model.Status;
import com.escalab.model.Usuario;
import com.escalab.repo.ICompanyRepo;
import com.escalab.repo.IUsuarioRepo;
import com.escalab.service.ICompanyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProyectoFinalApplicationTests {

	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private IUsuarioRepo UserRepo;
	
	public Status crearStatus() {
		Status status = new Status();
		status.setIdStatus(1);
		status.setName("Activo");
		status.setDescription("Usuario Activo");
		
		return status;
	}
	
	public Company crearCompany() {
		Company company = new Company();
		company.setIdCompany(1);
		company.setRazonSocial("My Company Name");
		company.setTaxPayerId("77777777");
		company.setStatus(crearStatus());
		company.setDirection("Calle Sur #7564");
		company.setEmail("company@gmail.com");
		company.setTelephone("55555555");
		
		return company;
	}
	
	@Test
	public void CREARUSUARIO() {
		Usuario user = new Usuario();
		user.setIdUsuario(2);
		user.setUserName("smoreno");
		user.setEmail("sus@gmail.com");
		user.setRut("18612177-5");
		user.setPassword(bcrypt.encode("abc123"));
		user.setStatus(crearStatus());
		user.setCompany(crearCompany());
		
		Usuario returnUsuario = UserRepo.save(user);
		
		assertTrue(returnUsuario.getPassword().equalsIgnoreCase(user.getPassword()));
	}
	

}
