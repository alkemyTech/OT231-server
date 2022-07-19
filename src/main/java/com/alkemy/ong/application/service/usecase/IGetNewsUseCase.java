package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.ListComments;
import com.alkemy.ong.domain.News;

public interface IGetNewsUseCase {

  News getOne(Long id);

  ListComments listCommentsByNewsId(Long id);
}