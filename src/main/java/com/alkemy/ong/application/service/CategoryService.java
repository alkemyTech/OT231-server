package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.CategoryAlreadyExistsException;
import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.exception.UserAlreadyExistsException;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.config.spring.security.common.Role;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CategoryService implements IDeleteCategoryUseCase, ICreateCategoryUseCase {

  private final ICategoryRepository categoryRepository;

  @Override
  public Category add(Category newCategory){
    if (categoryRepository.findByName(newCategory.getName()) != null) {
      throw new CategoryAlreadyExistsException(
              "The Category: " + newCategory.getName() + " is already being used");
    }
    return categoryRepository.add(newCategory);
  }
  @Override
  public void delete(Long id) {
    if (!categoryRepository.existsById(id) || categoryRepository.isDeleted(id)) {
      throw new RecordNotFoundException("Category not found.");
    }
    categoryRepository.delete(id);
  }
}
