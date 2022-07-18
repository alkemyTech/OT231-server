package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {

  @Pattern(regexp = "^[\\p{L}\\s.,!?:;-]*$",
      message = "Name can only contain letters and whitespaces.")
  private String name;

  @Pattern(regexp = "^[\\p{L}\\d.?:/-]*$",
      message = "Image can only contain alphanumerical with no whitespaces.")
  @NotBlank(message = "Image must not be empty.")
  private String image;

  private SocialMediaRequest socialMedia;

  private String description;

}
