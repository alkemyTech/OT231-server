package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.infrastructure.database.mapper.CategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CategoryRepository {

    private final ICategorySpringRepository categorySpringRepository;
    private final CategoryEntityMapper categoryRepository;



}
