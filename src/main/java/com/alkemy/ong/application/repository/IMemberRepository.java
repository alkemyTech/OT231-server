package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Member;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IMemberRepository {

  void save(Member member);

  Optional<Member> findById(Long id);

  Member add(Member member);

  Page<Member> findAll(PageRequest pageable);

}
