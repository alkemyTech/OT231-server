package com.alkemy.ong.infrastructure.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkemy.ong.infrastructure.database.entity.MemberEntity;

@Repository
public interface IMemberRepository extends JpaRepository<MemberEntity, Long> {

}
