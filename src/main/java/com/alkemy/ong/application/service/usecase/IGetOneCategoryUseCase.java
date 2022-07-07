package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Category;

public interface IGetOneCategoryUseCase {

  Category getOne(Long id);
}
