package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IListMemberUseCase {

  Page<Member> findAll(PageRequest pageRequest);

}
