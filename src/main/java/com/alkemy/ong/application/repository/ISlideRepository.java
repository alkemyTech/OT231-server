package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Slide;
import java.util.List;

public interface ISlideRepository {

  List<Slide> findAllByOrderByOrder();

  boolean existsById(Long id);

  void delete(Long id);

  Slide findBy(Long id);

  Slide add(Slide slide);

  Integer findLastKnownOrder();
}
