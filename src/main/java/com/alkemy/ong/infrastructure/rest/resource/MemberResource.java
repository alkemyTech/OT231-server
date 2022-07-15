package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateMemberUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.domain.Member;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.mapper.MemberMapper;
import com.alkemy.ong.infrastructure.rest.request.MemberRequest;
import com.alkemy.ong.infrastructure.rest.request.NewsRequest;
import com.alkemy.ong.infrastructure.rest.response.MemberResponse;
import com.alkemy.ong.infrastructure.rest.response.NewsResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MemberResource {

  @Autowired
  private IDeleteMemberUseCase deleteMemberUseCase;

  @Autowired
  private MemberMapper memberMapper;

  @Autowired
  private ICreateMemberUseCase createMemberUseCase;

  @DeleteMapping(value = "/members/{id}", produces = {"application/json"})
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteMemberUseCase.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping(value = "/members",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<MemberResponse> create(@Valid @RequestBody MemberRequest createRequest) {
    Member member = memberMapper.toDomain(createRequest);
    MemberResponse response = memberMapper.toResponse(createMemberUseCase.add(member));
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

}