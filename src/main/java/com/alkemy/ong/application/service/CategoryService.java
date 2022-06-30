package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.domain.Category;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryService implements IDeleteCategoryUseCase {

  private final ICategoryRepository categoryRepository;

  @Override
  public Category add(Category newCategory){

  }
  @Override
  public void delete(Long id) {
    if (!categoryRepository.existsById(id) || categoryRepository.isDeleted(id)) {
      throw new RecordNotFoundException("Category not found.");
    }
    categoryRepository.delete(id);
  }
}
