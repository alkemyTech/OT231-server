package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.rest.request.ContactRequest;
import com.alkemy.ong.infrastructure.rest.response.ContactResponse;
import com.alkemy.ong.infrastructure.rest.response.ListContactResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

  public ListContactResponse toResponse(List<Contact> contacts) {
    if (contacts == null || contacts.isEmpty()) {
      return new ListContactResponse(Collections.emptyList());
    }
    List<ContactResponse> contactsResponses = new ArrayList<>(contacts.size());
    for (Contact contact : contacts) {
      contactsResponses.add(toResponseNoMessage(contact));
    }
    return new ListContactResponse(contactsResponses);
  }

  public ContactResponse toResponseNoMessage(Contact contact) {
    if (contact == null) {
      return null;
    }
    return ContactResponse.builder()
        .name(contact.getName())
        .phone(contact.getPhone())
        .email(contact.getEmail())
        .build();
  }
}
