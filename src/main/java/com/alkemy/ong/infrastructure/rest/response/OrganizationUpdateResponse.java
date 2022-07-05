package com.alkemy.ong.infrastructure.rest.response;

import com.alkemy.ong.infrastructure.rest.request.SocialMediaRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationUpdateResponse {
  private String name;
  private String image;
  private String email;
  private String welcomeText;
  private String address;
  private String phone;
  private SocialMediaResponse socialMedia;
}
