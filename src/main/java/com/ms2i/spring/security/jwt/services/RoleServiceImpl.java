package com.ms2i.spring.security.jwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms2i.spring.security.jwt.models.ERole;
import com.ms2i.spring.security.jwt.models.Role;
import com.ms2i.spring.security.jwt.repositories.RoleRepository;

/**
 * 
 * Implement the RoleService interface
 * See : RoleService
 * 
 * @author NOEL MAURICE
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	@Override
	public Role findByName(ERole name) {
		return roleRepository.findByName(name);
	}

	@Override
	public List<Role> findAllSortedByName() {
		return roleRepository.findAllSortedByName();
	}

}
