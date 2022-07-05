package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.SocialMedia;
import com.alkemy.ong.infrastructure.database.entity.OrganizationEntity;
import org.springframework.stereotype.Component;

@Component
public class OrganizationEntityMapper {

  public Organization toDomain(OrganizationEntity organizationEntity) {
    if (organizationEntity == null) {
      return null;
    }
    return Organization.builder()
        .id(organizationEntity.getId())
        .name(organizationEntity.getName())
        .image(organizationEntity.getImage())
        .address(organizationEntity.getAddress())
        .phone(organizationEntity.getPhone())
        .email(organizationEntity.getEmail())
        .welcomeText(organizationEntity.getWelcomeText())
        .socialMedia(getSocialMedia(organizationEntity))
        .build();
  }

  public OrganizationEntity toEntity(Organization organization) {
    if (organization == null) {
      return null;
    }
    SocialMedia socialMedia = organization.getSocialMedia();
    return OrganizationEntity.builder()
        .id(organization.getId())
        .name(organization.getName())
        .image(organization.getImage())
        .address(organization.getAddress())
        .phone(organization.getPhone())
        .email(organization.getEmail())
        .welcomeText(organization.getWelcomeText())
        .facebookUrl(socialMedia == null ? null : socialMedia.getFacebookUrl())
        .instagramUrl(socialMedia == null ? null : socialMedia.getInstagramUrl())
        .linkedInUrl(socialMedia == null ? null : socialMedia.getLinkedIndUrl())
        .build();
  }

  private SocialMedia getSocialMedia(OrganizationEntity organizationEntity) {
    return SocialMedia.builder()
        .facebookUrl(organizationEntity.getFacebookUrl())
        .instagramUrl(organizationEntity.getInstagramUrl())
        .linkedIndUrl(organizationEntity.getLinkedInUrl())
        .build();
  }


}