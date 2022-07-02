package com.alkemy.ong.application.repository;

import com.alkemy.ong.domain.Contact;
import com.alkemy.ong.infrastructure.database.entity.ContactEntity;

public interface IContactRepository {

  Contact add(Contact contact);
}
