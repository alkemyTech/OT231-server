package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Slide {

  private String imageUrl;
  //private String text;
  private Integer order;

}
