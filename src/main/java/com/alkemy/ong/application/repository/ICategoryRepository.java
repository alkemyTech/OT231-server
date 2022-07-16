package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Category;
import java.util.List;

public interface ICategoryRepository {

  Category add(Category category);

  boolean existsById(Long id);

  boolean isDeleted(Long id);

  void delete(Long id);

  List<Category> findAllActive();

  Category findBy(Long id);

  Category update(Category category);

}
