package com.alkemy.ong.infrastructure.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse {

  private Long id;
  private String body;
  private FullNameResponse createdBy;
  private String associatedNews;
  private Timestamp createTimestamp;

}
