package com.alkemy.ong.infrastructure.database.repository.spring;

import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrganizationSpringRepository extends JpaRepository<OrganizationEntity, Long> {

}
