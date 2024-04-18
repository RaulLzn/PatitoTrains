package patitotrains.model.repository;

import patitotrains.model.domain.ContactPerson;
import patitotrains.model.repository.entity.ContactPersonEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;

import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

/**
 * Repositorio de personas de contacto.
 */
public class ContactPersonRepository {

    /**
     * Nombre del archivo JSON.
     */
    private final String CONTACT_PERSON_DATA_FILE = "RMIServer/src/main/java/database/ContactPerson.Json";

    /**
     * Adaptador de archivos JSON.
     */
    private final FileJsonAdapter<ContactPersonEntity> fileJsonAdapter;

    /**
     * Constructor de la clase.
     */
    public ContactPersonRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene una lista de todas las personas de contacto.
     *
     * @return Lista de personas de contacto.
     */
    public List<ContactPerson> getAllContactPersons() {
        List<ContactPersonEntity> contactPersonEntities = fileJsonAdapter.getObjects(CONTACT_PERSON_DATA_FILE, ContactPersonEntity[].class);
        List<ContactPerson> contactPersons = new raul.Model.linkedlist.doubly.circular.LinkedList<>();

        Iterator<ContactPersonEntity> iterator = contactPersonEntities.iterator();
        while (iterator.hasNext()) {
            contactPersons.add(mapToContactPerson(iterator.next()));
        }

        return contactPersons;
    }

    /**
     * Guarda una persona de contacto en el archivo JSON.
     *
     * @param contactPerson Persona de contacto.
     * @return Verdadero si la persona de contacto se guardó correctamente, falso en caso contrario.
     */
    public boolean saveContactPerson(ContactPerson contactPerson) {
        List<ContactPersonEntity> contactPersonEntities = fileJsonAdapter.getObjects(CONTACT_PERSON_DATA_FILE, ContactPersonEntity[].class);
        contactPersonEntities.add(mapToContactPersonEntity(contactPerson));

        return fileJsonAdapter.writeObjects(CONTACT_PERSON_DATA_FILE, contactPersonEntities);
    }


    /**
     * Mapea un objeto de tipo ContactPersonEntity a un objeto de tipo ContactPerson.
     *
     * @param entity Entidad de persona de contacto.
     * @return Objeto de persona de contacto.
     */
    private ContactPerson mapToContactPerson(ContactPersonEntity entity) {
        ContactPerson contactPerson = new ContactPerson(
                entity.getId(),
                entity.getNames(),
                entity.getLastNames(),
                entity.getNumbers()
        );

        return contactPerson;
    }

    /**
     * Mapea un objeto de tipo ContactPerson a un objeto de tipo ContactPersonEntity.
     *
     * @param contactPerson Objeto de persona de contacto.
     * @return Entidad de persona de contacto.
     */
    private ContactPersonEntity mapToContactPersonEntity(ContactPerson contactPerson) {
        ContactPersonEntity entity = new ContactPersonEntity(
                contactPerson.getId(),
                contactPerson.getNames(),
                contactPerson.getLastNames(),
                contactPerson.getNumbers()
        );

        return entity;
    }

    /**
     * Obtiene una persona de contacto por su id.
     *
     * @param id Id de la persona de contacto.
     * @return Persona de contacto.
     */
    public ContactPerson getContactPersonById(String id) {
        List<ContactPersonEntity> contactPersonEntities = fileJsonAdapter.getObjects(CONTACT_PERSON_DATA_FILE, ContactPersonEntity[].class);
        Iterator<ContactPersonEntity> iterator = contactPersonEntities.iterator();
        while (iterator.hasNext()) {
            ContactPersonEntity contactPersonEntity = iterator.next();
            if (contactPersonEntity.getId().equals(id)) {
                return mapToContactPerson(contactPersonEntity);
            }
        }

        return null;
    }

    /**
     * Actualiza una persona de contacto.
     *
     * @param contactPerson Persona de contacto.
     * @return Verdadero si la persona de contacto se actualizó correctamente, falso en caso contrario.
     */
    public boolean updateContactPerson(ContactPerson contactPerson) {
        List<ContactPersonEntity> contactPersonEntities = fileJsonAdapter.getObjects(CONTACT_PERSON_DATA_FILE, ContactPersonEntity[].class);
        Iterator<ContactPersonEntity> iterator = contactPersonEntities.iterator();
        while (iterator.hasNext()) {
            ContactPersonEntity contactPersonEntity = iterator.next();
            if (contactPersonEntity.getId().equals(contactPerson.getId())) {
                contactPersonEntity.setNames(contactPerson.getNames());
                contactPersonEntity.setLastNames(contactPerson.getLastNames());
                contactPersonEntity.setNumbers(contactPerson.getNumbers());
                return fileJsonAdapter.writeObjects(CONTACT_PERSON_DATA_FILE, contactPersonEntities);
            }
        }

        return false;
    }

    /**
     * Elimina una persona de contacto por su id.
     *
     * @param id Id de la persona de contacto.
     * @return Verdadero si la persona de contacto se eliminó correctamente, falso en caso contrario.
     */
    public boolean deleteContactPerson(String id) {
        List<ContactPersonEntity> contactPersonEntities = fileJsonAdapter.getObjects(CONTACT_PERSON_DATA_FILE, ContactPersonEntity[].class);
        Iterator<ContactPersonEntity> iterator = contactPersonEntities.iterator();
        while (iterator.hasNext()) {
            ContactPersonEntity contactPersonEntity = iterator.next();
            if (contactPersonEntity.getId().equals(id)) {
                contactPersonEntities.remove(contactPersonEntity);
                return fileJsonAdapter.writeObjects(CONTACT_PERSON_DATA_FILE, contactPersonEntities);
            }
        }

        return false;
    }

}
