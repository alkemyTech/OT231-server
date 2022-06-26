package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class CategoryRepository implements ICategoryRepository {

  ICategorySpringRepository iCategorySpringRepository;

  @Override
    public boolean exitsById(Long id) {
        return iCategorySpringRepository.existsById(id);
  }

  @Override
    public boolean isDeleted(Long id) {
        return iCategorySpringRepository.isDeleted(id);
  }

  @Override
  @Transactional
  public void delete(Long id){
        iCategorySpringRepository.sofDeleteById(id);
    }
}
