package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.User;
import com.alkemy.ong.infrastructure.rest.request.CommentRequest;
import com.alkemy.ong.infrastructure.rest.request.UpdateCommentRequest;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;
import com.alkemy.ong.infrastructure.rest.response.FullNameResponse;
import com.alkemy.ong.infrastructure.rest.response.ListCommentResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  @Autowired
  private UserMapper userMapper;

  public Comment toDomain(CommentRequest commentRequest) {
    if (commentRequest == null) {
      return null;
    }
    Comment comment = new Comment();
    comment.setNewsId(commentRequest.getNewsId());
    comment.setUserId(commentRequest.getUserId());
    comment.setBody(commentRequest.getBody());
    return comment;
  }

  public Comment toDomain(Long id, String token) {
    return Comment.builder()
        .id(id)
        .user(userMapper.toDomain(token))
        .build();
  }

  public Comment toDomain(Long id, UpdateCommentRequest updateCommentRequest, String token) {
    if (id == null || updateCommentRequest == null) {
      return null;
    }
    return Comment.builder()
        .id(id)
        .body(updateCommentRequest.getBody())
        .user(userMapper.toDomain(token))
        .build();
  }

  public ListCommentResponse toResponse(List<Comment> comments) {
    if (comments == null || comments.isEmpty()) {
      return new ListCommentResponse(Collections.emptyList());
    }

    List<CommentResponse> commentResponses = new ArrayList<>(comments.size());
    for (Comment comment : comments) {
      CommentResponse response = CommentResponse.builder().body(comment.getBody()).build();
      commentResponses.add(response);
    }
    return new ListCommentResponse(commentResponses);
  }

  public CommentResponse toResponse(Comment comment) {
    if (comment == null) {
      return null;
    }
    return CommentResponse.builder()
        .id(comment.getId())
        .body(comment.getBody())
        .createdBy(getFullNameResponse(comment.getUser()))
        .associatedNews(comment.getNews().getName())
        .createTimestamp(comment.getCreateTimestamp())
        .build();
  }

  private FullNameResponse getFullNameResponse(User user) {
    return FullNameResponse.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .build();
  }

}
