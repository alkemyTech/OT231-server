package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.SocialMedia;
import com.alkemy.ong.infrastructure.rest.response.OrganizationPublicDataResponse;
import com.alkemy.ong.infrastructure.rest.response.SocialMediaResponse;
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
        .socialMedia(getSocialMedia(organization.getSocialMedia()))
        .build();
  }

  private SocialMediaResponse getSocialMedia(SocialMedia socialMedia) {
    return SocialMediaResponse.builder()
        .facebookUrl(socialMedia.getFacebookUrl())
        .instagramUrl(socialMedia.getInstagramUrl())
        .linkedIndUrl(socialMedia.getLinkedIndUrl())
        .build();
  }

}
