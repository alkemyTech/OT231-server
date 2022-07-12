package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Contact;
import java.util.List;

public interface IContactRepository {

  Contact add(Contact contact);

  List<Contact> findAllActive();
}
