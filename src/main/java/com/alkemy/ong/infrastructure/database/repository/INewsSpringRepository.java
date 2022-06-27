package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface INewsSpringRepository extends JpaRepository<NewsEntity, Long> {

  @Query("SELECT u FROM NewsEntity u WHERE u.softDelete = true")
  Optional<NewsEntity> isDeleted(Long id);

  @Modifying
  @Query("UPDATE NewsEntity u SET u.softDelete = true WHERE u.id = ?1")
  void softDeleteById(Long id);

}
