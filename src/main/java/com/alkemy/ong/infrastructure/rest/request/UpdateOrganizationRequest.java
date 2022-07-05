package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UpdateOrganizationRequest {

  @Nullable
  @Pattern(regexp = "^[\\p{L}\\d\\s]*$", message = "Name can only contain "
      + "alphanumeric characters and whitespaces")
  private String name;

  @Nullable
  @Pattern(regexp = "^[\\p{L}\\d.?:/-]*$", message = "Image can only contain "
      + "alphanumeric characters without whitespaces")
  private String image;

  private String address;

  private String phone;

  @Nullable
  @Email(message = "Email should be valid")
  private String email;

  @Nullable
  @Size(max = 50, message = "Welcome text must have a maximum of 50 alphanumeric characters")
  private String welcomeText;

  private SocialMediaRequest socialMedia;

}
