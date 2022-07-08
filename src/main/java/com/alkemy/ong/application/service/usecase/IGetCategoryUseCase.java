package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Category;
import java.util.Optional;

public interface IGetCategoryUseCase {

  Optional<Category> findById(Long id);
}
