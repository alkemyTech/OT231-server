package com.alkemy.ong.infrastructure.rest.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ListSlideResponse {

  private List<SlideResponse> slides;

}
