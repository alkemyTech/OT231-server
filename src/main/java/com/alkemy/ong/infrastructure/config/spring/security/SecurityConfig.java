package com.alkemy.ong.infrastructure.config.spring.security;

import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import com.alkemy.ong.infrastructure.config.spring.security.filter.CustomAccessDeniedHandler;
import com.alkemy.ong.infrastructure.config.spring.security.filter.CustomAuthenticationEntryPoint;
import com.alkemy.ong.infrastructure.config.spring.security.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new CustomAuthenticationEntryPoint();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
    managerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .cors()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/api/docs")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/auth/login", "/auth/register")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/auth/me")
        .hasAnyRole(Role.USER.name())
        .antMatchers(HttpMethod.POST, "/contacts")
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.POST, "/categories")
        .hasAnyRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/organization/public")
        .permitAll()
        .antMatchers(HttpMethod.PATCH, "/organization/public")
        .hasAnyRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/categories/{id:[\\d+]}")
        .hasAnyRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "testimonials/{id:[\\d+]}")
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.POST, "/testimonials")
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.DELETE, "/news/{id:[\\d+]}")
        .hasAnyRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.POST, "/news")
        .hasAnyRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/news/{id:[\\d+]}")
        .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/members/{id:[\\d+]}")
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "users/{id:[\\d+]}")
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.PUT, "/users/{id:[\\d+]}")
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.GET, "/slides")
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/users")
        .hasAnyRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/categories")
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.POST, "/comments")
        .hasRole(Role.USER.name())
        .antMatchers(HttpMethod.DELETE, "/comments/{id:[\\d+]}")
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.PATCH, "/comments/{id:[\\d+]}")
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.DELETE, "/slides/{id:[\\d+]}")
        .hasRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/slides/{id:[\\d+]}")
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.GET, "/categories/{id:[\\d+]}")
        .hasAnyRole(Role.ADMIN.name(), Role.USER.name())
        .antMatchers(HttpMethod.POST, "/activities")
        .hasAnyRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/contacts")
        .hasAnyRole(Role.ADMIN.name())
        .antMatchers(HttpMethod.PUT, "/activities/{id:[\\d+]}")
        .hasAnyRole(Role.ADMIN.name())
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler())
        .authenticationEntryPoint(authenticationEntryPoint());
  }

}

