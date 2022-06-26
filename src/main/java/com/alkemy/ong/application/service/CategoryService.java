package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.InvalidCredentialsException;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryService implements IDeleteCategoryUseCase {

  ICategoryRepository categoryRepository;

  @Override
  public void delete(Long id) {
    if (!categoryRepository.exitsById(id) || categoryRepository.isDeleted(id)) {
      throw new InvalidCredentialsException("test");
    }
    categoryRepository.delete(id);
  }
}
