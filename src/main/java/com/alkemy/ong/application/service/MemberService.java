package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.IMemberRepository;
import com.alkemy.ong.application.service.usecase.ICreateMemberUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.domain.Member;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MemberService implements IDeleteMemberUseCase, ICreateMemberUseCase {

  private final IMemberRepository memberRepository;

  @Override
  public void delete(Long id) {
    Member member = findBy(id);
    member.setSoftDelete(true);
    memberRepository.save(member);
  }

  @Override
  public Member add(Member member) {
    member.setSoftDelete(false);
    return memberRepository.add(member);
  }

  private Member findBy(Long id) {
    Member member = memberRepository.findById(id).orElse(null);
    if (member == null || isDeleted(member)) {
      throw new RecordNotFoundException("Member not found.");
    }
    return member;
  }

  private boolean isDeleted(Member member) {
    Boolean softDelete = member.getSoftDelete();
    return !(softDelete == null || Boolean.FALSE.equals(softDelete));
  }

}
