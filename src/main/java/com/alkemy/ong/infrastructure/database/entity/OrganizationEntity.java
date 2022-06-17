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

<<<<<<< HEAD
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORGANIZATIONS")
public class OrganizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    Column(name = "NAME", nullable = false)
    private String name;

    Column(name = "IMAGE", nullable = false)
    private String image;

    Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

    Column(name = "ADDRESS")
    private String addres;

    Column(name = "IMAGE_URL", nullable = false)
    private String imageUrl;

    Column(name = "PHONE")
    private String phone;

    Column(name = "EMAIL", nullable = false)
    private String email;

    Column(name = "WELCOME_TEXT", nullable = false)
    private String welcomeText;

    Column(name = "ABOUT_US_TEXT")
    private String imageUrl;

    @Column(name = "CREATE_TIMESTAMP")
    @CreationTimestamp
    private Timestamp createTimestamp;

    @Column(name = "SOFT_DELETE")
    private Boolean softDelete;
=======
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "ORGANITAZIONS")
public class OrganizationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "IMAGE")
  private String image;

  @Column(name = "ADDRESS")
  private String address;

  @Column(name = "PHONE")
  private String phone;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "WELCOME_TEXT")
  private String welcomeText;

  @Column(name = "ABOUT_US_TEXT")
  private String aboutUsText;

  @Column(name = "CREATE_TIMESTAMP")
  @CreationTimestamp
  private Timestamp createTimestamp;

  @Column(name = "SOFT_DELETE")
  private Boolean softDelete;
>>>>>>> OT231-18
}
