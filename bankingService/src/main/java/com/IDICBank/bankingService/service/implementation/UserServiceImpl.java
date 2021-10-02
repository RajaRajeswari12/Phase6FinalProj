package com.IDICBank.bankingService.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.IDICBank.bankingService.model.Users;
import com.IDICBank.bankingService.repositories.UsersRepository;
import com.IDICBank.bankingService.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UsersRepository UsersRepository;

	@Override
	public Users findByUserName(String username) throws UsernameNotFoundException{
		
		Users user = UsersRepository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Not Found "+username));
		
		return user;
	}

}
