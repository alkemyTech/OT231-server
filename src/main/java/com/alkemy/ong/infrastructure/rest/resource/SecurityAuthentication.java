package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.infrastructure.config.spring.security.common.JwtUtils;
import com.alkemy.ong.infrastructure.database.repository.IUserSpringRepository;
import com.alkemy.ong.infrastructure.rest.response.JwtResponse;
import com.alkemy.ong.infrastructure.rest.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityAuthentication {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private IUserSpringRepository userSpringRepository;

  @Autowired
  private JwtUtils jwtTokenUtil;

  @PostMapping(value = "/auth/login ")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody UserResponse userResponse) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              userResponse.getEmail(), userResponse.getPassword()));
//no consegui entender el comentario del servicio,
// con mis compañeros me sugirieron los cambios que hice mas no estoy seguro de lo que me hablo.
    final UserDetails userDetails = userDetailsService
        .loadUserByUsername(userResponse.getEmail());
    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new JwtResponse(jwt));
  }
}
