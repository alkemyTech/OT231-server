package com.alkemy.ong.infrastructure.config.spring;

import com.alkemy.ong.application.service.AuthenticationService;
import com.alkemy.ong.application.service.OrganizationPublicDataService;
import com.alkemy.ong.application.service.usecase.ILoginUseCase;
import com.alkemy.ong.application.service.usecase.IOrganizationPublicDataUseCase;
import com.alkemy.ong.infrastructure.database.repository.OrganizationRepository;
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
  public IOrganizationPublicDataUseCase organizationPublicDataUseCase(
          OrganizationRepository organizationRepository) {
    return new OrganizationPublicDataService(organizationRepository);
  }
}
