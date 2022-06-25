package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Category;
import com.alkemy.ong.infrastructure.database.entity.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryEntityMapper {

    public Category toDomain(CategoryEntity categoryEntity) {
        if (categoryEntity == null) {
            return null;
        }
        return  Category.builder()
                .name(categoryEntity.getName())
                .description(categoryEntity.getDescription())
                .image(categoryEntity.getImage())
                .build();
    }
}
