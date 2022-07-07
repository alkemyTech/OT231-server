package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IGetOneCategoryUseCase;
import com.alkemy.ong.domain.Category;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryServiceI implements IDeleteCategoryUseCase, ICreateCategoryUseCase,
    IGetOneCategoryUseCase {

  private final ICategoryRepository categoryRepository;

  @Override
  public Category add(Category newCategory) {
    return categoryRepository.add(newCategory);
  }

  @Override
  public void delete(Long id) {
    if (!categoryRepository.existsById(id) || categoryRepository.isDeleted(id)) {
      throw new RecordNotFoundException("Category not found.");
    }
    categoryRepository.delete(id);
  }

  @Override
  public Category getOne(Long id) {
    if (!categoryRepository.existsById(id)) {
      throw new RecordNotFoundException("Category not found.");
    }
    return categoryRepository.getOne(id);
  }
}
