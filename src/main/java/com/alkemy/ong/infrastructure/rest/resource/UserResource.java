package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.mapper.UserMapper;
import com.alkemy.ong.infrastructure.rest.request.UserRegisterRequest;
import com.alkemy.ong.infrastructure.rest.response.UserRegisterResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {

  @Autowired
  private ICreateUserUseCase createUserUseCase;

  @Autowired
  private UserMapper userMapper;

  @PostMapping(value = "auth/register", produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<UserRegisterResponse> createUser(@Valid @RequestBody UserRegisterRequest user) {
    User newUser = userMapper.toDomain(user);
    UserRegisterResponse response = userMapper.toResponse(createUserUseCase.addUser(newUser));
    return new ResponseEntity<UserRegisterResponse>(response, HttpStatus.CREATED);
  }

}
