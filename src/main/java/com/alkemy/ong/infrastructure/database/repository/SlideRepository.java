package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ISlideRepository;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.database.entity.SlideEntity;
import com.alkemy.ong.infrastructure.database.mapper.SlideEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.ISlideSpringRepository;
import java.util.List;
import java.util.Optional;
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
  public void delete(Long id) {
    slideSpringRepository.deleteById(id);
  }

  @Override
  public Slide findBy(Long id) {
    return slideEntityMapper.toDomain(slideSpringRepository.findById(id));
  }

  @Override
  public Slide add(Slide slide) {
    SlideEntity slideEntity = slideEntityMapper.toEntity(slide);
    return slideEntityMapper.toDomain(Optional.of(slideSpringRepository.save(slideEntity)));
  }

  @Override
  public Integer findLastKnownOrder() {
    return slideSpringRepository.findMaxOrder();
  }

}
