package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.domain.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberService implements IDeleteMemberUseCase {

  private final IMemberRepository memberRepository;

  @Override
  public void delete(Long id) {
    Member member = memberRepository
        .findById(id)
        .orElseThrow(() -> new RecordNotFoundException("Member not found."));
    if (isNotDeleted(member)) {
      member.setSoftDelete(true);
      memberRepository.save(member);
    }
  }

  private boolean isNotDeleted(Member member) {
    Boolean softDelete = member.getSoftDelete();
    return softDelete == null || Boolean.FALSE.equals(softDelete);
  }

}
