package com.alkemy.ong.infrastructure.database.repository;

import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.database.entity.ContactEntity;
import com.alkemy.ong.infrastructure.database.mapper.ContactEntityMapper;
import com.alkemy.ong.infrastructure.database.repository.spring.IContactSpringRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class ContactRepository implements IContactRepository {

  private final IContactSpringRepository contactSpringRepository;
  private final ContactEntityMapper contactEntityMapper;

  @Override
  @Transactional
  public Contact add(Contact newContact) {
    ContactEntity contactEntity = contactEntityMapper.toEntity(newContact);
    return contactEntityMapper.toDomain(contactSpringRepository.save(contactEntity));
  }

  @Override
  public List<Contact> findAllActive() {
    return contactEntityMapper.toDomain(contactSpringRepository.findByDeletedAtNull());
  }
}
