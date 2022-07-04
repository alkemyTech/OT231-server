package com.alkemy.ong.infrastructure.rest.response;

import com.alkemy.ong.domain.UserFullName;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CommentResponse {

  private Long id;
  private String body;
  private UserFullName createBy;
  private String associatedNews;
  private Timestamp createTimestamp;
}
