package patitotrains.model.repository.repositoryInterface;

import patitotrains.model.domain.ContactPerson;

public interface ContactPersonRepositoryInterface {
    ContactPerson findById(String id);
    void save(ContactPerson contactPerson);
    void update(ContactPerson contactPerson);
    void delete(ContactPerson contactPerson);
}
