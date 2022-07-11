package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.News;

public interface IGetNewsUseCase {

  News getOne(Long id);

}
