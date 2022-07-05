package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.SocialMedia;
import com.alkemy.ong.infrastructure.rest.request.OrganizationRequest;
import com.alkemy.ong.infrastructure.rest.request.SocialMediaRequest;
import com.alkemy.ong.infrastructure.rest.response.OrganizationUpdateResponse;
import com.alkemy.ong.infrastructure.rest.response.SocialMediaResponse;
import org.springframework.stereotype.Component;

@Component
public class OrganizationUpdateMapper {

  public Organization toDomain(OrganizationRequest organizationRequest) {
    if (organizationRequest == null) {
      return null;
    }
    return Organization.builder()
          .name(organizationRequest.getName())
          .image(organizationRequest.getImage())
          .address(organizationRequest.getAddress())
          .phone(organizationRequest.getPhone())
          .email(organizationRequest.getEmail())
          .welcomeText(organizationRequest.getWelcomeText())
          .socialMedia(getSocialMediaRequest(organizationRequest.getSocialMedia()))
          .build();
  }

  public OrganizationUpdateResponse toResponse(Organization organization) {
    if (organization == null) {
      return null;
    }
    return OrganizationUpdateResponse.builder()
            .name(organization.getName())
            .image(organization.getImage())
            .address(organization.getAddress())
            .phone(organization.getPhone())
            .email(organization.getEmail())
            .welcomeText(organization.getWelcomeText())
            .socialMedia(getSocialMediaResponse(organization.getSocialMedia()))
            .build();
  }

  private SocialMediaResponse getSocialMediaResponse(SocialMedia socialMedia) {
    return SocialMediaResponse.builder()
            .facebookUrl(socialMedia.getFacebookUrl())
            .instagramUrl(socialMedia.getInstagramUrl())
            .linkedIndUrl(socialMedia.getLinkedIndUrl())
            .build();
  }

  private SocialMedia getSocialMediaRequest(SocialMediaRequest socialMedia) {
    return SocialMedia.builder()
            .facebookUrl(socialMedia.getFacebookUrl())
            .instagramUrl(socialMedia.getInstagramUrl())
            .linkedIndUrl(socialMedia.getLinkedIndUrl())
            .build();
  }

}
