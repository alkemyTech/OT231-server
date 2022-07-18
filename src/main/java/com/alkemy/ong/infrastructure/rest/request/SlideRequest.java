package com.alkemy.ong.infrastructure.rest.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SlideRequest {

  @NotBlank(message = "File must not be empty. Expected: Base64 encoded jpeg file.")
  @JsonAlias("imageFile")
  private String base64ImageEncoded;
  private String text;
  private Integer order;

}
