package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateOrganizationUseCase;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrganizationService implements IGetOrganizationUseCase, IUpdateOrganizationUseCase {

  private final IOrganizationRepository organizationRepository;

  @Override
  public Organization find() {
    return organizationRepository.find();
  }

  @Override
  public Organization update(Organization updateOrganization) {

    return organizationRepository.update(updateOrganization);
  }
}
