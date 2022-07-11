package com.alkemy.ong.infrastructure.database.repository.spring;

import com.alkemy.ong.infrastructure.database.entity.ActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActivitySpringRepository extends JpaRepository<ActivityEntity, Long> {

  ActivityEntity findByIdAndSoftDeleteFalse(Long id);

}
