package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrganizationSocialMedia {

  private String facebookUrl;
  private String instagramUrl;
  private String linkedIndUrl;
}
