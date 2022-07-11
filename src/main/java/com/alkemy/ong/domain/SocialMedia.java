package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class SocialMedia {

  private String facebookUrl;
  private String instagramUrl;
  private String linkedInUrl;

}
