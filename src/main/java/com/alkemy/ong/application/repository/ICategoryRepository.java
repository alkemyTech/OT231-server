package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Category;

public interface ICategoryRepository {

  Category add(Category category);

  boolean existsById(Long id);

  boolean isDeleted(Long id);

  void delete(Long id);

  Category getOne(Long id);
}
