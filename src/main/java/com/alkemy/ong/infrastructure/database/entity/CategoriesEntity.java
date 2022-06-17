package com.alkemy.ong.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "CATEGORIES")
public class CategoriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "CREATE_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createTimestamp;

    @Column(name = "SOFT_DELETE")
    private Boolean softDelete;


}
