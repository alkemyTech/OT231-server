package com.alkemy.ong.infrastructure.database.mapper;

import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.database.entity.ContactEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ContactEntityMapper {

  public Contact toDomain(ContactEntity contactEntity) {
    if (contactEntity == null) {
      return null;
    }
    return Contact.builder()
        .name(contactEntity.getName())
        .phone(contactEntity.getPhone())
        .email(contactEntity.getEmail())
        .message(contactEntity.getMessage())
        .build();
  }

  public List<Contact> toDomain(List<ContactEntity> contactEntities) {
    if (contactEntities == null || contactEntities.isEmpty()) {
      return Collections.emptyList();
    }
    List<Contact> contacts = new ArrayList<>(contactEntities.size());
    for (ContactEntity contactEntity : contactEntities) {
      contacts.add(toDomain(contactEntity));
    }
    return contacts;
  }

  public ContactEntity toEntity(Contact contact) {
    if (contact == null) {
      return null;
    }
    return ContactEntity.builder()
        .name(contact.getName())
        .phone(contact.getPhone())
        .email(contact.getEmail())
        .message(contact.getMessage())
        .build();
  }

}
