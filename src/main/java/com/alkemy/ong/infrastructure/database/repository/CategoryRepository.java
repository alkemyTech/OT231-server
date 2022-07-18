package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import com.alkemy.ong.infrastructure.database.mapper.CategoryEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.ICategorySpringRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Component
public class CategoryRepository implements ICategoryRepository {

  private final ICategorySpringRepository categorySpringRepository;
  private final CategoryEntityMapper categoryEntityMapper;

  @Override
  @Transactional
  public Category add(Category newCategory) {
    CategoryEntity categoryEntity = categoryEntityMapper.toEntity(newCategory);
    return categoryEntityMapper.toDomain(categorySpringRepository.save(categoryEntity));
  }

  @Override
  public boolean existsById(Long id) {
    return categorySpringRepository.existsById(id);
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

  @Override
  public Category findBy(Long id) {
    return categoryEntityMapper.toDomain(categorySpringRepository.findByIdAndSoftDeleteFalse(id));
  }

  @Override
  public Category update(Category category) {
    CategoryEntity categoryUpdate = categoryEntityMapper.toEntity(category);
    return categoryEntityMapper.toDomain(categorySpringRepository.save(categoryUpdate));
  }

  public Optional<Category> findById(Long id) {
    Optional<CategoryEntity> categoryEntityOptional = categorySpringRepository.findById(id);
    if (categoryEntityOptional.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(categoryEntityMapper.toDomain(categoryEntityOptional.get()));
  }

  @Override
  public Page<Category> findAll(Pageable pageable) {
    Page<CategoryEntity> categories = categorySpringRepository.findBySoftDeleteFalse(pageable);
    return categoryEntityMapper.toPageDomain(
        categories.getContent(),
        categories.getNumber(),
        categories.getSize(),
        categories.getTotalElements());
  }
}
