package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.usecase.IDeleteNewsUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewsService implements IDeleteNewsUseCase {

  private final INewsRepository newsRepository;

  @Override
  public void delete(Long id) {
    if (!newsRepository.existsById(id) || newsRepository.isDeleted(id)) {
      throw new RecordNotFoundException("News not found.");
    }
    newsRepository.delete(id);
  }

}
