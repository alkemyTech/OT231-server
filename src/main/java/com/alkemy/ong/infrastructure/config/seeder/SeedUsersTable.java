package com.alkemy.ong.infrastructure.config.seeder;

import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import com.alkemy.ong.infrastructure.database.entity.RoleEntity;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.repository.spring.IRoleSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.IUserSpringRepository;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log
@Configuration
public class SeedUsersTable {
  
  private static final String ADMIN_EMAIL_I = "jason@voorhees.com";
  private static final String ADMIN_EMAIL_II = "freddy@krueger.com";
  private static final String ADMIN_EMAIL_III = "michael@myers.com";
  private static final String ADMIN_EMAIL_IV = "pamela@voorhees.com";
  private static final String ADMIN_EMAIL_V = "leather@face.com";
  private static final String USER_EMAIL_I = "mark@facebook.com";
  private static final String USER_EMAIL_II = "bill@windows.com";
  private static final String USER_EMAIL_III = "jeff@amazon.com";
  private static final String USER_EMAIL_IV = "elon@tesla.com";
  private static final String USER_EMAIL_V = "jack@twitter.com";
  private static final String PASSWORD = "abcd1234";
  
  @Autowired
  protected PasswordEncoder passwordEncoder;

  @Autowired
  protected IUserSpringRepository userRepository;

  @Autowired
  protected IRoleSpringRepository roleRepository;
  
  @Bean
  CommandLineRunner initDatabase() {
    return args -> {
      log.info("Loading initial Roles in Database...");
      createRoles();
      log.info("Loading initial Users in Database...");
      createStandardAndAdminUsers();
    };
  }

  private void createRoles() {
    if (roleRepository.count() == 0) {
      roleRepository.saveAll(List.of(
          buildRole(Role.USER),
          buildRole(Role.ADMIN)));
      log.info("Initial Roles created");
    }
  }
  
  private void createStandardAndAdminUsers() {
    if (userRepository.count() == 0) {
      userRepository.saveAll(List.of(
          buildUser("Jason", "Voorhees", ADMIN_EMAIL_I, Role.ADMIN),
          buildUser("Freddy", "Krueger", ADMIN_EMAIL_II, Role.ADMIN),
          buildUser("Michael", "Myers", ADMIN_EMAIL_III, Role.ADMIN),
          buildUser("Pamela", "Voorhees", ADMIN_EMAIL_IV, Role.ADMIN),
          buildUser("Leatherface", "Unknown", ADMIN_EMAIL_V, Role.ADMIN),
          buildUser("Mark", "Zuckerberg", USER_EMAIL_I, Role.USER),
          buildUser("Bill", "Gates", USER_EMAIL_II, Role.USER),
          buildUser("Jeff", "Bezos", USER_EMAIL_III, Role.USER),
          buildUser("Elon", "Musk", USER_EMAIL_IV, Role.USER),
          buildUser("Jack", "Dorsey", USER_EMAIL_V, Role.USER)));
      log.info("Initial Users created");
    }
  }
  
  private RoleEntity buildRole(Role role) {
    return RoleEntity.builder()
         .description(role.name())
         .name(role.getFullRoleName())
         .build();
  }
  
  private UserEntity buildUser(String firstName, String lastName, String email, Role role) {
    return UserEntity.builder()
        .firstName(firstName)
        .lastName(lastName)
        .email(email)
        .password(passwordEncoder.encode(PASSWORD))
        .role(roleRepository.findByName(role.getFullRoleName()))
        .softDelete(false)
        .build();
  }
}
