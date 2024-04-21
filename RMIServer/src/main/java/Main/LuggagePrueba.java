package Main;

import patitotrains.model.domain.Luggage;
import patitotrains.model.repository.LuggageRepository;

public class LuggagePrueba {

    public static void main(String[] args) {
        // Crear un repositorio de equipaje
        LuggageRepository repository = new LuggageRepository();

        // Crear objetos de equipaje
        Luggage luggage1 = new Luggage("L001", 20, "T001");
        Luggage luggage2 = new Luggage("L002", 15, "T002");

        // Probar el método saveLuggage
        System.out.println("Guardando equipaje...");
        System.out.println("Equipaje 1 guardado correctamente: " + repository.saveLuggage(luggage1));
        System.out.println("Equipaje 2 guardado correctamente: " + repository.saveLuggage(luggage2));

        // Probar el método getAllLuggage
        System.out.println("\nObteniendo todo el equipaje...");
        System.out.println(repository.getAllLuggage());

        // Probar el método getLuggageById
        System.out.println("\nObteniendo equipaje por ID...");
        System.out.println("Equipaje con ID 'L001': " + repository.getLuggageById("L001"));

        // Probar el método updateLuggage
        System.out.println("\nActualizando equipaje...");
        luggage1.setWeight(25);
        System.out.println("Equipaje actualizado correctamente: " + repository.updateLuggage(luggage1));
        System.out.println("Nuevo equipaje 1: " + repository.getLuggageById("L001"));

        // Probar el método deleteLuggage
        System.out.println("\nEliminando equipaje...");
        System.out.println("Equipaje eliminado correctamente: " + repository.deleteLuggage("L002"));

        // Verificar nuevamente el equipaje después de eliminar uno
        System.out.println("\nObteniendo todo el equipaje después de eliminar uno...");
        System.out.println(repository.getAllLuggage());
    }
}
