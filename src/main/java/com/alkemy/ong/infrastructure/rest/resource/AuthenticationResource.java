package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ILoginUseCase;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.mapper.AuthenticationMapper;
import com.alkemy.ong.infrastructure.rest.request.AuthenticationRequest;
import com.alkemy.ong.infrastructure.rest.response.AuthenticationResponse;
import com.alkemy.ong.infrastructure.rest.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationResource {

  @Autowired
  private ILoginUseCase loginUseCase;

  @Autowired
  private AuthenticationMapper authenticationMapper;


  @Operation(summary = "User login")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User logged successfully",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = AuthenticationResponse.class))}),
      @ApiResponse(responseCode = "400", description = "Invalid input data",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorResponse.class)))})
  @PostMapping(value = "/auth/login",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<AuthenticationResponse> login(
      @RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody
          (description = "User login", required = true,
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = AuthenticationRequest.class)))
      AuthenticationRequest authenticationRequest) {
    User user = loginUseCase.login(authenticationMapper.toDomain(authenticationRequest));
    return ResponseEntity.ok().body(authenticationMapper.toResponse(user));
  }

}
