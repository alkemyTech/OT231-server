package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.AuthenticationService;
import com.alkemy.ong.application.service.NewsService;
import com.alkemy.ong.application.service.usecase.IDeleteNewsUseCase;
import com.alkemy.ong.application.service.usecase.ILoginUseCase;
import com.alkemy.ong.infrastructure.database.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class ServiceBeanConfig {

  @Bean
  public ILoginUseCase authenticationService(AuthenticationManager authenticationManager,
      UserRepository userRepository) {
    return new AuthenticationService(authenticationManager, userRepository);
  }

  @Bean
  public IDeleteNewsUseCase deleteNewsService(INewsRepository newsRepository) {
    return new NewsService(newsRepository);
  }

}
