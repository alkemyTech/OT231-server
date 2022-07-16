package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateActivityUseCase;
import com.alkemy.ong.application.service.usecase.IUpdateActivityUseCase;
import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.infrastructure.rest.mapper.ActivityMapper;
import com.alkemy.ong.infrastructure.rest.request.ActivityRequest;
import com.alkemy.ong.infrastructure.rest.request.UpdateActivityRequest;
import com.alkemy.ong.infrastructure.rest.response.ActivityResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityResource {

  @Autowired
  private ICreateActivityUseCase createActivityUseCase;

  @Autowired
  private ActivityMapper activityMapper;

  @Autowired
  private IUpdateActivityUseCase updateActivityUseCase;

  @PostMapping(value = "/activities",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<ActivityResponse> create(
      @Valid @RequestBody ActivityRequest activityRequest) {
    Activity activity = activityMapper.toDomain(activityRequest);
    ActivityResponse response = activityMapper.toResponse(createActivityUseCase.add(activity));
    return new ResponseEntity<ActivityResponse>(response, HttpStatus.CREATED);
  }

  @PutMapping(value = "/activities/{id}",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<ActivityResponse> update(
      @PathVariable Long id, @Valid @RequestBody UpdateActivityRequest updateActivityRequest) {
    Activity activity = updateActivityUseCase
        .update(activityMapper.toDomain(id, updateActivityRequest));
    ActivityResponse response = activityMapper.toResponse(activity);
    return ResponseEntity.ok(response);
  }
}
