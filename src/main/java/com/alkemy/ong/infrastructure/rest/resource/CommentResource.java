package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateCommentUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCommentUseCase;
import com.alkemy.ong.application.service.usecase.IListCommentUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateCommentUseCase;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.rest.mapper.CommentMapper;
import com.alkemy.ong.infrastructure.rest.request.CommentRequest;
import com.alkemy.ong.infrastructure.rest.request.UpdateCommentRequest;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;
import com.alkemy.ong.infrastructure.rest.response.ListCommentResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
  private IUpdateCommentUseCase updateCommentUseCase;

  @Autowired
  private IDeleteCommentUseCase deleteCommentUseCase;

  @Autowired
  private IListCommentUseCase listCommentUseCase;

  @PostMapping(value = "/comments",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<CommentResponse> create(
      @Valid @RequestBody CommentRequest commentRequest) {
    Comment comment = commentMapper.toDomain(commentRequest);
    CommentResponse response = commentMapper.toResponse(createCommentUseCase.add(comment));
    return new ResponseEntity<CommentResponse>(response, HttpStatus.CREATED);
  }

  @DeleteMapping(value = "comments/{id}", produces = {"application/json"})
  public ResponseEntity<Void> delete(@PathVariable Long id,
      @RequestHeader("Authorization") String token) {
    Comment comment = commentMapper.toDomain(id, token);
    deleteCommentUseCase.delete(comment);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/comments", produces = {"application/json"})
  public ResponseEntity<ListCommentResponse> getAll() {
    List<Comment> comments = listCommentUseCase.findAll();
    return ResponseEntity.ok().body(commentMapper.toResponse(comments));
  }

  @PatchMapping(value = "comments/{id}",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<CommentResponse> update(
      @PathVariable Long id,
      @Valid @RequestBody UpdateCommentRequest updatecommentRequest,
      @RequestHeader("Authorization") String token) {
    Comment comment = commentMapper.toDomain(id, updatecommentRequest, token);
    CommentResponse response = commentMapper.toResponse(updateCommentUseCase.update(comment));
    return ResponseEntity.ok().body(response);
  }

}
