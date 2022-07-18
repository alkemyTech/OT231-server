package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Comment;

public interface ICommentRepository {

  Comment add(Comment comment);

  Comment findBy(Long id);

  void save(Comment comment);

  Comment update(Comment comment);

}