package com.alkemy.ong.infrastructure.rest.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;
import com.alkemy.ong.infrastructure.rest.response.ListMemberResponse;
import com.alkemy.ong.infrastructure.rest.response.MemberResponse;
import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.rest.request.MemberRequest;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MemberMapper {

  @Autowired
  private SocialMediaMapper socialMediaMapper;

  public Member toDomain(MemberRequest memberRequest) {
    if (memberRequest == null) {
      return null;
    }
    return Member.builder()
        .name(memberRequest.getName())
        .image(memberRequest.getImage())
        .socialMedia(socialMediaMapper.toDomain(memberRequest.getSocialMedia()))
        .description(memberRequest.getDescription())
        .build();
  }

  public ListMemberResponse toResponse(List<Member> members) {
    if (members == null || members.isEmpty()) {
      return new ListMemberResponse(Collections.emptyList());
    }
    List<MemberResponse> memberResponses = new ArrayList<>(members.size());
    for (Member member : members) {
      memberResponses.add(toResponse(member));
    }
    return new ListMemberResponse(memberResponses);
  }

  public MemberResponse toResponse(Member member) {
    if (member == null) {
      return null;
    }
    return MemberResponse.builder()
        .name(member.getName())
        .image(member.getImage())
        .socialMedia(socialMediaMapper.toResponse(member.getSocialMedia()))
        .description(member.getDescription())
        .build();
  }

}
