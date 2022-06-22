package com.alkemy.ong.infrastructure.config.spring.security.models;

public class AuthenticationResponse {

  private final Srtrin jwt;

  public AuthenticationResponse(Srtrin jwt) {
        this.jwt = jwt;
  }

  public Srtrin getJwt() {
        return jwt;
  }
}
