package Main;

import patitotrains.model.domain.ContactPerson;
import patitotrains.model.domain.Passenger;
import patitotrains.model.domain.types.IdType;
import patitotrains.model.repository.ContactPersonRepository;
import patitotrains.model.repository.PassengerRepository;

import raul.Model.array.Array;

public class PassengerAndContactPersonPrueba {
    public static void main(String[] args) {
        // Crear una instancia de IdType para el pasajero y la persona de contacto
        IdType passengerIdType = new IdType("1", "Cedula");
        IdType contactPersonIdType = new IdType("2", "Pasaporte");

        // Crear una instancia de la persona de contacto
        // Crear una instancia de la persona de contacto
        ContactPerson contactPerson = new ContactPerson("1", "Juan", "Perez", new Array<>("123456789"));


        // Crear una instancia del pasajero con su persona de contacto
        Passenger passenger = new Passenger("Juan", "Perez", new Array<>("123456789"), "1", passengerIdType, "Calle 123", contactPerson);

        // Crear instancias de los repositorios
        PassengerRepository passengerRepository = new PassengerRepository();
        ContactPersonRepository contactPersonRepository = new ContactPersonRepository();

        // Guardar la persona de contacto y el pasajero
        boolean contactPersonSaved = contactPersonRepository.saveContactPerson(contactPerson);
        boolean passengerSaved = passengerRepository.savePassenger(passenger);

        // Verificar si los datos se guardaron correctamente
        if (contactPersonSaved && passengerSaved) {
            System.out.println("¡El pasajero y su persona de contacto se guardaron correctamente!");
        } else {
            System.out.println("¡Hubo un problema al intentar guardar el pasajero y/o su persona de contacto!");
        }

        // Obtener el pasajero y su persona de contacto
        Passenger passengerObtained = passengerRepository.getPassengerById("1");
        ContactPerson contactPersonObtained = contactPersonRepository.getContactPersonById("1");
        passengerObtained.setContactPerson(contactPersonObtained);

        // Verificar si el pasajero y su persona de contacto se obtuvieron correctamente
        //Imprimir los datos del pasajero y su persona de contacto
        if (passengerObtained != null && contactPersonObtained != null) {
            System.out.println("Pasajero: " + passengerObtained);
        } else {
            System.out.println("¡Hubo un problema al intentar obtener el pasajero y/o su persona de contacto!");
        }
    }
}
