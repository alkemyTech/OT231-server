package com.alkemy.ong.infrastructure.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SLIDES")
public class SlideEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "SLIDE_ID")
  private Long id;

  @Column(name = "IMAGE_URL", nullable = false)
  private String imageUrl;

  @Column(name = "TEXT")
  private String text;

  @Column(name = "POSITION", nullable = false)
  private Integer order;

}
