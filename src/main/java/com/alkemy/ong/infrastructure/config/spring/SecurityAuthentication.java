package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.infrastructure.config.spring.security.common.JwtUtils;
import com.alkemy.ong.infrastructure.config.spring.models.AuthenticationResponse;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class SecurityAuthentication {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtUtils jwtTokenUtil;

  @PostMapping(value = "/auth/login ")
    public ResponseEntity<?> createAuthenticationToken(@Request UserEntity userEntity)
      {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              userEntity.getEmail(), userEntity.getPassword()));


    final UserDetails userDetails = userDetailsService
        .loadUserByUsername(userEntity.getEmail());
    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }
}
