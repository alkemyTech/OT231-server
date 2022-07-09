package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Activity {

  private String name;
  private String content;
  private String image;
  private Boolean softDelete;
}
