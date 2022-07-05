package com.alkemy.ong.infrastructure.database.repository.spring;

import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface INewsSpringRepository extends JpaRepository<NewsEntity, Long> {

  @Query(value = "SELECT u FROM NewsEntity u WHERE u.softDelete = true")
  Optional<NewsEntity> isDeleted(Long id);

  @Modifying
  @Query(value = "UPDATE NewsEntity u SET u.softDelete = true WHERE u.id = :id")
  void softDeleteById(@Param("id") Long id);

  NewsEntity findByIdAndSoftDeleteFalse(Long id);


}
