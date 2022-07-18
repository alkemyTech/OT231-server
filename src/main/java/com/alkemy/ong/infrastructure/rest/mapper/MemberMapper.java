package com.alkemy.ong.infrastructure.rest.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;
import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.rest.response.ListMemberResponse;
import com.alkemy.ong.infrastructure.rest.response.MemberResponse;
import com.alkemy.ong.infrastructure.rest.response.SocialMediaResponse;

@Component
public class MemberMapper {

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
    return MemberResponse.builder()
        .name(member.getName())
        .image(member.getImage())
        .description(member.getDescription())
        .socialMedia(getSocialMedia(member))
        .build();
  }

  private SocialMediaResponse getSocialMedia(Member member) {
    return SocialMediaResponse.builder()
        .facebookUrl(member.getFacebookUrl())
        .instagramUrl(member.getInstagramUrl())
        .linkedInUrl(member.getLinkedInUrl())
        .build();
  }

}
