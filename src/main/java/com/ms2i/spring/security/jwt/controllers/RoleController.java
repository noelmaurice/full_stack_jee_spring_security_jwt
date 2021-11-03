package com.ms2i.spring.security.jwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms2i.spring.security.jwt.models.Role;
import com.ms2i.spring.security.jwt.services.RoleService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * Role API services
 * 
 * @author NOEL MAURICE
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/role")
@Tag(name = "RoleController", description = "Role management")
public class RoleController {

	@Autowired
	RoleService roleService;

	/**
	 * 
	 * All the role name are returned
	 * 
	 * @return The role types unduplicated and sorted by name
	 * 
	 */
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Role>> getAll() {

		return ResponseEntity.ok(roleService.findAllSortedByName());
	}

}
