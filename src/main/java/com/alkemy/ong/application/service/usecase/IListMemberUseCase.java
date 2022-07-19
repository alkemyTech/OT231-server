package com.alkemy.ong.application.service.usecase;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.alkemy.ong.domain.Member;

public interface IListMemberUseCase {

  Page<Member> findAll(PageRequest pageRequest);

}
