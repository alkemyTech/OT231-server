package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Category {

  private Long id;
  private String name;
  private String description;
  private String image;
  private Boolean softDelete;

}
