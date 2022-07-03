package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.rest.request.ContactRequest;
import com.alkemy.ong.infrastructure.rest.response.ContactResponse;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

  public Contact toDomain(ContactRequest contactRequest) {
    if (contactRequest == null) {
      return null;
    }
    return Contact.builder()
        .name(contactRequest.getName())
        .phone(contactRequest.getPhone())
        .email(contactRequest.getEmail())
        .message(contactRequest.getMessage())
        .build();
  }

  public ContactResponse toResponse(Contact contact) {
    if (contact == null) {
      return null;
    }

    return ContactResponse.builder()
        .name(contact.getName())
        .phone(contact.getPhone())
        .email(contact.getEmail())
        .message(contact.getMessage())
        .build();
  }
}
