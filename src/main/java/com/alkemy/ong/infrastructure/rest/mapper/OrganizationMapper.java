package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.domain.SocialMedia;
import com.alkemy.ong.infrastructure.rest.request.UpdateOrganizationRequest;
import com.alkemy.ong.infrastructure.rest.response.OrganizationPublicDataResponse;
import com.alkemy.ong.infrastructure.rest.response.SlideResponse;
import com.alkemy.ong.infrastructure.rest.response.SocialMediaResponse;
import org.springframework.stereotype.Component;


@Component
public class OrganizationMapper {

  public Organization toDomain(UpdateOrganizationRequest updateOrganizationRequest) {
    if (updateOrganizationRequest == null) {
      return null;
    }
    return Organization.builder()
            .name(updateOrganizationRequest.getName())
            .image(updateOrganizationRequest.getImage())
            .address(updateOrganizationRequest.getAddress())
            .phone(updateOrganizationRequest.getPhone())
            .build();
  }

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
        .slide(getSlide(organization.getSlide()))
        .build();
  }

  private SocialMediaResponse getSocialMedia(SocialMedia socialMedia) {
    return SocialMediaResponse.builder()
        .facebookUrl(socialMedia.getFacebookUrl())
        .instagramUrl(socialMedia.getInstagramUrl())
        .linkedIndUrl(socialMedia.getLinkedIndUrl())
        .build();
  }

  private SlideResponse getSlide(Slide slide) {
    return SlideResponse.builder().build().builder()
            .imageUrl(slide.getImageUrl())
            .text(slide.getText())
            .order(slide.getOrder())
            .build();
  }
}
