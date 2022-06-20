package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.infrastructure.database.entity.NewEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface INewRepository extends JpaRepository<NewEntity, Long> {
  @Modifying
  @Query("update NewEntity n set n.softDelete = true where n.id = :id")
  void softDelete(Long id);

  @Query("select n FROM NewEntity n WHERE n.softDelete = false")
  List<NewEntity> findAll();
}
