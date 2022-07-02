package com.alkemy.ong.infrastructure.rest.request;

import com.alkemy.ong.infrastructure.rest.response.SocialMediaResponse;
import javax.validation.constraints.Email;
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
public class OrganizationRequest {

  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Name can only contain "
            + "alphanumeric")
  private String name;

  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Image can only contain "
            + "alphanumeric")
  private String image;

  private String address;

  private String phone;

  @Email(message = "Email should be valid")
  private String email;

  @Size(max = 50, message = "Welcome text must have a maximum of 150 characters")
  @NotBlank
  private String welcomeText;

  private SocialMediaResponse socialMedia;
}
