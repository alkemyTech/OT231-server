package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestimonialSpringRepository extends JpaRepository<TestimonialEntity, Long> {

  @Query(value = "SELECT t FROM TestimonialEntity t WHERE t.softDelete = true")
  Optional<TestimonialEntity> isDeleted(Long id);

  @Modifying
  @Query(value = "UPDATE TestimonialEntity t SET t.softDelete = true WHERE t.id= :id")
  void softDeleteById(@Param("id") Long id);
}
