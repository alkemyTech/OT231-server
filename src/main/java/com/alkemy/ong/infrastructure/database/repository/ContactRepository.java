package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.database.entity.ContactEntity;
import com.alkemy.ong.infrastructure.database.mapper.ContactEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.IContactSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class ContactRepository implements IContactRepository {

  private final IContactSpringRepository contactSpringRepository;
  private final ContactEntityMapper contactEntityMapper;

  @Override
  @Transactional
  public ContactEntity add(Contact newContact) {
    ContactEntity contactEntity = contactEntityMapper.toEntity(newContact);
    contactEntity.setDeletedAt(Date );
    return contactSpringRepository.save(contactEntityMapper.toEntity(newContact));
  }
}
