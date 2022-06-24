package com.alkemy.ong.infrastructure.rest.resource;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.mapper.UserMapper;
import com.alkemy.ong.infrastructure.rest.request.UserRequest;
import com.alkemy.ong.infrastructure.rest.response.UserResponse;

@RestController
@RequestMapping("auth")
public class UserResource {

  @Autowired
  private ICreateUserUseCase createUserUseCase;

  @Autowired
  private UserMapper mapper;

  @PostMapping("/register")
  public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest user) {
    User newUser = createUserUseCase.addUser(mapper.toDomain(user));
    UserResponse response = mapper.toResponse(newUser);
    return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);
  }

}
