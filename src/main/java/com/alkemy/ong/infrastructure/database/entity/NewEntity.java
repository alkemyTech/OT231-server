package com.alkemy.ong.infrastructure.database.entity;

import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="NEWS")
public class NewEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "NEW_ID")
  private Long id;
  @Column(name = "NAME",nullable = false)
  private String name;
  @Column(name = "CONTENT",nullable = false)
  private String content;
  @Column(name = "IMAGE",nullable = false)
  private String image;
  @Column(name = "CREATE_TIMESTAMP",nullable = false)
  @CreationTimestamp
  private Timestamp createTimeStamp;
  @Column(name = "SOFT_DELETE", nullable = false)
  private boolean softDelete = false;
  @ManyToOne
  @JoinColumn(name = "CATEGORY_ID", nullable = false)
  private CategoryEntity categoryEntities;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Timestamp getCreateTimeStamp() {
    return createTimeStamp;
  }

  public void setCreateTimeStamp(Timestamp createTimeStamp) {
    this.createTimeStamp = createTimeStamp;
  }

  public boolean isSoftDelete() {
    return softDelete;
  }

  public void setSoftDelete(boolean softDelete) {
    this.softDelete = softDelete;
  }

  public CategoryEntity getCategoryEntities() {
    return categoryEntities;
  }

  public void setCategoryEntities(
      CategoryEntity categoryEntities) {
    this.categoryEntities = categoryEntities;
  }
}