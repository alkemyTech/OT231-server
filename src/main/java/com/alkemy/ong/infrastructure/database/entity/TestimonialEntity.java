package com.alkemy.ong.infrastructure.database.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "testimonials")
@Getter
@Setter
@SQLDelete(sql = "UPDATE testimonials SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class TestimonialEntity {
  @NotNull
  private String name;
  @Nullable
  private String image;
  @Nullable
  private String content;
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar registDate;
  @SuppressWarnings("unused")
  private boolean deleted = Boolean.FALSE;
}
