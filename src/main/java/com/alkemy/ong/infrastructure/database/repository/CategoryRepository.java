package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICategoryRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.ICategorySpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CategoryRepository implements ICategoryRepository {

  private final ICategorySpringRepository categorySpringRepository;

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
}
