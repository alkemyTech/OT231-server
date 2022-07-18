package com.alkemy.ong.application.service.usecase;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import com.alkemy.ong.domain.Member;

public interface IListMemberUseCase {

  List<Member> findAll(PageRequest of);

}
