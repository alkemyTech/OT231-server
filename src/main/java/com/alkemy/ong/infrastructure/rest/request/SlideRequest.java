package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SlideRequest {
  
  /**
   * @param imageFile
   *    Request Body should contain a Base64 encoded jpeg file.
   */
  @NotBlank(message = "File must not be empty.")
  String imageFile;
  String text;
  Integer order;

}
