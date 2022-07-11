package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

  @Pattern(regexp = "^\\p{L}+[\\p{L}\\s]*$",
      message = "Name can only contain letters and whitespaces")
  @Size(max = 50, message = "Name must have a maximum of 50 characters")
  private String name;

  @Pattern(regexp = "^\\p{L}+[\\p{L}\\s]*$",
      message = "Content can only contain letters and whitespaces")
  private String content;

  @Pattern(regexp = "^[\\p{L}\\d.?:/-]*$",
      message = "Image can only contain alphanumerical with no whitespaces.")
  private String image;
}
