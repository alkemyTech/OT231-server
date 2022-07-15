package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.request.MemberRequest;
import com.alkemy.ong.infrastructure.rest.response.MemberResponse;
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
        .facebookUrl(memberRequest.getFacebookUrl())
        .instagramUrl(memberRequest.getInstagramUrl())
        .linkedInUrl(memberRequest.getLinkedInUrl())
        .description(memberRequest.getDescription())
        .build();
  }

  public MemberResponse toResponse(Member add) {
    if (add == null) {
      return null;
    }
    return MemberResponse.builder()
        .name(add.getName())
        .image(add.getImage())
        .facebookUrl(add.getFacebookUrl())
        .instagramUrl(add.getInstagramUrl())
        .linkedInUrl(add.getLinkedInUrl())
        .description(add.getDescription())
        .build();
  }
}
