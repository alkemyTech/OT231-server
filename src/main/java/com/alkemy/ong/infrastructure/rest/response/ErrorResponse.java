package com.alkemy.ong.infrastructure.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

  @Schema(example = "404")
  private int statusCode;
  @Schema(example = "Invalid input data.")
  private String message;
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @Schema(example = "Some error")
  private List<String> moreInfo;

}
