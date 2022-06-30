package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.usecase.IGetUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.mapper.AuthenticationMapper;
import com.alkemy.ong.infrastructure.rest.mapper.UserRegisterMapper;
import com.alkemy.ong.infrastructure.rest.request.UserRegisterRequest;
import com.alkemy.ong.infrastructure.rest.response.AuthenticationResponse;
import com.alkemy.ong.infrastructure.rest.response.UserRegisterResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

  @Autowired
  private ICreateUserUseCase createUserUseCase;

  @Autowired
  private UserRegisterMapper userRegisterMapper;

  @Autowired
  private IDeleteUserUseCase deleteUserUseCase;
  
  @Autowired
  private IGetUserUseCase getUserUseCase;
  
  @Autowired
  private AuthenticationMapper authenticationMapper;

  @PostMapping(value = "/auth/register",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<UserRegisterResponse> create(
      @Valid @RequestBody UserRegisterRequest registerRequest) {
    User user = userRegisterMapper.toDomain(registerRequest);
    UserRegisterResponse response = userRegisterMapper.toResponse(createUserUseCase.add(user));
    return new ResponseEntity<UserRegisterResponse>(response, HttpStatus.CREATED);
  }
  
  @GetMapping(value = "/auth/me", 
      produces = {"application/json"})
  public ResponseEntity<AuthenticationResponse> getDetails(@RequestHeader HttpHeaders headers) {
    String jwt = headers.getFirst("Authorization");
    AuthenticationResponse response = authenticationMapper.toResponse(getUserUseCase.getDetails(jwt));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/users/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteUserUseCase.delete(id);
    return ResponseEntity.noContent().build();
  }

}
