package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.NotBlank;
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
public class TestimonialRequest {

  @Pattern(regexp = "^\\p{L}+[\\p{L}\\s]*$",
      message = "Name can only contain letters and whitespaces")
  @Size(max = 50, message = "Name should be 50 characters or less.")
  @NotBlank(message = "Name must not be empty.")
  private String name;

  @Pattern(regexp = "^\\p{L}+[\\p{L}\\s]*$",
      message = "Content can only contain letters and whitespaces")
  @Size(max = 150, message = "Content should be 150 characters or less.")
  private String content;

  private String image;


}
