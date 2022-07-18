package com.alkemy.ong.infrastructure.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

  private Long id;

  private String name;

  private SocialMediaResponse socialMedia;

  private String image;

  private String description;

}
