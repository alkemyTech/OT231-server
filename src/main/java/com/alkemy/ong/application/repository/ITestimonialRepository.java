package com.alkemy.ong.application.repository;


public interface ITestimonialRepository {

  boolean existById(Long id);

  boolean isDeleted(Long id);

  void delete(Long id);
}
