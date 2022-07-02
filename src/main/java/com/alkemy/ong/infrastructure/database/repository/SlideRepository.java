package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.database.mapper.SlideEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.ISlideSpringRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SlideRepository implements ISlideRepository {

  private final ISlideSpringRepository slideSpringRepository;
  private final SlideEntityMapper slideEntityMapper;

  @Override
  public List<Slide> findAllByOrderByOrder() {
    return slideEntityMapper.toDomain(slideSpringRepository.findAllByOrderByOrder());
  }

}
