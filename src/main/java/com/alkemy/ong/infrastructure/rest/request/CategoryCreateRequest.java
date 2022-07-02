package com.alkemy.ong.infrastructure.rest.request;

import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryCreateRequest {

  @Pattern(regexp = "^\\p{L}+[\\p{L}\\s]*$",
     message = "Name can only contain letters and whitespaces")
  private String name;

  private String description;
  private String image;
}
