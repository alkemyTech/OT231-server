package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class News {

  private Long id;
  private String name;
  private String text;
  private String image;
  private boolean softDelete;
  private Slide slide;

}
