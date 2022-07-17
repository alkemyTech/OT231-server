package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.rest.request.SlideRequest;
import com.alkemy.ong.infrastructure.rest.response.ListSlideResponse;
import com.alkemy.ong.infrastructure.rest.response.SlideResponse;
import com.alkemy.ong.infrastructure.rest.response.field.SlideResponseField;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

  private static final SlideResponseField[] DEFAULT_RESPONSE_MAPPING = {
      SlideResponseField.IMAGE_URL, SlideResponseField.ORDER};

  public ListSlideResponse toResponse(List<Slide> slides) {
    return toResponse(slides, DEFAULT_RESPONSE_MAPPING);
  }

  public ListSlideResponse toResponse(List<Slide> slides, SlideResponseField... fields) {
    if (slides == null || slides.isEmpty()) {
      return new ListSlideResponse(Collections.emptyList());
    }

    List<SlideResponse> slideResponses = new ArrayList<>(slides.size());
    for (Slide slide : slides) {
      slideResponses.add(toResponse(slide, fields));
    }
    return new ListSlideResponse(slideResponses);
  }

  public SlideResponse toResponse(Slide slide) {
    return toResponse(slide, DEFAULT_RESPONSE_MAPPING);
  }

  public SlideResponse toResponse(Slide slide, SlideResponseField... fields) {
    SlideResponse slideResponse = new SlideResponse();
    for (SlideResponseField field : fields) {
      switch (field) {
        case IMAGE_URL:
          slideResponse.setImageUrl(slide.getImageUrl());
          break;
        case TEXT:
          slideResponse.setText(slide.getText());
          break;
        case ORDER:
          slideResponse.setOrder(slide.getOrder());
          break;
        default:
          throw new UnsupportedOperationException(
              MessageFormat.format("Field {0} is unsupported.", field));
      }
    }
    return slideResponse;
  }

  public Slide toDomain(SlideRequest slideRequest) {
    if (slideRequest == null) {
      return null;
    }
    return Slide.builder()
        .base64File(slideRequest.getImageFile())
        .text(slideRequest.getText())
        .order(slideRequest.getOrder())
        .build();
  }

}
