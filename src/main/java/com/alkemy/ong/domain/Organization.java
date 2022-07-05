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
  private String email;
  private String welcomeText;
  private String aboutUsText;
  private SocialMedia socialMedia;

}
