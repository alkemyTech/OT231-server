package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Member;
import com.alkemy.ong.domain.SocialMedia;
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
        .socialMedia(getSocialMedia(memberEntity))
        .image(memberEntity.getImage())
        .description(memberEntity.getDescription())
        .softDelete(memberEntity.getSoftDelete())
        .build();
  }

  private SocialMedia getSocialMedia(MemberEntity memberEntity) {
    return SocialMedia.builder()
        .facebookUrl(memberEntity.getFacebookUrl())
        .instagramUrl(memberEntity.getInstagramUrl())
        .linkedIndUrl(memberEntity.getLinkedInUrl())
        .build();
  }

  public MemberEntity toEntity(Member member) {
    if (member == null) {
      return null;
    }
    SocialMedia socialMedia = member.getSocialMedia();
    return MemberEntity.builder()
        .id(member.getId())
        .name(member.getName())
        .facebookUrl(socialMedia == null ? null : socialMedia.getFacebookUrl())
        .instagramUrl(socialMedia == null ? null : socialMedia.getInstagramUrl())
        .linkedInUrl(socialMedia == null ? null : socialMedia.getLinkedIndUrl())
        .image(member.getImage())
        .description(member.getDescription())
        .softDelete(member.getSoftDelete())
        .build();
  }
}
