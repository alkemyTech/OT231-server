package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.rest.request.MemberRequest;
import com.alkemy.ong.infrastructure.rest.response.MemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

  @Autowired
  SocialMediaMapper socialMediaMapper;

  public Member toDomain(MemberRequest memberRequest) {
    if (memberRequest == null) {
      return null;
    }
    return Member.builder()
        .name(memberRequest.getName())
        .image(memberRequest.getImage())
        .socialMedia(socialMediaMapper.getSocialMedia(memberRequest.getSocialMedia()))
        .description(memberRequest.getDescription())
        .build();
  }

  public MemberResponse toResponse(Member member) {
    if (member == null) {
      return null;
    }
    return MemberResponse.builder()
        .name(member.getName())
        .image(member.getImage())
        .socialMedia(socialMediaMapper.getSocialMediaResponse(member.getSocialMedia()))
        .description(member.getDescription())
        .build();
  }

}
