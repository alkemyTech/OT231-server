package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Role;
import com.alkemy.ong.infrastructure.database.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleEntityMapper {

  public Role toDomain(RoleEntity role) {
    if (role == null) {
      return null;
    }
    return Role.builder()
        .id(role.getId())
        .name(role.getName())
        .description(role.getDescription())
        .build();
  }

  public RoleEntity toEntity(Role roleE) {
    if (roleE == null) {
      return null;
    }
    return RoleEntity.builder()
        .id(roleE.getId())
        .name(roleE.getName())
        .description(roleE.getDescription())
        .build();
  }

}
