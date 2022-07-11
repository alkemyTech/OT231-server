package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateCommentUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.mapper.CommentMapper;
import com.alkemy.ong.infrastructure.rest.request.CommentRequest;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentResource {

  @Autowired
  private CommentMapper commentMapper;

  @Autowired
  private ICreateCommentUseCase createCommentUseCase;

  @Autowired
  private IDeleteCommentUseCase deleteCommentUseCase;

  @PostMapping(value = "/comments",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<CommentResponse> create(
      @Valid @RequestBody CommentRequest commentRequest) {
    Comment comment = commentMapper.toDomain(commentRequest);
    CommentResponse response = commentMapper.toResponse(createCommentUseCase.add(comment));
    return new ResponseEntity<CommentResponse>(response, HttpStatus.CREATED);
  }

  @DeleteMapping(value = "comments/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id,
      @RequestHeader("Authorization") String token) {
    Comment comment = commentMapper.toDomain(id, token);
    deleteCommentUseCase.delete(comment);
    return ResponseEntity.noContent().build();
  }

}
