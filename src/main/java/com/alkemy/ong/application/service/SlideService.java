package com.alkemy.ong.application.service;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.service.usecase.ICreateSlideUseCase;
import com.alkemy.ong.application.service.usecase.IDeleteSlideUseCase;
import com.alkemy.ong.application.service.usecase.IGetSlideUseCase;
import com.alkemy.ong.application.service.usecase.IListSlideUseCase;
import com.alkemy.ong.application.util.IUploadImage;
import com.alkemy.ong.domain.Slide;
import com.alkemy.ong.infrastructure.database.repository.SlideRepository;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SlideService
    implements IListSlideUseCase, IDeleteSlideUseCase, IGetSlideUseCase, ICreateSlideUseCase {

  private final SlideRepository slideRepository;
  private final IUploadImage uploadImage;

  @Override
  public List<Slide> findAllByOrderByOrder() {
    return slideRepository.findAllByOrderByOrder();
  }

  @Override
  public void delete(Long id) {
    if (!slideRepository.existsById(id)) {
      throw new RecordNotFoundException("Slide not found");
    }
    slideRepository.delete(id);
  }

  @Override
  public Slide findBy(Long id) {
    Slide slide = slideRepository.findBy(id);
    if (slide == null) {
      throw new RecordNotFoundException("Slide not found.");
    }
    return slide;
  }

  @Override
  public Slide create(Slide slide) {
    String imageUrl = uploadSlideImage(slide);
    slide.setImageUrl(imageUrl);
    if (slide.getOrder() == null) {
      slide.setOrder(findAndSetSubsequentOrder());
    }
    return slideRepository.add(slide);
  }

  private String uploadSlideImage(Slide slide) {
    return uploadImage.upload(slide);
  }

  private Integer findAndSetSubsequentOrder() {
    Integer lastKnownOrder = slideRepository.findLastKnownOrder();
    if (lastKnownOrder == null) {
      return 1;
    }
    return lastKnownOrder + 1;
  }

}
