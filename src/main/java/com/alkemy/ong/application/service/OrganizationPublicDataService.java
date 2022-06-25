package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.service.usecase.IOrganizationPublicDataUseCase;
import com.alkemy.ong.domain.Organization;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrganizationPublicDataService implements IOrganizationPublicDataUseCase {

  private final IOrganizationRepository organizationRepository;

  @Override
  public List<Organization> findAll() {
    return organizationRepository.findAll();
  }
}
