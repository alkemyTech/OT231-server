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
        .softDelete(memberEntity.getSoftDelete())
        .build();
  }

  public MemberEntity toEntity(Member member) {
    if (member == null) {
      return null;
    }
    return MemberEntity.builder()
        .id(member.getId())
        .softDelete(member.getSoftDelete())
        .build();
  }
}
