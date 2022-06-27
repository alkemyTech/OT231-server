package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.AuthenticationService;
import com.alkemy.ong.application.service.CategoryService;
import com.alkemy.ong.application.service.NewsService;
import com.alkemy.ong.application.service.UserService;
import com.alkemy.ong.application.service.usecase.ICreateUserUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
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
  public ICreateUserUseCase createUserService(UserRepository userRepository) {
    return new UserService(userRepository);
  }

  @Bean
  public IDeleteCategoryUseCase deleteCategoryUseCase(ICategoryRepository categoryRepository) {
    return new CategoryService(categoryRepository);
  }

  @Bean
  public IDeleteNewsUseCase deleteNewsService(INewsRepository newsRepository) {
    return new NewsService(newsRepository);
  }
}
