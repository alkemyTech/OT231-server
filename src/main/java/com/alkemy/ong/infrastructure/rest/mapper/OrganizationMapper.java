package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.rest.response.OrganizationPublicDataResponse;
import java.util.Collections;
import org.springframework.stereotype.Component;


@Component
public class OrganizationMapper {

  public OrganizationPublicDataResponse toResponse(Organization organization) {
    if (organization == null) {
      return null;
    }
    return OrganizationPublicDataResponse.builder()
        .name(organization.getName())
        .image(organization.getImage())
        .address(organization.getAddress())
        .phone(organization.getPhone())
        .socialMedia(Collections.singletonList(organization.getFacebookUrl()))
        .socialMedia(Collections.singletonList(organization.getInstagramUrl()))
        .socialMedia(Collections.singletonList(organization.getLinkedIndUrl()))
        .build();
  }

}
