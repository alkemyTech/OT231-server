package com.alkemy.ong.infrastructure.database.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "ROLES")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name= "NAME", nullable = false)
    private String name;

    @Column(name= "DESCRIPTION")
    private String description;

    @Column(name="CREATE_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createTimestamp;
}