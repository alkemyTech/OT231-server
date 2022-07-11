package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.SocialMedia;
import com.alkemy.ong.infrastructure.rest.request.SocialMediaRequest;
import com.alkemy.ong.infrastructure.rest.request.UpdateOrganizationRequest;
import com.alkemy.ong.infrastructure.rest.response.SocialMediaResponse;
import com.alkemy.ong.infrastructure.rest.response.UpdateOrganizationResponse;
import org.springframework.stereotype.Component;

@Component
public class UpdateOrganizationMapper {

  public Organization toDomain(UpdateOrganizationRequest updateOrganizationRequest) {
    if (updateOrganizationRequest == null) {
      return null;
    }
    return Organization.builder()
        .name(updateOrganizationRequest.getName())
        .image(updateOrganizationRequest.getImage())
        .address(updateOrganizationRequest.getAddress())
        .phone(updateOrganizationRequest.getPhone())
        .email(updateOrganizationRequest.getEmail())
        .welcomeText(updateOrganizationRequest.getWelcomeText())
        .socialMedia(getSocialMedia(updateOrganizationRequest.getSocialMedia()))
        .build();
  }

  public UpdateOrganizationResponse toResponse(Organization organization) {
    if (organization == null) {
      return null;
    }
    return UpdateOrganizationResponse.builder()
        .name(organization.getName())
        .image(organization.getImage())
        .address(organization.getAddress())
        .phone(organization.getPhone())
        .email(organization.getEmail())
        .welcomeText(organization.getWelcomeText())
        .socialMedia(getSocialMedia(organization.getSocialMedia()))
        .build();
  }

  private SocialMediaResponse getSocialMedia(SocialMedia socialMedia) {
    if (socialMedia == null) {
      return null;
    }
    return SocialMediaResponse.builder()
        .facebookUrl(socialMedia.getFacebookUrl())
        .instagramUrl(socialMedia.getInstagramUrl())
        .linkedInUrl(socialMedia.getLinkedInUrl())
        .build();
  }

  private SocialMedia getSocialMedia(SocialMediaRequest socialMedia) {
    if (socialMedia == null) {
      return null;
    }
    return SocialMedia.builder()
        .facebookUrl(socialMedia.getFacebookUrl())
        .instagramUrl(socialMedia.getInstagramUrl())
        .linkedInUrl(socialMedia.getLinkedInUrl())
        .build();
  }

}
