package com.escalab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.model.ResetToken;

public interface IResetTokenRepo extends JpaRepository<ResetToken, Integer>{
	
		//from reset_token where token = :?
		ResetToken findByToken(String token);
}
