package com.alkemy.ong.infrastructure.database.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CATEGORIES")
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    



}
