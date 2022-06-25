package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Organization;

import java.util.List;

public interface IOrganizationRepository {
  List<Organization> findAll();
}
