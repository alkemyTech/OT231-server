package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.domain.SocialMedia;
import com.alkemy.ong.infrastructure.rest.request.UpdateOrganizationRequest;
import com.alkemy.ong.infrastructure.rest.response.OrganizationPublicDataResponse;
import com.alkemy.ong.infrastructure.rest.response.SlideResponse;
import com.alkemy.ong.infrastructure.rest.response.SocialMediaResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
        .slides(getSlides(organization.getSlides()))
        .build();
  }

  private SocialMediaResponse getSocialMedia(SocialMedia socialMedia) {
    return SocialMediaResponse.builder()
        .facebookUrl(socialMedia.getFacebookUrl())
        .instagramUrl(socialMedia.getInstagramUrl())
        .linkedIndUrl(socialMedia.getLinkedIndUrl())
        .build();
  }

  private List<SlideResponse> getSlides(List<Slide> slides) {
    if (slides == null || slides.isEmpty()) {
      return Collections.emptyList();
    }

    List<SlideResponse> slideResponses = new ArrayList<>(slides.size());
    for (Slide slide : slides) {
      slideResponses.add(SlideResponse.builder()
          .imageUrl(slide.getImageUrl())
          .order(slide.getOrder())
          .text(slide.getText())
          .build());
    }
    return slideResponses;
  }

}
