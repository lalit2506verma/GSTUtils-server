package com.GSTUtils.server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GSTUtils.server.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
}
