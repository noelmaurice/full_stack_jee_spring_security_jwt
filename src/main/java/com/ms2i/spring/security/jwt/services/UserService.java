package com.ms2i.spring.security.jwt.services;


import java.util.List;

import com.ms2i.spring.security.jwt.models.ERole;
import com.ms2i.spring.security.jwt.models.User;

/**
 * 
 * User services
 * 
 * @author NOEL MAURICE
 *
 */
public interface UserService
{
	/**
	 * Save a user
	 * 
	 * @param user User to save
	 * 
	 * @return The user saved with its id
	 * 
	 */
	public User save(User user);
	
	/**
	 * 
	 * Find a user by username
	 * 
	 * @param username Username of the user to find
	 * 
	 * @return The user found
	 * 
	 */
	public User findByUsername(String username);
	
	/**
	 * 
	 * Returns all the users
	 * 
	 * @return All the users
	 * 
	 */
	public List<User> findAll();
	
	/**
	 * 
	 * Checks if the username exists
	 * 
	 * @param username Username to check
	 * 
	 * @return True if username exists, false otherwise
	 * 
	 */
	public boolean existsByUsername(String username);
	
	/**
	 * 
	 * Checks if the email exists
	 * 
	 * @param email Email to check
	 * 
	 * @return True if email exists, false otherwise
	 * 
	 */
	public boolean existsByEmail(String email);
	
	/**
	 * 
	 * Returns all the users with the role name
	 * 
	 * @param roleName Role name to find for the users
	 * 
	 * @return All the users with the role name
	 * 
	 */
	public List<User> findAllByRoleName(ERole roleName);
}
