package com.alkemy.ong.application.service;

import com.alkemy.ong.application.repository.IContactRepository;
import com.alkemy.ong.application.service.usecase.ICreateContactUseCase;
import com.alkemy.ong.application.service.usecase.IListContactUseCase;
import com.alkemy.ong.application.util.ISendEmail;
import com.alkemy.ong.application.util.template.ConfirmationEmailTemplate;
import com.alkemy.ong.domain.Contact;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ContactService implements ICreateContactUseCase, IListContactUseCase {

  private final IContactRepository contactRepository;

  private final ISendEmail sendEmail;

  @Override
  public Contact add(Contact newContact) {
    Contact contact = contactRepository.add(newContact);
    sendConfirmationMail(contact);
    return contact;
  }

  private void sendConfirmationMail(Contact contact) {
    try {
      ConfirmationEmailTemplate confirmationEmailTemplate = new ConfirmationEmailTemplate(contact);
      sendEmail.execute(confirmationEmailTemplate);
    } catch (Exception e) {
      log.error("Something went wrong sending the email. Reason: " + e.getMessage());
    }
  }

  @Override
  public List<Contact> findAll() {
    List<Contact> contacts = contactRepository.findAllActive();
    return contacts.stream()
        .distinct()
        .collect(Collectors.toList());
  }

}

