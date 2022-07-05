package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IOrganizationRepository;
import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateOrganizationUseCase;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.domain.SocialMedia;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrganizationService implements IGetOrganizationUseCase, IUpdateOrganizationUseCase {

  private final IOrganizationRepository organizationRepository;

  @Override
  public Organization find() {
    return organizationRepository.find();
  }

  @Override
  public Organization update(Organization updateOrganization) {
    return organizationRepository.update(updateValues(updateOrganization));
  }

  private Organization updateValues(Organization updateOrganization) {
    Organization savedOrganization = find();
    updateSocialMedia(savedOrganization, updateOrganization.getSocialMedia());
    updateTexts(savedOrganization, updateOrganization);
    updateBasicInformation(savedOrganization, updateOrganization);
    return savedOrganization;
  }

  private void updateBasicInformation(Organization savedOrganization,
      Organization updateOrganization) {
    String name = updateOrganization.getName();
    if (name != null) {
      savedOrganization.setName(name);
    }

    String image = updateOrganization.getImage();
    if (image != null) {
      savedOrganization.setImage(image);
    }

    String email = updateOrganization.getEmail();
    if (email != null) {
      savedOrganization.setEmail(email);
    }

    String phone = updateOrganization.getPhone();
    if (phone != null) {
      savedOrganization.setPhone(phone);
    }

    String address = updateOrganization.getAddress();
    if (address != null) {
      savedOrganization.setAddress(address);
    }
  }

  private void updateTexts(Organization savedOrganization, Organization updateOrganization) {
    String welcomeText = updateOrganization.getWelcomeText();
    if (welcomeText != null) {
      savedOrganization.setWelcomeText(welcomeText);
    }

    String aboutUsText = updateOrganization.getAboutUsText();
    if (aboutUsText != null) {
      savedOrganization.setAboutUsText(aboutUsText);
    }
  }

  private void updateSocialMedia(Organization savedOrganization, SocialMedia updateSocialMedia) {
    if (updateSocialMedia != null) {
      String facebookUrl = updateSocialMedia.getFacebookUrl();
      if (facebookUrl != null) {
        savedOrganization.getSocialMedia().setFacebookUrl(facebookUrl);
      }

      String instagramUrl = updateSocialMedia.getInstagramUrl();
      if (instagramUrl != null) {
        savedOrganization.getSocialMedia().setInstagramUrl(instagramUrl);
      }

      String linkedInUrl = updateSocialMedia.getLinkedIndUrl();
      if (linkedInUrl != null) {
        savedOrganization.getSocialMedia().setLinkedIndUrl(linkedInUrl);
      }
    }
  }

}
