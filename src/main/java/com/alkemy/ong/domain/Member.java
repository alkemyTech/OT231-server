package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Member {

  private Long id;
  private String name;
  private String facebookUrl;
  private String instagramUrl;
  private String linkedInUrl;
  private String image;
  private String description;
  private Boolean softDelete;

}
