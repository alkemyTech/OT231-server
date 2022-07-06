package com.alkemy.ong.infrastructure.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SlideResponse {

  private String imageUrl;
  private String text;
  private Integer order;

}
