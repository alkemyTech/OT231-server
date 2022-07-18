package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteUserUseCase;
import com.alkemy.ong.application.service.usecase.IGetAuthDetailsUseCase;
import com.alkemy.ong.application.service.usecase.IListUserUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateUserUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.mapper.UserMapper;
import com.alkemy.ong.infrastructure.rest.mapper.UserRegisterMapper;
import com.alkemy.ong.infrastructure.rest.request.UpdateUserRequest;
import com.alkemy.ong.infrastructure.rest.request.UserRegisterRequest;
import com.alkemy.ong.infrastructure.rest.response.ErrorResponse;
import com.alkemy.ong.infrastructure.rest.response.ListUserResponse;
import com.alkemy.ong.infrastructure.rest.response.UserRegisterResponse;
import com.alkemy.ong.infrastructure.rest.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  private IGetAuthDetailsUseCase getAuthDetailsUseCase;

  @Autowired
  private IUpdateUserUseCase updateUserUseCase;

  @Autowired
  private UserRegisterMapper userRegisterMapper;

  @Autowired
  private UserMapper userMapper;


  @Operation(summary = "Register new user")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "201", description = "User created successfully",
                  content = { @Content(mediaType = "application/json",
                          schema = @Schema(implementation = UserRegisterResponse.class)) }),
          @ApiResponse(responseCode = "400", description = "Invalid input data",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(implementation = ErrorResponse.class))) })
  @PostMapping(value = "/auth/register",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<UserRegisterResponse> create(
      @Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody
              (description = "User Register", required = true,
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = UserRegisterRequest.class)))
                  UserRegisterRequest registerRequest) {
    User user = userRegisterMapper.toDomain(registerRequest);
    UserRegisterResponse response = userRegisterMapper.toResponse(createUserUseCase.add(user));
    return new ResponseEntity<UserRegisterResponse>(response, HttpStatus.CREATED);
  }

  @Operation(summary = "Get current user data")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Return user data successfully",
                  content = { @Content(mediaType = "application/json",
                          schema = @Schema(implementation = UserResponse.class)) }),
          @ApiResponse(responseCode = "403",
                  description = "Access denied. Please, try to login "
                          + "again or contact your admin.",
                  content = @Content(mediaType = "application/json",
                          schema = @Schema(implementation = ErrorResponse.class))) })
  @GetMapping(value = "/auth/me",
      produces = {"application/json"})
  public ResponseEntity<UserResponse> getAuthDetails(
      @RequestHeader("Authorization") String authorizationHeader) {
    User user = userMapper.toDomain(authorizationHeader);
    UserResponse response = userMapper.toResponse(getAuthDetailsUseCase.getAuthDetails(user));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/users/{id}", produces = {"application/json"})
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteUserUseCase.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/users", produces = {"application/json"})
  public ResponseEntity<ListUserResponse> list() {
    List<User> users = listUserUseCase.findAll();
    return ResponseEntity.ok().body(userMapper.toResponse(users));
  }

  @PutMapping(value = "/users/{id}",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<UserResponse> update(
      @PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
    User user = updateUserUseCase.update(userMapper.toDomain(id, updateUserRequest));
    UserResponse response = userMapper.toResponse(user);
    return ResponseEntity.ok(response);
  }

}
