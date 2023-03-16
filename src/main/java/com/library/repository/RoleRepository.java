package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.Entity.Role;
import com.library.Entity.Roles;


public interface RoleRepository  extends JpaRepository<Role,Integer>
{
	 Optional<Role> findByName(Roles roleName);

}
