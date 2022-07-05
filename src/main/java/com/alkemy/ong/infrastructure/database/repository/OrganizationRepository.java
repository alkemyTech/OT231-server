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
    OrganizationEntity organizationEntity = organizationEntityMapper.toEntity(organization);
    OrganizationEntity updatedOrganization = organizationSpringRepository.findAll().get(0);
    updatedOrganization.setName(organizationEntity.getName());
    updatedOrganization.setImage(organizationEntity.getImage());
    updatedOrganization.setAddress(organizationEntity.getAddress());
    updatedOrganization.setPhone(organizationEntity.getPhone());
    updatedOrganization.setEmail(organizationEntity.getEmail());
    updatedOrganization.setWelcomeText(organizationEntity.getWelcomeText());
    updatedOrganization.setAboutUsText(organizationEntity.getAboutUsText());
    updatedOrganization.setFacebookUrl(organizationEntity.getFacebookUrl());
    updatedOrganization.setLinkedInUrl(organizationEntity.getLinkedInUrl());
    updatedOrganization.setInstagramUrl(organizationEntity.getInstagramUrl());
    return organizationEntityMapper.toDomain(organizationSpringRepository.save(
            updatedOrganization));
  }


}
