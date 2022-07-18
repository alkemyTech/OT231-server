package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.SocialMedia;
import com.alkemy.ong.infrastructure.rest.request.SocialMediaRequest;
import com.alkemy.ong.infrastructure.rest.response.SocialMediaResponse;
import org.springframework.stereotype.Component;

@Component
public class SocialMediaMapper {

  public SocialMedia toDomain(SocialMediaRequest socialMediaRequest) {
    if (socialMediaRequest == null) {
      return null;
    }
    return SocialMedia.builder()
        .facebookUrl(socialMediaRequest.getFacebookUrl())
        .instagramUrl(socialMediaRequest.getInstagramUrl())
        .linkedIndUrl(socialMediaRequest.getLinkedInUrl())
        .build();
  }

  public SocialMediaResponse toResponse(SocialMedia socialMedia) {
    if (socialMedia == null) {
      return null;
    }
    return SocialMediaResponse.builder()
        .facebookUrl(socialMedia.getFacebookUrl())
        .instagramUrl(socialMedia.getInstagramUrl())
        .linkedInUrl(socialMedia.getLinkedIndUrl())
        .build();
  }

}
