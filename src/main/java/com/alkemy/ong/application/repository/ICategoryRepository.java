package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryRepository {

  Category add(Category category);

  boolean existsById(Long id);

  boolean isDeleted(Long id);

  void delete(Long id);

  List<Category> findAllActive();

  Optional<Category> findById(Long id);

}
