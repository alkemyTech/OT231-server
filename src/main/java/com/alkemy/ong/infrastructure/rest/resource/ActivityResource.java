package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateActivityUseCase;
import com.alkemy.ong.domain.Activity;
import com.alkemy.ong.infrastructure.rest.mapper.ActivityMapper;
import com.alkemy.ong.infrastructure.rest.request.ActivityRequest;
import com.alkemy.ong.infrastructure.rest.response.ActivityResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityResource {

  @Autowired
  ICreateActivityUseCase createActivityUseCase;

  @Autowired
  ActivityMapper activityMapper;

  @PostMapping(value = "/activities",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<ActivityResponse> create(
      @Valid @RequestBody ActivityRequest activityRequest) {
    Activity activity = activityMapper.toDomain(activityRequest);
    ActivityResponse response = activityMapper.toResponse(createActivityUseCase.add(activity));
    return new ResponseEntity<ActivityResponse>(response, HttpStatus.CREATED);
  }
}
