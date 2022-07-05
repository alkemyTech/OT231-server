package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import com.alkemy.ong.infrastructure.database.mapper.CommentEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.ICommentSpringRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CommentRepository implements ICommentRepository {

  private final ICommentSpringRepository commentSpringRepository;
  private final CommentEntityMapper commentEntityMapper;

  @Override
  @Transactional
  public Comment add(Comment newComment) {
    CommentEntity commentEntity = commentEntityMapper.toEntity(newComment);
    commentEntity.setSoftDelete(false);
    return commentEntityMapper.toDomain(commentSpringRepository.save(commentEntity));
  }

}
