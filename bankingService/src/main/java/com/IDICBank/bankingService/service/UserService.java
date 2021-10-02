package com.IDICBank.bankingService.service;

import com.IDICBank.bankingService.model.Users;

public interface UserService {
	
	Users findByUserName(String username);

}
