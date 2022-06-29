package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.OrganizationSocialMedia;
import com.alkemy.ong.infrastructure.rest.response.OrganizationPublicDataResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class OrganizationMapper {

  public OrganizationPublicDataResponse toResponse(Organization organization, OrganizationSocialMedia organizationSocialMedia) {
    if (organization == null) {
      return null;
    }
    return OrganizationPublicDataResponse.builder()
        .name(organization.getName())
        .image(organization.getImage())
        .address(organization.getAddress())
        .phone(organization.getPhone())
        .socialMedia(Collections.singletonList(organizationSocialMedia.getFacebookUrl()))
        .socialMedia(Collections.singletonList(organizationSocialMedia.getInstagramUrl()))
        .socialMedia(Collections.singletonList(organizationSocialMedia.getLinkedIndUrl()))
        .build();
  }

}
