package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Slide;

public interface IGetSlideUseCase {

  Slide findBy(Long id);

}
