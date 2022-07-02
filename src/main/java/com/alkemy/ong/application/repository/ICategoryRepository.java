package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Category;

public interface ICategoryRepository {

  Category findByName(String name);

  boolean existsById(Long id);
  Category add(Category category);
  boolean isDeleted(Long id);

  void delete(Long id);
}
