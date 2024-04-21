package Main;

import patitotrains.model.domain.ContainerWagon;
import patitotrains.model.domain.Luggage;
import patitotrains.model.repository.ContainerWagonRepository;

public class ContainersWagonPrueba {

    public static void main(String[] args) {
        // Crear un repositorio de vagones contenedores
        ContainerWagonRepository repository = new ContainerWagonRepository();

        // Crear un objeto de vagón contenedor
        ContainerWagon containerWagon = new ContainerWagon("CW001");

        // Crear objetos de equipaje
        Luggage luggage1 = new Luggage("L001", 20, "T001");
        Luggage luggage2 = new Luggage("L002", 15, "T002");

        // Agregar equipaje al vagón contenedor
        containerWagon.getLuggages().add(luggage1);
        containerWagon.getLuggages().add(luggage2);

        // Probar el método saveContainerWagon
        System.out.println("Guardando vagón contenedor...");
        System.out.println("Vagón contenedor guardado correctamente: " + repository.saveContainerWagon(containerWagon));




        // Probar el método getAllContainerWagons
        System.out.println("\nObteniendo todos los vagones contenedores...");
        System.out.println(repository.getAllContainerWagons());

        // Probar el método getContainerWagonById
        System.out.println("\nObteniendo vagón contenedor por ID...");
        System.out.println("Vagón contenedor con ID 'CW001': " + repository.getContainerWagonById("CW001"));

        // Verificar nuevamente los vagones contenedores después de eliminar uno
        System.out.println("\nObteniendo todos los vagones contenedores después de eliminar uno...");
        System.out.println(repository.getAllContainerWagons());

        //Agregar un nuevo equipaje
        Luggage luggage3 = new Luggage("L003", 25, "T003");
        repository.addLuggageToContainerWagon("CW001", luggage3);

        //eliminar equipaje de un vagon contenedor
        repository.deleteLuggageFromContainerWagon("CW001", "L001");

    }
}
