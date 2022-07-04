package com.alkemy.ong.infrastructure.config.seeder;

import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.alkemy.ong.infrastructure.database.repository.spring.IActivitySpringRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Log
@Configuration
@Profile("default")
public class SeedActivities {


  //If I not failing the bullet,
  // these constants we're created for in some case to use this images en other instances
  private static final String IMAGE_I = "juanito.png";

  private static final String IMAGE_II = "juanAlCuadrado.jpg";

  private static final String IMAGE_III = "magaliArribaEspaña.jpg";

  @Autowired
  protected IActivitySpringRepository activitySpringRepository;

  @Bean
  CommandLineRunner initDatabase() {
    return args -> {
      log.info("Loading initial Activities in Database...");
      createStandarActivity();
    };
  }

  private void createStandarActivity(){
if(activitySpringRepository.count() == 0){
  activitySpringRepository.saveAll(List.of(
          buildActivity("Fix the tree's", "More tree's" , IMAGE_I),
          buildActivity("Create mor donation houses", "More donation and more houses" , IMAGE_II),
          buildActivity("Integrate public food donation mail", "Food's" , IMAGE_III)));
  log.info("Initial Activity Created");
}
  }
  private ActivityEntity buildActivity(String name, String content, String image){
    return ActivityEntity.builder()
            .name(name)
            .content(content)
            .image(image)
            .build();
  }

}
