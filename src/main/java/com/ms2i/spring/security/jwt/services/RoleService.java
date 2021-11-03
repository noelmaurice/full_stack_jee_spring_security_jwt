package com.ms2i.spring.security.jwt.services;

import java.util.List;

import com.ms2i.spring.security.jwt.models.ERole;
import com.ms2i.spring.security.jwt.models.Role;

/**
 * The role services
 * 
 * @author NOEL MAURICE
 *
 */
public interface RoleService {
	
	/**
	 * 
	 * Returns the role searched
	 * 
	 * @param name of the role to find
	 * 
	 * @return The role found
	 */
	public Role findByName(ERole name);
	
	/**
	 * 
	 * Returns all the roles
	 * 
	 * @return All the roles sorted by name
	 */
	public List<Role> findAllSortedByName();

}
