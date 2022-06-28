package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import com.alkemy.ong.infrastructure.database.mapper.MemberEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.IMemberSpringRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberRepository implements IMemberRepository {

  private final IMemberSpringRepository memberSpringRepository;
  private final MemberEntityMapper memberEntityMapper;

  @Override
  public void save(Member member) {
    memberSpringRepository.save(memberEntityMapper.toEntity(member));
  }

  @Override
  public Optional<Member> findById(Long id) {
    Optional<MemberEntity> memberEntity = memberSpringRepository.findById(id);
    if (memberEntity.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(memberEntityMapper.toDomain(memberEntity.get()));
  }
}
