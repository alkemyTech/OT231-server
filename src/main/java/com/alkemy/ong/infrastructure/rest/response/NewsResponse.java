package com.alkemy.ong.infrastructure.rest.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class NewsResponse {

  private Long id;
  private String name;
  private String text;
  private String image;

}
