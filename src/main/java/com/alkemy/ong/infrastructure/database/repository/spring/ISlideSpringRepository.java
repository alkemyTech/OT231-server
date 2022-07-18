package com.alkemy.ong.infrastructure.database.repository.spring;

import com.alkemy.ong.infrastructure.database.entity.SlideEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISlideSpringRepository extends JpaRepository<SlideEntity, Long> {

  List<SlideEntity> findAllByOrderByOrder();
  
  @Query(value = "SELECT MAX(position) FROM slides", nativeQuery = true)
  Integer findMaxOrder();

}
