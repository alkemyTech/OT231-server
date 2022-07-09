package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Category;

public interface IGetCategoryUseCase {

  Category findById(Long id);

}
