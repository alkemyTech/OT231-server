package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Comment;

import java.util.Optional;

public interface ICommentRepository {

  Comment add(Comment  comment);

  Optional<Comment> findById(Long id);

  void save(Comment comment);
}