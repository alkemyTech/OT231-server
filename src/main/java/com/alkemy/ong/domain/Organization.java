package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Organization {

  private Long id;
  private String name;
  private String image;
  private String address;
  private String phone;
  private String email;
  private String welcomeText;
  private String aboutUsText;
  private SocialMedia socialMedia;
  private Slide slide;

}
