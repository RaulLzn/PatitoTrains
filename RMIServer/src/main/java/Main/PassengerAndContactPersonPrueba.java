package Main;

import patitotrains.model.domain.ContactPerson;
import patitotrains.model.domain.Passenger;
import patitotrains.model.domain.types.IdType;
import patitotrains.model.repository.PassengerRepository;
import raul.Model.array.Array;

public class PassengerAndContactPersonPrueba {

    public static void main(String[] args) {
        // Crear un repositorio de pasajeros
        PassengerRepository repository = new PassengerRepository();

        // Crear objetos de pasajeros
        Passenger passenger1 = new Passenger("John", "Doe", new Array<>(new String[]{"1234567", "7654321"}), "ID001", new IdType("DNI", "Documento Nacional de Identidad"), "123 Main St", new ContactPerson("Jane", "Doe",  new Array<>(new String[]{"8765432"})));
        Passenger passenger2 = new Passenger("Jane", "Doe", new Array<>(new String[]{"7654321", "1234567"}), "ID002", new IdType("DNI", "Documento Nacional de Identidad"), "456 Elm St", new ContactPerson("John", "Doe",  new Array<>(new String[]{"9876543"})));

        // Probar el método savePassenger
        System.out.println("Guardando pasajeros...");
        System.out.println("Pasajero 1 guardado correctamente: " + repository.savePassenger(passenger1));
        System.out.println("Pasajero 2 guardado correctamente: " + repository.savePassenger(passenger2));

        // Probar el método getAllPassengers
        System.out.println("\nObteniendo todos los pasajeros...");
        System.out.println(repository.getAllPassengers());

        // Probar el método getPassengerById
        System.out.println("\nObteniendo pasajero por ID...");
        System.out.println("Pasajero con ID 'ID001': " + repository.getPassengerById("ID001"));

        // Probar el método updatePassenger
        System.out.println("\nActualizando pasajero...");
        passenger1.setAddress("789 Elm St");
        System.out.println("Pasajero actualizado correctamente: " + repository.updatePassenger(passenger1));
        System.out.println("Nuevo pasajero 1: " + repository.getPassengerById("ID001"));

        // Probar el método deletePassenger
        System.out.println("\nEliminando pasajero...");
        System.out.println("Pasajero eliminado correctamente: " + repository.deletePassenger("ID002"));

        // Verificar nuevamente los pasajeros después de eliminar uno
        System.out.println("\nObteniendo todos los pasajeros después de eliminar uno...");
        System.out.println(repository.getAllPassengers());
    }
}
