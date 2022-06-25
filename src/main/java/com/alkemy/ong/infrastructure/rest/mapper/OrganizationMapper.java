package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.rest.response.ListOrganizationResponse;
import com.alkemy.ong.infrastructure.rest.response.OrganizationPublicDataResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {
  public ListOrganizationResponse toResponse(List<Organization> organizations) {
    if (organizations == null || organizations.isEmpty()) {
      return new ListOrganizationResponse(Collections.emptyList());
    }

    List<OrganizationPublicDataResponse> organizationPublicDataResponses = new ArrayList<>(
            organizations.size());

    for (Organization organization : organizations) {
      organizationPublicDataResponses.add(new OrganizationPublicDataResponse(
                organization.getName(), organization.getImage(),organization.getAddress(),
                organization.getPhone()));
    }

    return new ListOrganizationResponse(organizationPublicDataResponses);
  }
}
