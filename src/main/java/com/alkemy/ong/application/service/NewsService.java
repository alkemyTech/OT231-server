package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.usecase.ICreateNewsUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteNewsUseCase;
import com.alkemy.ong.domain.News;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewsService implements IDeleteNewsUseCase, ICreateNewsUseCase {

  private final INewsRepository newsRepository;

  @Override
  public void delete(Long id) {
    if (!newsRepository.existsById(id) || newsRepository.isDeleted(id)) {
      throw new RecordNotFoundException("News not found.");
    }
    newsRepository.delete(id);
  }

  @Override
  public News add(News news) {
    return newsRepository.add(news);
  }

}
