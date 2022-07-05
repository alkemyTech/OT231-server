package com.alkemy.ong.domain;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

  private Long id;
  private String body;
  private User user;
  private News news;
  private Timestamp createTimestamp;

  public void setUserId(Long id) {
    if (user == null) {
      user = User.builder().id(id).build();
    } else {
      user.setId(id);
    }
  }

  public void setNewsId(Long id) {
    if (news == null) {
      news = News.builder().id(id).build();
    } else {
      news.setId(id);
    }
  }

}
