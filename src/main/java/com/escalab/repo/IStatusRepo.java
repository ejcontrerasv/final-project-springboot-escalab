package com.escalab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.model.Status;

public interface IStatusRepo extends JpaRepository<Status, Integer> {

}
