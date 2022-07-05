package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateCommentUseCase;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.mapper.CommentMapper;
import com.alkemy.ong.infrastructure.rest.request.CommentRequest;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentResource {

  @Autowired
  private CommentMapper commentMapper;

  @Autowired
  private ICreateCommentUseCase createCommentUseCase;


  @PostMapping(value = "/comments",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<CommentResponse> create(
      @Valid @RequestBody CommentRequest commentRequest) {
    Comment comment = commentMapper.toDomain(commentRequest);
    CommentResponse response = commentMapper.toResponse(
            createCommentUseCase.add(comment));
    return new ResponseEntity<CommentResponse>(response, HttpStatus.CREATED);
  }



}
