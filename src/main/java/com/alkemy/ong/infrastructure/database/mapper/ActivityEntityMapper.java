package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import org.springframework.stereotype.Component;

@Component
public class ActivityEntityMapper {

  public ActivityEntity toEntity(Activity activity) {
    if (activity == null) {
      return null;
    }
    return ActivityEntity.builder()
        .name(activity.getName())
        .content(activity.getContent())
        .image(activity.getImage())
        .softDelete(activity.getSoftDelete())
        .build();
  }

  public Activity toDomain(ActivityEntity activityEntity) {
    if (activityEntity == null) {
      return null;
    }
    return Activity.builder()
        .name(activityEntity.getName())
        .content(activityEntity.getContent())
        .image(activityEntity.getImage())
        .softDelete(activityEntity.getSoftDelete())
        .build();
  }
}
