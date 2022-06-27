package com.alkemy.ong.application.repository;


public interface ITestimonialRepository {

  Boolean existById(Long id);

  Boolean isDeleted(Long id);

  void delete(Long id);
}
