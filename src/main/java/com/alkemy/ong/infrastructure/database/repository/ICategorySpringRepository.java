package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategorySpringRepository extends JpaRepository<CategoryEntity, Long> {

  @Query("SELECT u FROM CategoryEntity u WHERE u.softDelete = true")
  Boolean isDeleted(Long id);

  @Query("UPDATE u WHERE CategoryEntity u SET u.sofDelete = true WHERE u.id = ?1")
  void sofDeleteById(Long id);
}
