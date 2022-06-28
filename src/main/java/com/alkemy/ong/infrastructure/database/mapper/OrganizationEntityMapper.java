package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import org.springframework.stereotype.Component;

@Component
public class OrganizationEntityMapper {

  public Organization toDomain(OrganizationEntity organizationEntity) {
    if (organizationEntity == null) {
      return null;
    }
    return Organization.builder()
        .name(organizationEntity.getName())
        .image(organizationEntity.getImage())
        .address(organizationEntity.getAddress())
        .phone(organizationEntity.getPhone())
        .build();
  }

}