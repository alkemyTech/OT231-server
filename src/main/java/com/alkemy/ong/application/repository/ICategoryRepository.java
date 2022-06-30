package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Category;

public interface ICategoryRepository {

  boolean existsById(Long id);

  Category add(Category user);
  boolean isDeleted(Long id);

  void delete(Long id);
}
