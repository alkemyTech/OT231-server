package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateMemberUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.application.service.usecase.IListMemberUseCase;
import com.alkemy.ong.infrastructure.rest.response.ListMemberResponse;
import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.rest.mapper.MemberMapper;
import com.alkemy.ong.infrastructure.rest.request.MemberRequest;
import com.alkemy.ong.infrastructure.rest.response.MemberResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberResource {

  @Autowired
  private IDeleteMemberUseCase deleteMemberUseCase;

  @Autowired
  private IListMemberUseCase listMemberUseCase;

  @Autowired
  private ICreateMemberUseCase createMemberUseCase;

  @Autowired
  private MemberMapper memberMapper;

  @DeleteMapping(value = "members/{id}", produces = {"application/json"})
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteMemberUseCase.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping(value = "/members",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<MemberResponse> create(@Valid @RequestBody MemberRequest memberRequest) {
    Member member = memberMapper.toDomain(memberRequest);
    MemberResponse response = memberMapper.toResponse(createMemberUseCase.add(member));
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping(value = "/members", produces = {"application/json"})
  public ResponseEntity<ListMemberResponse> list(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    List<Member> members = listMemberUseCase.findAll(PageRequest.of(page, size));
    ListMemberResponse response = memberMapper.toResponse(members);
    return ResponseEntity.ok(response);
  }

}
