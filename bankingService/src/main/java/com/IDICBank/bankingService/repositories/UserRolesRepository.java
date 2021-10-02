package com.IDICBank.bankingService.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.IDICBank.bankingService.model.UserRole;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {
	
	@Query("select r.roleName from UserRole a, Users b,Roles r where b.username=?1 and a.userid=b.id and a.roleid=r.id")
    public List<String> findRoleByUserName(String username);
	
}