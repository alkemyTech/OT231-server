package com.alkemy.ong.infrastructure.rest.mapper;

import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.rest.request.ContactRequest;
import com.alkemy.ong.infrastructure.rest.response.ContactResponse;
import com.alkemy.ong.infrastructure.rest.response.ListContactResponse;
import com.alkemy.ong.infrastructure.rest.response.field.ContactResponseField;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

  private static final ContactResponseField[] DEFAULT_RESPONSE_MAPPING = {
      ContactResponseField.NAME, ContactResponseField.PHONE_NUMBER, ContactResponseField.EMAIL};

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
    return toResponse(contacts, DEFAULT_RESPONSE_MAPPING);
  }

  public ListContactResponse toResponse(List<Contact> contacts, ContactResponseField... fields) {
    if (contacts == null || contacts.isEmpty()) {
      return new ListContactResponse(Collections.emptyList());
    }

    List<ContactResponse> contactResponses = new ArrayList<>(contacts.size());
    for (Contact contact : contacts) {
      contactResponses.add(toResponse(contact, fields));
    }
    return new ListContactResponse(contactResponses);
  }

  public ContactResponse toResponse(Contact contact, ContactResponseField... fields) {
    ContactResponse contactResponse = new ContactResponse();
    for (ContactResponseField field : fields) {
      switch (field) {
        case NAME:
          contactResponse.setName(contact.getName());
          break;
        case PHONE_NUMBER:
          contactResponse.setPhone(contact.getPhone());
          break;
        case EMAIL:
          contactResponse.setEmail(contact.getEmail());
          break;
        default:
          throw new UnsupportedOperationException(
              MessageFormat.format("Field {0} is unsupported.", field));
      }
    }
    return contactResponse;
  }

}
