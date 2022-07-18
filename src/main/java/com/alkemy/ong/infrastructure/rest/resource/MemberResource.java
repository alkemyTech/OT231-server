package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IDeleteMemberUseCase;
import com.alkemy.ong.application.service.usecase.IListMemberUseCase;
import com.alkemy.ong.domain.Member;
import com.alkemy.ong.infrastructure.rest.mapper.MemberMapper;
import com.alkemy.ong.infrastructure.rest.response.ListMemberResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/members")
public class MemberResource {

  @Autowired
  private IDeleteMemberUseCase deleteMemberUseCase;

  @Autowired
  private IListMemberUseCase listMemberUseCase;

  @Autowired
  private MemberMapper memberMapper;

  @DeleteMapping(value = "/{id}", produces = {"application/json"})
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteMemberUseCase.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(produces = {"application/json"})
  public ResponseEntity<ListMemberResponse> list(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    List<Member> members = listMemberUseCase.findAll(PageRequest.of(page, size));
    ListMemberResponse response = memberMapper.toResponse(members);
    return ResponseEntity.ok(response);
  }

}
