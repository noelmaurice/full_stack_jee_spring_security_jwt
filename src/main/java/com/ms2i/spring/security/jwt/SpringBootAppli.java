package com.ms2i.spring.security.jwt;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ms2i.spring.security.jwt.models.ERole;
import com.ms2i.spring.security.jwt.models.Role;
import com.ms2i.spring.security.jwt.models.User;
import com.ms2i.spring.security.jwt.repositories.RoleRepository;
import com.ms2i.spring.security.jwt.repositories.UserRepository;

/**
 *
 * The main class of the application
 *
 * @author NOEL MAURICE
 *
 */
@SpringBootApplication
public class SpringBootAppli {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	private static final Logger logger = LoggerFactory.getLogger(SpringBootAppli.class);


	/**
	 *
	 * Main method
	 *
	 * @param args The optional arguments for launching the application
	 *
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(SpringBootAppli.class, args);

	    logger.info("Application started");
	}


	/**
	 *
	 * The database is populated
	 *
	 */
	@PostConstruct
    @Transactional
    void initData()
	{
		Role roleUser = new Role(null, ERole.ROLE_USER);
		roleUser = roleRepository.save(roleUser);

		Role roleMod = new Role(null, ERole.ROLE_MOD);
		roleMod = roleRepository.save(roleMod);

		Role roleAdmin = new Role(null, ERole.ROLE_ADMIN);
		roleAdmin = roleRepository.save(roleAdmin);

		User userAdmin = new User("admin", "admin@admin.fr", encoder.encode("admin"));

		Set<Role> adminUserRoles = new HashSet<>();
		adminUserRoles.add(roleUser);
		adminUserRoles.add(roleAdmin);

		userAdmin.setRoles(adminUserRoles);
		userRepository.save(userAdmin);

		User userModerator = new User("mod", "mod@mod.fr", encoder.encode("mod"));

		Set<Role> userModeratorRoles = new HashSet<>();
		userModeratorRoles.add(roleUser);
		userModeratorRoles.add(roleMod);

		userModerator.setRoles(userModeratorRoles);
		userRepository.save(userModerator);
	}

}
