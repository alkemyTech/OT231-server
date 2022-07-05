package com.alkemy.ong.infrastructure.database.repository.spring;

import com.alkemy.ong.infrastructure.database.entity.SlideEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISlideSpringRepository extends JpaRepository<SlideEntity, Long> {

  List<SlideEntity> findAllByOrderByOrder();

  Optional<SlideEntity> isDeleted(Long id);

  @Modifying
  @Query(value = "UPDATE SlideEntity u SET u.softDelete = true WHERE u.id = :id")
  void softDeleteById(@Param("id") Long id);
}
