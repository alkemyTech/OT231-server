package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICategoryRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@RequiredArgsConstructor
@Component
public class CategoryRepository implements ICategoryRepository {

  ICategorySpringRepository categorySpringRepository;

  @Override
    public boolean exitsById(Long id) {
    return categorySpringRepository.existsById(id);
  }

  @Override
    public boolean isDeleted(Long id) {
    return categorySpringRepository.isDeleted(id);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    categorySpringRepository.sofDeleteById(id);
  }
}
