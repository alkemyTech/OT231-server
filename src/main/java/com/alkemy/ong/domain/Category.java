package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Category {

    private String name;
    private String description;
    private String image;
}
