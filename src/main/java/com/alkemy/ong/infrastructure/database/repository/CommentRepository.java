package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.exception.RecordNotFoundException;
import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import com.alkemy.ong.infrastructure.database.entity.NewsEntity;
import com.alkemy.ong.infrastructure.database.entity.UserEntity;
import com.alkemy.ong.infrastructure.database.mapper.CommentEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.ICommentSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.INewsSpringRepository;
import com.alkemy.ong.infrastructure.database.repository.spring.IUserSpringRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class CommentRepository implements ICommentRepository {

  private final IUserSpringRepository userSpringRepository;
  private final INewsSpringRepository newsSpringRepository;
  private final ICommentSpringRepository commentSpringRepository;
  private final CommentEntityMapper commentEntityMapper;

  @Override
  @Transactional
  public Comment add(Comment newComment) {
    CommentEntity commentEntity = commentEntityMapper.toEntity(newComment);
    commentEntity.setUser(getUserEntity(newComment.getUserId()));
    commentEntity.setNews(getNewsEntity(newComment.getNewsId()));
    commentEntity.setSoftDelete(false);
    return commentEntityMapper.toDomain(commentSpringRepository.save(commentEntity));
  }

  private UserEntity getUserEntity(Long id) {
    UserEntity userEntity = userSpringRepository.findByIdAndSoftDeleteFalse(id);
    if (userEntity == null) {
      throw new RecordNotFoundException("User not found.");
    }
    return userEntity;
  }

  private NewsEntity getNewsEntity(Long id) {
    NewsEntity newsEntity = newsSpringRepository.findByIdAndSoftDeleteFalse(id);
    if (newsEntity == null) {
      throw new RecordNotFoundException("News not found.");
    }
    return newsEntity;
  }

}
