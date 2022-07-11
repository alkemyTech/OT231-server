package com.alkemy.ong.infrastructure.rest.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialMediaRequest {

  private String facebookUrl;
  private String instagramUrl;
  private String linkedInUrl;

}
