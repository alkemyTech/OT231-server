package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.domain.News;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import com.alkemy.ong.infrastructure.database.mapper.NewsEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.ICategorySpringRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.INewsSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class NewsRepository implements INewsRepository {

  private final INewsSpringRepository newsSpringRepository;
  private final ICategorySpringRepository categorySpringRepository;
  private final NewsEntityMapper newsEntityMapper;

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

  @Override
  public News add(News news) {
    NewsEntity newsEntity = newsEntityMapper.toEntity(news);
    newsEntity.setCategory(getNewsCategoryEntity());
    return newsEntityMapper.toDomain(newsSpringRepository.save(newsEntity));
  }

  private CategoryEntity getNewsCategoryEntity() {
    return categorySpringRepository.findByName("news");
  }

}
