package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import com.alkemy.ong.infrastructure.database.repository.IMemberSpringRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberService implements IDeleteMemberUseCase {

  private final IMemberSpringRepository memberSpringRepository;

  @Override
  public void delete(Long id) {
    MemberEntity member = memberSpringRepository
            .findById(id).orElseThrow(() -> new RecordNotFoundException("Member not found"));
    if (!member.getSoftDelete()) {
      member.setSoftDelete(true);
      memberSpringRepository.save(member);
    }
  }

}
