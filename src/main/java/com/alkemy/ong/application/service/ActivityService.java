package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IActivityRepository;
import com.alkemy.ong.application.service.usecase.ICreateActivityUseCase;
import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.infrastructure.database.mapper.ActivityEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class ActivityService implements ICreateActivityUseCase {

  private final IActivityRepository activityRepository;

  @Override
  public Activity add(Activity activity) {
    return activityRepository.add(activity);
  }
}
