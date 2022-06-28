package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.infrastructure.rest.mapper.OrganizationMapper;
import com.alkemy.ong.infrastructure.rest.response.OrganizationPublicDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationResource {

  @Autowired
  private IGetOrganizationUseCase getOrganizationUseCase;

  @Autowired
  private OrganizationMapper organizationMapper;

  @GetMapping(value = "/organization/public", produces = {"application/json"})
  public ResponseEntity<OrganizationPublicDataResponse> getPublicData() {
    return ResponseEntity.ok().body(organizationMapper
        .toResponse(getOrganizationUseCase.find()));
  }
}
