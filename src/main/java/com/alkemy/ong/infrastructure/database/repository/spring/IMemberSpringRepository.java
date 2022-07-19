package com.alkemy.ong.infrastructure.database.repository.spring;

import com.alkemy.ong.infrastructure.database.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMemberSpringRepository extends JpaRepository<MemberEntity, Long> {

  Page<MemberEntity> findBySoftDeleteFalse(PageRequest pageable);

}
