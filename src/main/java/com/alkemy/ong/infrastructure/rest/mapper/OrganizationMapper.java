package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.OrganizationSocialMedia;
import com.alkemy.ong.infrastructure.rest.response.OrganizationPublicDataResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

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
        .socialMedia(List.add(organizationSocialMedia.getFacebookUrl()))
        .socialMedia(List.add(organizationSocialMedia.getInstagramUrl()))
        .socialMedia(List.add(organizationSocialMedia.getLinkedIndUrl()))
        .build();
  }

}
