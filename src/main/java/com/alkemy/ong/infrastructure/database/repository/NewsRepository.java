package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.INewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class NewsRepository implements INewsRepository {

  @Autowired
  private final INewsSpringRepository newsSpringRepository;

  @Override
  public boolean existsById(Long id) {
    return newsSpringRepository.existsById(id);
  }

  @Override
  public boolean isDeleted(Long id) {
    return newsSpringRepository.isDeleted(id).isPresent();
  }

  @Override
  @Transactional
  public void delete(Long id) {
    newsSpringRepository.softDeleteById(id);
  }

}
