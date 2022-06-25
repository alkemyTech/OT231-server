package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Organization;

import java.util.List;

public interface IOrganizationPublicDataUseCase {
  List<Organization> findAll();
}
