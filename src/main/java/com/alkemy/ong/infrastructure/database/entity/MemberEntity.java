package com.alkemy.ong.infrastructure.database.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBERS")
public class MemberEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "MEMBER_ID")
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "FACEBOOK_URL")
  private String facebookUrl;

  @Column(name = "INSTAGRAM_URL")
  private String instagramUrl;

  @Column(name = "LINKEDIN_URL")
  private String linkedinUrl;

  @Column(name = "IMAGE", nullable = false)
  private String image;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "CREATE_TIMESTAMP")
  @CreationTimestamp 
  private Timestamp createTimestamp;
   
  @Column(name = "SOFT_DELETE")
  private Boolean softDelete;
}
