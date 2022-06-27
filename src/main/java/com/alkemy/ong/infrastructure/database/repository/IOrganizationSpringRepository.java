package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrganizationSpringRepository extends JpaRepository<OrganizationEntity, Long> {
  @Query("SELECT u FROM OrganizationEntity u ORDER BY u.id ASC LIMIT 1")
  OrganizationEntity find();

}
