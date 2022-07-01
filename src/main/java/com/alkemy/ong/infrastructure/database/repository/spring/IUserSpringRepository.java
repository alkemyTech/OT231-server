package com.alkemy.ong.infrastructure.database.repository.spring;

import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserSpringRepository extends JpaRepository<UserEntity, Long> {

  UserEntity findByEmail(String username);

  @Query(value = "SELECT u FROM UserEntity u WHERE u.softDelete = true")
  Optional<UserEntity> isDeleted(Long id);

  @Modifying
  @Query(value = "UPDATE UserEntity u SET u.softDelete = true WHERE u.id = :id")
  void softDeleteById(@Param("id") Long id);
}
