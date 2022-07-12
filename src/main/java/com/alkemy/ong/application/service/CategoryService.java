package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.application.service.usecase.ICreateCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IGetCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IListCategoryUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateCategoryUseCase;
import com.alkemy.ong.domain.Category;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
public class CategoryService implements IDeleteCategoryUseCase, ICreateCategoryUseCase,
    IListCategoryUseCase, IGetCategoryUseCase, IUpdateCategoryUseCase {

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
  public List<Category> findAll() {
    return categoryRepository.findAllActive();
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

  @Override
  public Category update(Category updateCategory) {
    Category categorySaved = categoryRepository.findBy(updateCategory.getId());
    if (categorySaved == null) {
      throw new RecordNotFoundException("Category not found.");
    }
    updateCategoryValues(updateCategory, categorySaved);
    return categoryRepository.update(categorySaved);
  }

  private void updateCategoryValues(Category updatedCategory, Category categorySave) {
    categorySave.setName(updatedCategory.getName());
    categorySave.setImage(updatedCategory.getImage());
    categorySave.setDescription(updatedCategory.getDescription());
  }
}
