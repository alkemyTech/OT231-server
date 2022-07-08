package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.INewsRepository;
import com.alkemy.ong.application.service.usecase.ICreateNewsUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteNewsUseCase;
import com.alkemy.ong.application.service.usecase.IGetOneNewUseCase;
import com.alkemy.ong.domain.News;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NewsService implements IDeleteNewsUseCase, ICreateNewsUseCase, IGetOneNewUseCase {

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
    news.setSoftDelete(false);
    return newsRepository.add(news);
  }


  @Override
  public News getOne(Long id) {
    News news = newsRepository.findBy(id);
    if (news == null) {
      throw new RecordNotFoundException("News not found.");
    }
    return news;
  }

}
