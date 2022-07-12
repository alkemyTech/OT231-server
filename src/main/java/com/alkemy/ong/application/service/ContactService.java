package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.application.service.usecase.ICreateContactUseCase;
import com.alkemy.ong.application.service.usecase.IListContactUseCase;
import com.alkemy.ong.domain.Contact;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContactService implements ICreateContactUseCase , IListContactUseCase {

  private final IContactRepository contactRepository;

  @Override
  public Contact add(Contact newContact) {
    return contactRepository.add(newContact);
  }

  @Override
  public List<Contact> findAll() {
    return contactRepository.findAllActive();
  }
}
