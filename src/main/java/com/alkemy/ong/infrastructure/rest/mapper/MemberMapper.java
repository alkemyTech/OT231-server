package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.domain.SocialMedia;
import com.alkemy.ong.infrastructure.rest.request.MemberRequest;
import com.alkemy.ong.infrastructure.rest.request.SocialMediaRequest;
import com.alkemy.ong.infrastructure.rest.response.MemberResponse;
import com.alkemy.ong.infrastructure.rest.response.SocialMediaResponse;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

  public Member toDomain(MemberRequest memberRequest) {
    if (memberRequest == null) {
      return null;
    }
    return Member.builder()
        .name(memberRequest.getName())
        .image(memberRequest.getImage())
        .socialMedia(getSocialMedia(memberRequest.getSocialMedia()))
        .description(memberRequest.getDescription())
        .build();
  }

  private SocialMedia getSocialMedia(SocialMediaRequest socialMediaRequest) {
    if (socialMediaRequest == null) {
      return null;
    }
    return SocialMedia.builder()
        .facebookUrl(socialMediaRequest.getFacebookUrl())
        .instagramUrl(socialMediaRequest.getInstagramUrl())
        .linkedIndUrl(socialMediaRequest.getLinkedInUrl())
        .build();
  }

  public MemberResponse toResponse(Member member) {
    if (member == null) {
      return null;
    }
    return MemberResponse.builder()
        .name(member.getName())
        .image(member.getImage())
        .socialMedia(getSocialMedia(member.getSocialMedia()))
        .description(member.getDescription())
        .build();
  }

  private SocialMediaResponse getSocialMedia(SocialMedia socialMedia) {
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
