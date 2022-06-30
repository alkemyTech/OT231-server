package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Organization {

  private String name;
  private String image;
  private String address;
  private String phone;
  private String facebookUrl;
  private String instagramUrl;
  private String linkedIndUrl;
  private SocialMedia socialMedia;
}
