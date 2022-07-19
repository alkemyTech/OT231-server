package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.ListComments;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.rest.request.NewsRequest;
import com.alkemy.ong.infrastructure.rest.response.CommentResponse;
import com.alkemy.ong.infrastructure.rest.response.ListCommentsNewsResponse;
import com.alkemy.ong.infrastructure.rest.response.NewsResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

  @Autowired
  private CommentMapper commentMapper;

  public News toDomain(NewsRequest news) {
    if (news == null) {
      return null;
    }
    return News.builder()
        .name(news.getName())
        .text(news.getText())
        .image(news.getImage())
        .build();
  }

  public NewsResponse toResponse(News news) {
    if (news == null) {
      return null;
    }
    return NewsResponse.builder()
        .id(news.getId())
        .name(news.getName())
        .text(news.getText())
        .image(news.getImage())
        .build();
  }

  public ListCommentsNewsResponse toListResponse(ListComments comments) {
    if (comments == null || comments.getComments().isEmpty()) {
      return new ListCommentsNewsResponse();
    }
    String newsName = "";
    List<CommentResponse> commentResponses = new ArrayList<>(comments.getComments().size());
    for (Comment comment : comments.getComments()) {
      newsName = comment.getNews().getName();
      commentResponses.add(commentMapper.toResponse(comment));
    }
    return new ListCommentsNewsResponse(newsName, commentResponses);
  }

}
