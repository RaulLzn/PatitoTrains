package patitotrains.model.repository;

import patitotrains.model.domain.ContactPerson;
import patitotrains.shared.mysqlAdapter.MySQLAdapter;

import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;


/**
 * Clase que se encarga de la persistencia de las personas de contacto
 */
public class ContactPersonRepository {
    /**
     * Adaptador de MySQL
     */
    private final MySQLAdapter<ContactPersonEntity> mySQLAdapter;
    /**
     * Nombre de la tabla en la base de datos
     */
    private final String tableName;
    /**
     * Arreglo de entidades de personas de contacto
     */
    private Array<ContactPersonEntity> contactPersonsEntities;

    /**
     * Constructor de la clase
     */
    public ContactPersonRepository() {
        this.tableName = "ContactPerson";
        this.mySQLAdapter = MySQLAdapter.getInstance();
        loadContactPerson();
    }

    /**
     * Método que carga las personas de contacto de la base de datos
     */
    private void loadContactPerson() {
        contactPersonsEntities = new Array<>(mySQLAdapter.getObjects(tableName, ContactPersonEntity[].class));
    }

    /**
     * Método que retorna una persona de contacto por su id
     * @param idPassenger id de la persona de contacto
     * @return Persona de contacto
     */
    public ContactPerson getPassengerContactByIdPassenger(String idPassenger) {
        Iterator<ContactPersonEntity> iterator = contactPersonsEntities.iterator();
        while (iterator.hasNext()){
            ContactPersonEntity PCE = iterator.next();
            if (PCE.idContactPerson.equals(idPassenger)){
                // Separar la cadena de números de teléfono y convertirla en un array de números
                String[] phoneNumbersStr = PCE.phones.split(",");
                Array<String> phoneNumbers = new Array<>(phoneNumbersStr.length);
                for (String phoneNumberStr : phoneNumbersStr) {
                    phoneNumbers.add(phoneNumberStr.trim());
                }
                return new ContactPerson(PCE.names, PCE.lastNames, phoneNumbers);
            }
        }
        return ContactPerson.getEmptyContactPerson();
    }

    /**
     * Método que añade una persona de contacto a la base de datos
     * @param contactPerson persona de contacto
     * @return true si se añadió correctamente, false en caso contrario
     */
    public boolean addContactPerson(ContactPerson contactPerson) {
        String phones = String.join(",", contactPerson.getPhonesAsString());
        ContactPersonEntity contactPersonEntity = new ContactPersonEntity( null, contactPerson.getNames(), contactPerson.getLastNames(), phones);
        return mySQLAdapter.writeObject(tableName, contactPersonEntity);
    }

    /**
     * Método que añade una lista de personas de contacto a la base de datos
     * @param contactPersons lista de personas de contacto
     * @return true si se añadieron correctamente, false en caso contrario
     */
    public boolean addContactPersons(LinkedList<ContactPerson> contactPersons) {
        int count = 0;
        Iterator<ContactPerson> iterator = contactPersons.iterator();
        while (iterator.hasNext()) {
            ContactPerson contactPerson = iterator.next();
            count++;
        }

        ContactPersonEntity[] contactPersonEntities = new ContactPersonEntity[count];
        int index = 0;
        Iterator<ContactPerson> iterator2 = contactPersons.iterator();
        while (iterator2.hasNext()) {
            ContactPerson contactPerson = iterator2.next();
            String phones = String.join(",", contactPerson.getPhonesAsString());
            contactPersonEntities[index] = new ContactPersonEntity(null, contactPerson.getNames(), contactPerson.getLastNames(), phones);
            index++;
        }
        return mySQLAdapter.writeObjects(tableName, contactPersonEntities);
    }



}
