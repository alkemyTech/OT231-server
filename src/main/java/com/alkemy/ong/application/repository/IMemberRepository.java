package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Member;
import java.util.Optional;

public interface IMemberRepository {

  void save(Member member);

  Optional<Member> findById(Long id);

}
