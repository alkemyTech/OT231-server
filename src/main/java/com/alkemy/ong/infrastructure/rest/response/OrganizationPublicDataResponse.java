package com.alkemy.ong.infrastructure.rest.response;

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
public class OrganizationPublicDataResponse {

  private String name;
  private String image;
  private String address;
  private String phone;
  private SocialMediaResponse socialMedia;
  private SlideResponse slide;

}
