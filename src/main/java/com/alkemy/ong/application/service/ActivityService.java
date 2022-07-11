package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.IActivityRepository;
import com.alkemy.ong.application.service.usecase.ICreateActivityUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateActivityUseCase;
import com.alkemy.ong.domain.Activity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ActivityService implements ICreateActivityUseCase, IUpdateActivityUseCase {

  private final IActivityRepository activityRepository;

  @Override
  public Activity add(Activity activity) {
    activity.setSoftDelete(false);
    return activityRepository.add(activity);
  }

  @Override
  public Activity update(Activity updActivity) {
    Activity activitySaved = activityRepository.findBy(updActivity.getId());
    if (activitySaved == null) {
      throw new RecordNotFoundException("Activity not found.");
    }
    updateValues(updActivity, activitySaved);
    return activityRepository.update(activitySaved);
  }

  private void updateValues(Activity updatedActivity, Activity savedActivity) {
    savedActivity.setName(updatedActivity.getName());
    savedActivity.setContent(updatedActivity.getContent());
    savedActivity.setImage(updatedActivity.getImage());
  }
}
