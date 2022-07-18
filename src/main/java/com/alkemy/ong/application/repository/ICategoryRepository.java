package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryRepository {

  Category add(Category category);

  boolean existsById(Long id);

  boolean isDeleted(Long id);

  void delete(Long id);

  Category findBy(Long id);

  Category update(Category category);

  Page<Category> findAll(Pageable pageable);

}
