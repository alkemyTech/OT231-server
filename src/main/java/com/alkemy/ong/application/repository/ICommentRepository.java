package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Comment;
import java.util.List;

public interface ICommentRepository {

  Comment add(Comment comment);

  Comment findBy(Long id);

  void save(Comment comment);

  List<Comment> findAll();

  Comment update(Comment comment);

}