package com.ms2i.spring.security.jwt.controllers;


import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms2i.spring.security.jwt.models.ERole;
import com.ms2i.spring.security.jwt.models.Role;
import com.ms2i.spring.security.jwt.models.User;
import com.ms2i.spring.security.jwt.controllers.payload.request.LoginRequest;
import com.ms2i.spring.security.jwt.controllers.payload.request.SignupRequest;
import com.ms2i.spring.security.jwt.controllers.payload.response.JwtResponse;
import com.ms2i.spring.security.jwt.controllers.payload.response.MessageResponse;

import com.ms2i.spring.security.jwt.security.jwt.JwtUtils;
import com.ms2i.spring.security.jwt.security.userdetails.UserDetailsImpl;
import com.ms2i.spring.security.jwt.services.UserService;
import com.ms2i.spring.security.jwt.services.RoleService;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Signin and signout http methods
 * 
 * @author NOEL MAURICE
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@Tag(name = "AuthController", description = "Authentication services")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * 
	 * Http method for identicating user
	 * 
	 * @param loginRequest User parameters
	 * 
	 * @return JWT http response
	 */
	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	/**
	 * 
	 * Http method for register user
	 * 
	 * @param signUpRequest User parameters
	 * 
	 * @return Http response
	 */
	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userService.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userService.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		saveUser(signUpRequest);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
	
	/**
	 * 
	 * User object saved
	 * 
	 * @param signUpRequest Http request object
	 * 
	 */
	private void saveUser(SignupRequest signUpRequest)
	{
		User signupUser = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));
	
		Set<String> signUpRequestRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
	
		if (signUpRequestRoles == null) {
			Role userRole = roleService.findByName(ERole.ROLE_USER);
			
			if(userRole == null)
			{
				throw new RuntimeException("Error: Role is not found.");
			}
			else
			{
				roles.add(userRole);
			}
		} 
		else 
		{
			for (String role : signUpRequestRoles) 
			{
				if (role.equals(ERole.ROLE_ADMIN.name()))
				{
					Role adminRole = roleService.findByName(ERole.ROLE_ADMIN);
					
					if(adminRole == null)
					{
						throw new RuntimeException("Error: Role is not found.");
					}
					else
					{
						roles.add(adminRole);
					}
				}
				else if (role.equals(ERole.ROLE_MOD.name()))
				{
					Role modRole = roleService.findByName(ERole.ROLE_MOD);
					
					if (modRole == null)
					{
						throw new RuntimeException("Error: Role is not found.");
					}
					else
					{
						roles.add(modRole);
					}		
				}
				else
				{
					Role userRole = roleService.findByName(ERole.ROLE_USER);
					
					if (userRole == null)
					{
						throw new RuntimeException("Error: Role is not found.");
					}
					else
					{
						roles.add(userRole);
					}
				}
			}
		}
	
		signupUser.setRoles(roles);
		userService.save(signupUser);
	}
}
