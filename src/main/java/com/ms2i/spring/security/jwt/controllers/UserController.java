package com.ms2i.spring.security.jwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ms2i.spring.security.jwt.models.ERole;
import com.ms2i.spring.security.jwt.models.User;
import com.ms2i.spring.security.jwt.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * User API services
 * 
 * @author NOEL MAURICE
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "User management")
public class UserController {

	@Autowired
	UserService userService;

	
	/**
	 * Returns the user withe the username
	 * 
	 * @param username Username of the user to find
	 * 
	 * @return The user found
	 * 
	 */
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> getByUsername(@RequestParam("username") String username) {

		return ResponseEntity.ok(userService.findByUsername(username));
	}

	/**
	 * 
	 * All the users are returned
	 * 
	 * @return All the users
	 * 
	 */
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAll() {

		return ResponseEntity.ok(userService.findAll());
	}

	/**
	 * 
	 * All the user with the role name are returned
	 * 
	 * @return The users with the role name
	 */
	@GetMapping("/all/rolename")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllByRoleName(@RequestParam("rolename") ERole rolename) {
		List<User> users = userService.findAllByRoleName(rolename);
		return ResponseEntity.ok(users);
	}
}
