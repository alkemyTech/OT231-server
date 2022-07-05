package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.database.mapper.SlideEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.ISlideSpringRepository;
import java.util.List;
import javax.transaction.Transactional;
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

  @Override
  public boolean existsById(Long id) {
    return slideSpringRepository.existsById(id);
  }

  @Override
  public boolean isDeleted(Long id) {
    return slideSpringRepository.isDeleted(id).isPresent();
  }

  @Override
  @Transactional
  public void delete(Long id) {
    slideSpringRepository.softDeleteById(id);
  }

}
