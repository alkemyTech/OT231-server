package com.alkemy.ong.infrastructure.database.repository.spring;

import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategorySpringRepository extends JpaRepository<CategoryEntity, Long> {

  CategoryEntity findByName(String name);

  @Query(value = "SELECT c FROM CategoryEntity c WHERE c.softDelete = true")
  Optional<CategoryEntity> isDeleted(Long id);

  @Modifying
  @Query("UPDATE CategoryEntity c SET c.softDelete = true WHERE c.id = :id")
  void softDeleteById(@Param("id") Long id);
}
