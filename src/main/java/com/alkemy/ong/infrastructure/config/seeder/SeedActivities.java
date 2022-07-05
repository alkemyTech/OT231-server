package com.alkemy.ong.infrastructure.config.seeder;

import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.alkemy.ong.infrastructure.database.repository.spring.IActivitySpringRepository;
import java.util.List;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Log
@Configuration
@Profile("default")
public class SeedActivities {

  private static final String IMAGE_I = "https://aws.s3.com/juanito.png";
  private static final String IMAGE_II = "https://aws.s3.com/juanAlCuadrado.jpg";
  private static final String IMAGE_III = "https://aws.s3.com/magaliArribaEspaña.jpg";

  @Autowired
  protected IActivitySpringRepository activitySpringRepository;

  @Bean
  CommandLineRunner initDatabase() {
    return args -> {
      log.info("Loading initial Activities in Database...");
      createStandardActivities();
    };
  }

  private void createStandardActivities() {
    if (activitySpringRepository.count() == 0) {
      activitySpringRepository.saveAll(List.of(
          buildActivity("Fix the tree's", "More tree's", IMAGE_I),
          buildActivity("Create more donation houses", "More donation and more houses", IMAGE_II),
          buildActivity("Integrate public food donation mail", "Food's", IMAGE_III)));
      log.info("Initial Activities Created");
    }
  }

  private ActivityEntity buildActivity(String name, String content, String image) {
    return ActivityEntity.builder()
        .name(name)
        .content(content)
        .image(image)
        .build();
  }

}
