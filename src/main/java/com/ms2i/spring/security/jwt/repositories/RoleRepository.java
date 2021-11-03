package com.ms2i.spring.security.jwt.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ms2i.spring.security.jwt.models.ERole;
import com.ms2i.spring.security.jwt.models.Role;

/**
 * 
 * Repository of the "Role" persistence objet
 * 
 * @author NOEL MAURICE
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	/**
	 * 
	 * The role is searched by its name
	 * 
	 * @param name Name of the role to find
	 * 
	 * @return The role found
	 * 
	 */
	Role findByName(ERole name);
	
	/**
	 * 
	 * All the roles sorted by name
	 * 	
	 * @return The roles found
	 * 
	 */
	@Query("select r from Role r order by r.name ASC")
    List<Role> findAllSortedByName();
	
	
	
}
