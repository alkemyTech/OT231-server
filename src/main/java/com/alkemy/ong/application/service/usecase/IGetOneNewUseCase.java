package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.News;

public interface IGetOneNewUseCase {

  News getOne(Long id);

}
