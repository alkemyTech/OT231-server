package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrganizationEntityMapper {

  public List<Organization> toDomain(List<OrganizationEntity> organizationEntity) {
    if (organizationEntity == null || organizationEntity.isEmpty()) {
      return Collections.emptyList();
    }
    List<Organization> organizations = new ArrayList<>(organizationEntity.size());
    for (OrganizationEntity organization : organizationEntity) {
      organizations.add(Organization.builder()
                .name(organization.getName())
                .image(organization.getImage())
                .address(organization.getAddress())
                .phone(organization.getPhone())
                .build());
    }
    return organizations;
  }
}