package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Organization;

public interface IOrganizationRepository {

  Organization find();

  Organization update(Organization organization);

}
