package com.alkemy.ong.infrastructure.config.spring.security.models;

public class AuthenticationResponse {

  private final String jwt;

  public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
  }

  public String getJwt() {
        return jwt;
  }
}
