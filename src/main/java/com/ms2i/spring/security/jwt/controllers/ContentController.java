package com.ms2i.spring.security.jwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms2i.spring.security.jwt.controllers.payload.response.MessageResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * Each method returns content by Http response if the user role is correct
 * 
 * @author NOEL MAURICE
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/content")
@Tag(name = "ContentController", description = "Content provider")
public class ContentController 
{
	
	/**
	 * 
	 * The content for all users is returned
	 * 
	 * @return The text content
	 */
	@GetMapping("/public")
	public ResponseEntity<MessageResponse> publicContent() 
	{
		return ResponseEntity.ok(new MessageResponse("Public content sent by the serveur."));
	}
	
	/**
	 * 
	 * The content for user with "user" role is returned
	 * 
	 * @return The text content
	 */
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> userContent() 
	{
		return ResponseEntity.ok(new MessageResponse("User content sent by the serveur."));
	}

	/**
	 * 
	 * The content for user with "moderator" role is returned
	 * 
	 * @return The text content
	 */
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MOD')")
	public ResponseEntity<MessageResponse> moderatorContent() 
	{
		return ResponseEntity.ok(new MessageResponse("Moderator content sent by the serveur."));
	}

	/**
	 * 
	 * The content for user with "admin" role is returned
	 * 
	 * @return The text content
	 */
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> adminContent() 
	{
		return ResponseEntity.ok(new MessageResponse("Admin content sent by the serveur."));
	}
}
