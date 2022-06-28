package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import org.springframework.stereotype.Component;

@Component
public class MemberEntityMapper {

  public Member toDomain(MemberEntity memberEntity) {
    if (memberEntity == null) {
      return null;
    }
    return Member.builder()
        .id(memberEntity.getId())
        .name(memberEntity.getName())
        .facebookUrl(memberEntity.getFacebookUrl())
        .instagramUrl(memberEntity.getInstagramUrl())
        .linkedInUrl(memberEntity.getLinkedInUrl())
        .image(memberEntity.getImage())
        .description(memberEntity.getDescription())
        .softDelete(memberEntity.getSoftDelete())
        .build();
  }

  public MemberEntity toEntity(Member member) {
    if (member == null) {
      return null;
    }
    return MemberEntity.builder()
        .id(member.getId())
        .name(member.getName())
        .facebookUrl(member.getFacebookUrl())
        .instagramUrl(member.getInstagramUrl())
        .linkedInUrl(member.getLinkedInUrl())
        .image(member.getImage())
        .description(member.getDescription())
        .softDelete(member.getSoftDelete())
        .build();
  }
}
