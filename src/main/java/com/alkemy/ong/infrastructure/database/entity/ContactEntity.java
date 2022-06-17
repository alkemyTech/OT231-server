package com.alkemy.ong.infrastructure.database.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTACTS")
public class ContactEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "CONTACT_ID")
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "PHONE_NUMBER")
  private String phone;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @Column(name = "MESSAGE", nullable = false)
  private String message;

  @Column(name = "SOFT_DELETE")
  private Date deletedAt = null;

}
