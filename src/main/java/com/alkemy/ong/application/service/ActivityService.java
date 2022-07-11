package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IActivityRepository;
import com.alkemy.ong.application.service.usecase.ICreateActivityUseCase;
import com.alkemy.ong.domain.Activity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActivityService implements ICreateActivityUseCase {

  private final IActivityRepository activityRepository;

  @Override
  public Activity add(Activity activity) {
    activity.setSoftDelete(false);
    return activityRepository.add(activity);
  }

}
