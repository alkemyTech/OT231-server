package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentRequest {

  @Pattern(regexp = "^[A-Za-z\\d\\s]*$", message = "Body can only contain "
      + "alphanumeric and whitespaces")
  private String body;

  @NotNull(message = "User must not be null")
  private Long userId;

  @NotNull(message = "News must not be null")
  private Long newsId;

}
