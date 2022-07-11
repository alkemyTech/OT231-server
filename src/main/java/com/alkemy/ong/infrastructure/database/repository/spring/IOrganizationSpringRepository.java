package com.alkemy.ong.infrastructure.database.repository.spring;

import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrganizationSpringRepository extends JpaRepository<OrganizationEntity, Long> {

  List<OrganizationEntity> findBySoftDeleteFalse();

}
