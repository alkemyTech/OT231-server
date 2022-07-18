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
  private SocialMedia socialMedia;
  private String image;
  private String description;
  private Boolean softDelete;

}
