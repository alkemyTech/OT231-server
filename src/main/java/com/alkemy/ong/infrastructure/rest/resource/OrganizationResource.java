package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.IGetOrganizationUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateOrganizationUseCase;
import com.alkemy.ong.domain.Organization;
import com.alkemy.ong.infrastructure.rest.mapper.OrganizationMapper;
import com.alkemy.ong.infrastructure.rest.mapper.UpdateOrganizationMapper;
import com.alkemy.ong.infrastructure.rest.request.UpdateOrganizationRequest;
import com.alkemy.ong.infrastructure.rest.response.OrganizationPublicDataResponse;
import com.alkemy.ong.infrastructure.rest.response.UpdateOrganizationResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganizationResource {

  @Autowired
  private IGetOrganizationUseCase getOrganizationUseCase;

  @Autowired
  private IUpdateOrganizationUseCase updateOrganizationUseCase;

  @Autowired
  private OrganizationMapper organizationMapper;

  @Autowired
  private UpdateOrganizationMapper updateOrganizationMapper;

  @GetMapping(value = "/organization/public", produces = {"application/json"})
  public ResponseEntity<OrganizationPublicDataResponse> getPublicData() {
    return ResponseEntity.ok().body(organizationMapper.toResponse(getOrganizationUseCase.find()));
  }

  @PatchMapping(value = "/organization/public",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<UpdateOrganizationResponse> update(
      @Valid @RequestBody UpdateOrganizationRequest updateOrganizationRequest) {
    Organization organization = updateOrganizationMapper.toDomain(updateOrganizationRequest);
    UpdateOrganizationResponse response = updateOrganizationMapper.toResponse(
        updateOrganizationUseCase.update(organization));
    return ResponseEntity.ok().body(response);
  }

}
