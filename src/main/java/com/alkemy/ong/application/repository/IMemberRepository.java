package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;

public interface IMemberRepository {

  void save(Member member);

  Optional<Member> findById(Long id);

  List<Member> findAllActive(PageRequest of);

}
