package com.IDICBank.bankingService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IDICBank.bankingService.model.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>{
	
	

}
