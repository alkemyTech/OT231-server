package com.alkemy.ong.application.repository;

public interface INewsRepository {

  boolean existsById(Long id);

  boolean isDeleted(Long id);

  void delete(Long id);

}
