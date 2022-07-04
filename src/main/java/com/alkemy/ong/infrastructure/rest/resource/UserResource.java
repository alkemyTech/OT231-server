package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.usecase.IGetUserUseCase;
import com.alkemy.ong.application.service.usecase.IListUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.mapper.UserMapper;
import com.alkemy.ong.infrastructure.rest.mapper.UserRegisterMapper;
import com.alkemy.ong.infrastructure.rest.request.UserRegisterRequest;
import com.alkemy.ong.infrastructure.rest.response.ListUserResponse;
import com.alkemy.ong.infrastructure.rest.response.UserRegisterResponse;
import com.alkemy.ong.infrastructure.rest.response.UserResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
  private IDeleteUserUseCase deleteUserUseCase;

  @Autowired
  private IListUserUseCase listUserUseCase;

  @Autowired
  private IGetUserUseCase getUserUseCase;

  @Autowired
  private UserRegisterMapper userRegisterMapper;

  @Autowired
  private UserMapper userMapper;

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
  public ResponseEntity<UserResponse> getDetails(
      @RequestHeader("Authorization") String authorizationHeader) {
    User user = userMapper.toDomain(authorizationHeader);
    UserResponse response = userMapper.toResponse(getUserUseCase.getDetails(user));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/users/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteUserUseCase.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/users", produces = {"application/json"})
  public ResponseEntity<ListUserResponse> list() {
    List<User> users = listUserUseCase.findAll();
    return ResponseEntity.ok().body(userMapper.toResponse(users));
  }

}
