package com.ms2i.spring.security.jwt.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ms2i.spring.security.jwt.security.userdetails.UserDetailsServiceImpl;

/**
 * 
 * 
 * 
 * @author NOEL MAURICE
 *
 */
public class AuthTokenFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
  
  public final static String TOKEN_PREFIX = "Bearer ";
  public final static String TOKEN_HEADER_NAME = "Authorization";
	
  /**
   * Provides HttpServletRequest and HttpServletResponse arguments instead of the 
   * default ServletRequest and ServletResponse ones
   * 
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      String jwt = jwtHeaderRequestToString(request);
      if (jwt != null && jwtUtils.validateToken(jwt)) {
        String username = jwtUtils.getUserNameFromToken(jwt);

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
            userDetails.getAuthorities());
        
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } 
    catch (Exception e) 
    {
      logger.error("Cannot set user authentication: {}", e);
    }

    filterChain.doFilter(request, response);
  }

  
  /**
   * 
   * parse a JWT token store in the request header to a string
   * 
   * @param request Request containing JWT token in its header
   * 
   * @return The JWT token parse to string
   * 
   */
  private String jwtHeaderRequestToString(HttpServletRequest request) {
	  
	String tokenPrefix = AuthTokenFilter.TOKEN_PREFIX;
    String headerAuth = request.getHeader(AuthTokenFilter.TOKEN_HEADER_NAME);

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(tokenPrefix)) {
      return headerAuth.substring(tokenPrefix.length(), headerAuth.length());
    }

    return null;
  }
}
