package com.alkemy.ong.infrastructure.database.entity;

import java.security.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "members")
@SQLDelete(sql = "UPDATE members SET deleted = true WHERE id = ?")
@Where(clause = "softDeleted = false")
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER_ID")
  private Long fooId;
  
  @Column(name = "NAME")
  @NotNull
  private String name;
  
  @Nullable
  private String facebookUrl;
  
  @Nullable
  private String instagramUrl;
  
  @Nullable
  private String linkedinUrl;
  
  @NotNull
  private String image;
  
  @Nullable
  private String description;
  
  @Column(name = "CREATE_TIMESTAMP")
  @CreationTimestamp
  private Timestamp createTimestamp;

  @Column(name = "SOFT_DELETED")
  private Boolean softDeleted;
}
