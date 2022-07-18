package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICategoryRepository {

  Category add(Category category);

  boolean existsById(Long id);

  boolean isDeleted(Long id);

  void delete(Long id);

  Category findBy(Long id);

  Category update(Category category);

  Optional<Category> findById(Long id);

  Page<Category> findAll(Pageable pageable);

}
