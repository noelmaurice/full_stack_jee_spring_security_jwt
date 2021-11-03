package com.ms2i.spring.security.jwt.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.ms2i.spring.security.jwt.security.userdetails.UserDetailsImpl;

import io.jsonwebtoken.*;

/**
 * 
 * Useful methods for managing JWT Tokens
 * 
 * @author NOEL MAURICE
 *
 */
@Component
public class JwtUtils 
{
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

  @Value("${com.ms2i.app.jwtSecret}")
  private String jwtSecret;

  @Value("${com.ms2i.app.jwtExpirationMs}")
  private int jwtExpirationMs;

  /**
   * 
   * Generates a JWT token
   * 
   * @param authentication Principal authentication
   * 
   * @return The JWT token generated
   */
  public String generateJwtToken(Authentication authentication) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  /**
   * 
   * The username in the jwt subject token is returned 
   * 
   * @param token containing the username information
   * 
   * @return The username
   * 
   */
  public String getUserNameFromToken(String token) {
	  
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }

  /**
   * 
   * Checks if the token is on accordance with the secret sentence
   * 
   * @param authToken JWT token to check
   * 
   * @return The check status
   */
  public boolean validateToken(String authToken) {
    try 
    {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }

    return false;
  }
}
