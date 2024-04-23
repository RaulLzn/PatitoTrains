package Main;

import patitotrains.model.domain.ContactPerson;
import patitotrains.model.domain.Passenger;
import patitotrains.model.domain.PassengerWagon;
import patitotrains.model.domain.types.IdType;
import patitotrains.model.repository.PassengerWagonRepository;

import raul.Model.array.Array;

public class PassengerWagonPrueba {

    public static void main(String[] args) {
        // Crear un repositorio de vagones de pasajeros
        PassengerWagonRepository repository = new PassengerWagonRepository();

        // Crear un objeto de vagón de pasajeros
        PassengerWagon passengerWagon = new PassengerWagon("PW001", true);

        // Crear objetos de pasajeros
        Passenger passenger1 = new Passenger("John", "Doe", new Array<>(new String[]{"1234567", "7654321"}), "P001", new IdType("DNI", "Documento Nacional de Identidad"), "123 Main St", new ContactPerson( "Jane", "Doe", new Array<>(new String[]{"1234567", "7654321"})));
        Passenger passenger2 = new Passenger("Jane", "Smith", new Array<>(new String[]{"1234567", "7654321"}), "P002", new IdType("DNI", "Documento Nacional de Identidad"), "456 Elm St", new ContactPerson("John", "Smith", new Array<>(new String[]{"1234567", "7654321"})));

        // Agregar pasajeros al vagón de pasajeros
        passengerWagon.getPremiumPassengers().add(passenger1);
        passengerWagon.getExecutivePassengers().add(passenger2);

        // Probar el método savePassengerWagon
        System.out.println("Guardando vagón de pasajeros...");
        System.out.println("Vagón de pasajeros guardado correctamente: " + repository.savePassengerWagon(passengerWagon));

        // Probar el método getPassengerWagonById
        System.out.println("\nObteniendo vagón de pasajeros por ID...");
        System.out.println("Vagón de pasajeros con ID 'PW001': " + repository.getPassengerWagonById("PW001"));

        //mostrar todos los pasajeros de un vagón de pasajeros, de todas las categorías
        System.out.println("\nObteniendo todos los pasajeros de un vagón de pasajeros...");
        System.out.println(repository.getAllPassengersFromPassengerWagon("PW001"));

        //Mostar todos los pasajeros de una categoría de un vagón de pasajeros
        System.out.println("\nObteniendo todos los pasajeros de una categoría de un vagón de pasajeros...");
        System.out.println(repository.getAllPassengersFromPassengerWagonCategory("PW001", "Premium"));



        //mostrar todos los vagones de pasajeros
        System.out.println("\nObteniendo todos los vagones de pasajeros...");
        System.out.println(repository.getAllPassengerWagons());


    }
}
