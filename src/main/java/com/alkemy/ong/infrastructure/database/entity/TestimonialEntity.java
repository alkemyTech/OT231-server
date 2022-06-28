package com.alkemy.ong.infrastructure.database.entity;

import java.sql.Timestamp;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "TESTIMONIALS")
public class TestimonialEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "TESTIMONIAL_ID")
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "IMAGE")
  private String image;

  @Column(name = "CONTENT")
  private String content;

  @Column(name = "CREATE_TIMESTAMP", updatable = false)
  @CreationTimestamp
  private Timestamp createTimestamp;

  @Column(name = "SOFT_DELETE")
  private Boolean softDelete;
}
