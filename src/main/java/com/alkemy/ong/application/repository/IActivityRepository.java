package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Activity;

public interface IActivityRepository {

  Activity add(Activity activity);

  Activity findBy(Long id);

  Activity update(Activity activity);
}
