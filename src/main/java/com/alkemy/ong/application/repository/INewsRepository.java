package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.ListComments;
import com.alkemy.ong.domain.News;

public interface INewsRepository {

  boolean existsById(Long id);

  boolean isDeleted(Long id);

  void delete(Long id);

  News add(News news);

  News findBy(Long id);

  ListComments getCommentsNews(Long id);
}