package com.ms2i.spring.security.jwt.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ms2i.spring.security.jwt.models.ERole;
import com.ms2i.spring.security.jwt.models.User;

/**
 * 
 * Repository of the "User" persistence objet
 * 
 * @author NOEL MAURICE
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
	/**
	 * 
	 * The role is searched by its name
	 * 
	 * @param username Name of the role to find
	 * 
	 * @return The role found
	 * 
	 */
	User findByUsername(String username);	

	/**
	 * 
	 * Checks if the user username exists
	 * 
	 * @param username Username to verifie
	 * 
	 * @return The username status
	 * 
	 */
	Boolean existsByUsername(String username);

	/**
	 * 
	 * Checks if the user email exists
	 * 
	 * @param email Email to verifie
	 * 
	 * @return The email status
	 * 
	 */
	Boolean existsByEmail(String email);
	
	@Query("select u from User u join u.roles r where r.name = :rolename")
    List<User> findAllByRoleName(@Param("rolename") ERole rolename);
}
