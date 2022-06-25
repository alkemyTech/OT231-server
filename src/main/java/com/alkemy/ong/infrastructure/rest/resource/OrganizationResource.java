package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IOrganizationPublicDataUseCase;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.rest.mapper.OrganizationMapper;
import com.alkemy.ong.infrastructure.rest.response.ListOrganizationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationResource {

  @Autowired
  private IOrganizationPublicDataUseCase organizationPublicDataUseCase;

  @Autowired
  private OrganizationMapper organizationMapper;

  @GetMapping(value = "/organization/public", produces = {"application/json"})
  public ResponseEntity<ListOrganizationResponse> getPublicData() {
    return ResponseEntity.ok().body(organizationMapper
            .toResponse(organizationPublicDataUseCase.findAll()));
  }
}
