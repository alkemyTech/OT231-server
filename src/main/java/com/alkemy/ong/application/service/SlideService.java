package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.service.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.database.repository.SlideRepository;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SlideService implements IListSlideUseCase, IDeleteSlideUseCase {


  private final SlideRepository slideRepository;

  @Override
  public List<Slide> findAllByOrderByOrder() {
    return slideRepository.findAllByOrderByOrder();
  }

  @Override
  public void delete(Long id) {
    if(!slideRepository.existsById(id) || slideRepository.isDeleted(id)){
      throw new RecordNotFoundException("Slide not found");
    }
    slideRepository.delete(id);
  }
}
