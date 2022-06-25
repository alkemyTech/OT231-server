package com.alkemy.ong.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Category {

    public String name;
    public String description;
    public String image;

}
