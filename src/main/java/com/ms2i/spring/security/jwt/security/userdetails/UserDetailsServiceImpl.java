package com.ms2i.spring.security.jwt.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms2i.spring.security.jwt.models.User;
import com.ms2i.spring.security.jwt.services.UserService;


/**
 * 
 * Service for the UserDetails managing
 * 
 * @author NOEL MAURICE
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  
	@Autowired
	UserService userService;
	

  /**
   * 
   * Load the user by username
   * 
   * @return UserDetails object
   * 
   */
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  
    User user = userService.findByUsername(username);
    
    if (user == null)
    {
        throw new UsernameNotFoundException("User Not Found with username: " + username);
    }
    
    return UserDetailsImpl.build(user);
  }

}
