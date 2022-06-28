package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.infrastructure.database.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentSpringRepository extends JpaRepository<CommentEntity, Long> {

}
