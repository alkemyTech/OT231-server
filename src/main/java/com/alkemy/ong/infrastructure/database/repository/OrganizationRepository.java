package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.database.mapper.OrganizationEntityMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrganizationRepository implements IOrganizationRepository {

  private final IOrganizationSpringRepository organizationSpringRepository;
  private final OrganizationEntityMapper organizationEntityMapper;

  @Override
  public List<Organization> findAll() {
    return organizationEntityMapper.toDomain(organizationSpringRepository.findAll());
  }

}
