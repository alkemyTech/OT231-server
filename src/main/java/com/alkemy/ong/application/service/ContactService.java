package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.application.service.usecase.ICreateContactUseCase;
import com.alkemy.ong.domain.Contact;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ContactService implements ICreateContactUseCase {
  private final IContactRepository contactRepository;

  @Override
  public Contact add(Contact newContact) {
    contactRepository.add(newContact);
    return newContact;
  }

}
