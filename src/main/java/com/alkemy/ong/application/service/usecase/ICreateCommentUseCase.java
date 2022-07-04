package com.alkemy.ong.application.service.usecase;

import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.UserFullName;

public interface ICreateCommentUseCase {

  Comment add(Comment  comment, UserFullName userFullName, String newsName);

}
