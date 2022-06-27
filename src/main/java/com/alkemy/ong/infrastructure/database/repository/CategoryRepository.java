package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class CategoryRepository implements ICategoryRepository {

  @Autowired
  private final ICategorySpringRepository categorySpringRepository;

  @Override
  public boolean existsById(Long id) {
    return categorySpringRepository.existsById(id);
  }

  @Override
  public boolean isDeleted(Long id) {
    if (categorySpringRepository.isDeleted(id) == null) {
      return false;
    }
    return categorySpringRepository.isDeleted(id).isPresent();
  }

  @Override
  @Transactional
  public void delete(Long id) {
    categorySpringRepository.softDeleteById(id);
  }
}
