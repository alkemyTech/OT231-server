package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IGetCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IListCategoryUseCase;
import com.alkemy.ong.domain.Category;
import java.util.Optional;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
public class CategoryService implements IDeleteCategoryUseCase, ICreateCategoryUseCase,
    IListCategoryUseCase, IGetCategoryUseCase {

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
  public Page<Category> findAll(Pageable pageable) {
    return categoryRepository.findAll(pageable);
  }

  @Override
  public Category findById(@PathVariable Long id) {
    Optional<Category> optionalCategory = categoryRepository.findById(id);
    if (optionalCategory.isEmpty()
        || Boolean.TRUE.equals(optionalCategory.get().getSoftDelete())) {
      throw new RecordNotFoundException("Category not found.");
    }
    return optionalCategory.get();
  }

}
