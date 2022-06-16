package com.alkemy.ong.infrastructure.database.entity;


import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="members")
@Getter
@Setter
@SQLDelete(sql="UPDATE members SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class MemberEntity {

	
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
	
	@SuppressWarnings("unused")
	private boolean deleted = Boolean.FALSE;
	@Temporal(TemporalType.TIMESTAMP)
    private Calendar registDate;


}
