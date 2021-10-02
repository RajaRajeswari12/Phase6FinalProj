package com.IDICBank.bankingService.security;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.IDICBank.bankingService.model.Users;
import com.IDICBank.bankingService.repositories.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Users> user = usersRepository.getUserByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("UserName " + username + " not found"));
		
		return user.map(AuthenticatedUser::new).get();
	}

//	private static List<? extends GrantedAuthority> getAuthorities(Optional<Users> user) {
//	
//		String[] userRoles = user.getRoles().stream().map((role)-> role.getRoleName()).toArray(String[]::new);
//		List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
//		
//		return authorities;
//		
//	}

}
