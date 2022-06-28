package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.domain.Organization;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrganizationService implements IGetOrganizationUseCase {

  private final IOrganizationRepository organizationRepository;

  @Override
  public Organization find() {
    return organizationRepository.find();
  }

}
