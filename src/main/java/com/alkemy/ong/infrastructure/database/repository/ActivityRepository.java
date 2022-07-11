package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IActivityRepository;
import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import com.alkemy.ong.infrastructure.database.mapper.ActivityEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.IActivitySpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class ActivityRepository implements IActivityRepository {

  private final IActivitySpringRepository activitySpringRepository;
  private final ActivityEntityMapper activityEntityMapper;

  @Override
  @Transactional
  public Activity add(Activity activity) {
    ActivityEntity activityEntity = activityEntityMapper.toEntity(activity);
    return activityEntityMapper.toDomain(activitySpringRepository.save(activityEntity));
  }

}
