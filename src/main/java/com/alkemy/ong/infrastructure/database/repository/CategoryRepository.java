package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.mapper.CategoryEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.ICategorySpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CategoryRepository implements ICategoryRepository {

  private final ICategorySpringRepository categorySpringRepository;

  private CategoryEntityMapper categoryEntityMapper;

  @Override
  public Category findByName(String name) {
    return categoryEntityMapper.toDomain(categorySpringRepository.findByName(name));
  }

  @Override
  public boolean existsById(Long id) {
    return categorySpringRepository.existsById(id);

  }

  @Override
  public Category add(Category newCategory) {
    CategoryEntity categoryEntity = categoryEntityMapper.toEntity(newCategory);
    categoryEntity.setSoftDelete(false);
    return categoryEntityMapper.toDomain(categorySpringRepository.save(categoryEntity));
  }

  @Override
  public boolean isDeleted(Long id) {
    return categorySpringRepository.isDeleted(id).isPresent();
  }

  @Override
  @Transactional
  public void delete(Long id) {
    categorySpringRepository.softDeleteById(id);
  }
}
