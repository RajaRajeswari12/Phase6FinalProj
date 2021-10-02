package com.IDICBank.bankingService.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IDICBank.bankingService.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
	@Transactional
	Optional<Users> getUserByUsername(String username);
}
