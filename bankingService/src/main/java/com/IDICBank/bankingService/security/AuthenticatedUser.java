package com.IDICBank.bankingService.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.IDICBank.bankingService.model.Roles;
import com.IDICBank.bankingService.model.Users;

public class AuthenticatedUser implements UserDetails
{

	private static final long serialVersionUID = 1L;
	private Users user;
	private String userName;
	
	private String password;
	private boolean enabled;
//	private List<GrantedAuthority> authorities;
	
	public AuthenticatedUser(Users user)
	{
		
		this.userName = user.getUsername();
		this.password = user.getPassword();
		this.enabled = user.isEnabled();
//		this.authorities = user.getRoles().;
		//super(user.getUsername(), user.getPassword(), getAuthorities(user));
		this.user = user;
		
		
	}
	
	public AuthenticatedUser() {
		
	}
	
	public Users getUser()
	{
		System.out.println("wwww "+user.toString());
		return user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<String> roleAndPermissions = new HashSet<>();
		List<Roles> roles = user.getRoles();
		
		for (Roles role : roles)
		{
			roleAndPermissions.add(role.getRoleName());
		}
		String[] roleNames = new String[roleAndPermissions.size()];
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(roleAndPermissions.toArray(roleNames));
		System.out.println("AUTHORITIES +++++++++++ ");
		authorities.forEach(s-> System.out.println(s));
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
}

