package com.alkemy.ong.infrastructure.rest.resource;

import com.alkemy.ong.application.service.usecase.ICreateContactUseCase;
import com.alkemy.ong.application.service.usecase.IListContactUseCase;
import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.rest.mapper.ContactMapper;
import com.alkemy.ong.infrastructure.rest.request.ContactRequest;
import com.alkemy.ong.infrastructure.rest.response.ContactResponse;
import com.alkemy.ong.infrastructure.rest.response.ListContactResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactResource {

  @Autowired
  private ICreateContactUseCase createContactUseCase;

  @Autowired
  private ContactMapper contactMapper;

  @Autowired
  private IListContactUseCase listContactUseCase;

  @PostMapping(value = "/contacts",
      produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<ContactResponse> create(
      @Valid @RequestBody ContactRequest contactRequest) {
    Contact contact = contactMapper.toDomain(contactRequest);
    ContactResponse response = contactMapper.toResponse(createContactUseCase.add(contact));
    return new ResponseEntity<ContactResponse>(response, HttpStatus.CREATED);
  }

  @GetMapping(value = "/contacts", produces = {"application/json"})
  public ResponseEntity<ListContactResponse> list() {
    List<Contact> contacts = listContactUseCase.findAll();
    return ResponseEntity.ok().body(contactMapper.toResponse(contacts));
  }
}
