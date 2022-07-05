package com.alkemy.ong.domain;

import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Comment {

  private Long id;
  private String body;
  private Long userId;
  private Long newsId;
  private Timestamp createTimestamp;

}
