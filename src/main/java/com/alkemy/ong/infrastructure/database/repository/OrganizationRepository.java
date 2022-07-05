package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import com.alkemy.ong.infrastructure.database.mapper.OrganizationEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.IOrganizationSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Component
public class OrganizationRepository implements IOrganizationRepository {

  private final IOrganizationSpringRepository organizationSpringRepository;
  private final OrganizationEntityMapper organizationEntityMapper;

  @Override
  @Transactional
  public Organization find() {
    return organizationEntityMapper.toDomain(organizationSpringRepository.findAll().get(0));
  }

  @Override
  @Transactional
  public Organization update(Organization organization) {
    OrganizationEntity updatedOrganization = organizationSpringRepository.save(
        organizationEntityMapper.toEntity(organization));
    return organizationEntityMapper.toDomain(updatedOrganization);
  }


}
