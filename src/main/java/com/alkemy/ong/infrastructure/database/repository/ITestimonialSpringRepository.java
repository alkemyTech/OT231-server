package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.infrastructure.database.entity.TestimonialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestimonialSpringRepository extends JpaRepository<TestimonialEntity, Long> {

  @Query("SELECT u FROM TestimonialEntity u WHERE u.softDelete = true")
  Boolean isDeleted(Long id);

  @Query("UPDATE TestimonialEntity u SET u.softDelete = true WHERE u.id= ?1")
  void softDeleteById(Long id);
}
