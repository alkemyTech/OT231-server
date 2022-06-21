package com.alkemy.ong.infrastructure.config.spring.security.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

  private static final String SECRET_KEY = "SECRET";
  private static final String BEARER_TOKEN = "Bearer %s";
  private static final String AUTHORITIES = "authorities";
  private static final String BEARER_PART = "Bearer ";
  private static final String EMPTY = "";

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String authorizationHeader) {
    return Jwts.parser()
        .setSigningKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8))
        .parseClaimsJws(getToken(authorizationHeader))
        .getBody();
  }

  private String getToken(String authorizationHeader) {
    return authorizationHeader.replace(BEARER_PART, EMPTY);
  }

  public String generateToken(UserDetails userDetails) {
    String token = Jwts.builder()
        .claim(AUTHORITIES, getFirstAuthority(userDetails))
        .setSubject(userDetails.getUsername())
        .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
        .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(30).toInstant()))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes(StandardCharsets.UTF_8)).compact();
    return String.format(BEARER_TOKEN, token);
  }

  private String getFirstAuthority(UserDetails userDetails) {
    Optional<? extends GrantedAuthority> authority = userDetails.getAuthorities()
        .stream()
        .findFirst();

    if (authority.isEmpty()) {
      throw new IllegalArgumentException("User must have one authority.");
    }

    return authority.get().getAuthority();
  }

  public boolean isValidToken(String authorizationHeader) {
    return authorizationHeader != null && authorizationHeader.startsWith(BEARER_PART);
  }

  public List<GrantedAuthority> getAuthorities(String token) {
    return AuthorityUtils.commaSeparatedStringToAuthorityList(
        Objects.toString(extractAllClaims(token).get(AUTHORITIES)));
  }

}