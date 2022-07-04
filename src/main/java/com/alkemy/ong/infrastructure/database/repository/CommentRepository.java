package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.ICommentRepository;
import com.alkemy.ong.domain.Comment;
import com.alkemy.ong.domain.UserFullName;
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
    commentEntity.setUser(getUserEntity(newComment.getCreateBy()));
    commentEntity.setNews(getNewsEntity(newComment.getAssociatedNews()));
    commentEntity.setSoftDelete(false);
    return commentEntityMapper.toDomain(commentSpringRepository.save(commentEntity));
  }

  private UserEntity getUserEntity(UserFullName userFullName) {
    return userSpringRepository.findByFirstNameAndLastName(
            userFullName.getFirstName(), userFullName.getLastName());
  }

  private NewsEntity getNewsEntity(String news) {
    return newsSpringRepository.findByName(news);
  }

}
