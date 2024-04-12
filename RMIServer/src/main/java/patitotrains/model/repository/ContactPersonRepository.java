package patitotrains.model.repository;

import patitotrains.model.domain.ContactPerson;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;

import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.list.List;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que se encarga de la persistencia de las personas de contacto
 */
public class ContactPersonRepository {
    /**
     * Ruta del archivo JSON para almacenar las personas de contacto
     */
    private static final String CONTACT_PERSON_FILE_PATH = "contactPersons.json";

    /**
     * Adaptador de archivos JSON
     */
    private final FileJsonAdapter<ContactPerson> fileJsonAdapter;

    /**
     * Constructor de la clase
     */
    public ContactPersonRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Método que carga las personas de contacto desde el archivo JSON
     *
     * @return Lista de personas de contacto cargadas
     */
    public LinkedList<ContactPerson> getContactPersons() {
        List<ContactPerson> contactPersons = fileJsonAdapter.getObjects(CONTACT_PERSON_FILE_PATH, ContactPerson[].class);
        return (LinkedList<ContactPerson>) contactPersons;
    }

    /**
     * Método para agregar una persona de contacto al archivo JSON
     *
     * @param newContactPerson Nueva persona de contacto a agregar
     * @return Verdadero si la operación fue exitosa, falso en caso contrario
     */
    public boolean addContactPerson(ContactPerson newContactPerson) {
        try {
            LinkedList<ContactPerson> contactPersons = getContactPersons();
            contactPersons.add(newContactPerson);
            return fileJsonAdapter.writeObjects(CONTACT_PERSON_FILE_PATH, contactPersons.toList());
        } catch (Exception e) {
            Logger.getLogger(ContactPersonRepository.class.getName()).log(Level.SEVERE, "Error adding contact person", e);
            return false;
        }
    }

    /**
     * Método para agregar varias personas de contacto al archivo JSON
     *
     * @param newContactPersons Lista de nuevas personas de contacto a agregar
     * @return Verdadero si la operación fue exitosa, falso en caso contrario
     */
    public boolean addContactPersons(LinkedList<ContactPerson> newContactPersons) {
        try {
            LinkedList<ContactPerson> contactPersons = getContactPersons();
            contactPersons.add(newContactPersons);
            return fileJsonAdapter.writeObjects(CONTACT_PERSON_FILE_PATH, contactPersons);
        } catch (Exception e) {
            Logger.getLogger(ContactPersonRepository.class.getName()).log(Level.SEVERE, "Error adding contact persons", e);
            return false;
        }
    }
}
