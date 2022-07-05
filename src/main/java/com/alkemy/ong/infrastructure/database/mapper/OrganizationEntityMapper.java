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
        .name(organizationEntity.getName())
        .image(organizationEntity.getImage())
        .address(organizationEntity.getAddress())
        .phone(organizationEntity.getPhone())
        .email(organizationEntity.getEmail())
        .welcomeText(organizationEntity.getWelcomeText())
        .socialMedia(getSocialMedia(organizationEntity))
        .build();
  }

  public OrganizationEntity toEntity(Organization updateOrganization) {
    if (updateOrganization == null) {
      return null;
    }
    return OrganizationEntity.builder()
            .name(updateOrganization.getName())
            .image(updateOrganization.getImage())
            .address(updateOrganization.getAddress())
            .phone(updateOrganization.getPhone())
            .email(updateOrganization.getEmail())
            .welcomeText(updateOrganization.getWelcomeText())
            .facebookUrl(updateOrganization.getSocialMedia().getFacebookUrl())
            .instagramUrl(updateOrganization.getSocialMedia().getInstagramUrl())
            .linkedInUrl(updateOrganization.getSocialMedia().getLinkedIndUrl())
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