package com.alkemy.ong.infrastructure.rest.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CommentResponse {

  private Long id;
  private String body;
  private FullNameResponse createBy;
  private String associatedNews;
  private Timestamp createTimestamp;
}
