package com.alkemy.ong.infrastructure.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.sql.Timestamp;
import javax.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

  private Long id;

  private String name;

  private String facebookUrl;

  private String instagramUrl;

  private String linkedInUrl;

  private String image;

  private String description;

}
