package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.rest.request.ActivityRequest;
import com.alkemy.ong.infrastructure.rest.response.ActivityResponse;
import com.alkemy.ong.infrastructure.rest.response.CategoryResponse;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

  public Activity toDomain(ActivityRequest activityRequest) {
    if (activityRequest == null) {
      return null;
    }
    return Activity.builder()
        .name(activityRequest.getName())
        .content(activityRequest.getContent())
        .image(activityRequest.getImage())
        .build();
  }

  public ActivityResponse toResponse(Activity activity) {
    if (activity == null) {
      return null;
    }
    return ActivityResponse.builder()
        .name(activity.getName())
        .content(activity.getContent())
        .image(activity.getImage())
        .build();
  }


}
