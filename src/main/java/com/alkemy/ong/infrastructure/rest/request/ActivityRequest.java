package com.alkemy.ong.infrastructure.rest.request;

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
public class ActivityRequest {

  @Pattern(regexp = "^[\\D\\s_-]{0,50}$",
      message = "Un máximo de 50 caracteres.")
  private String name;

  @Pattern(regexp = "[.,\\w\\s_-]*")
  private String content;

  @Pattern(regexp = "^[\\w\\S_-]*$",
      message = "caracteres alfanuméricos sin espacios.")
  private String image;
}
