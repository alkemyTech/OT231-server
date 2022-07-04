package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Slide;
import java.util.List;

public interface ISlideRepository {

  List<Slide> findAllByOrderByOrder();

}
