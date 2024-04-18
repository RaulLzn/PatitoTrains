package Main;

import patitotrains.model.domain.ContainerWagon;
import patitotrains.model.repository.ContainerWagonRepository;

import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;



public class ContainersWagonPrueba {
    public static void main(String[] args) {
        // Crear una instancia del repositorio de vagones contenedores
        ContainerWagonRepository containerWagonRepository = new ContainerWagonRepository();

        // Guardar un nuevo vagón contenedor
        ContainerWagon newContainerWagon1 = new ContainerWagon("CW001");
        boolean saveResult1 = containerWagonRepository.saveContainerWagon(newContainerWagon1);
        if (saveResult1) {
            System.out.println("Vagón contenedor 1 guardado correctamente.");
        } else {
            System.out.println("Error al guardar el vagón contenedor 1.");
        }

        // Guardar otro nuevo vagón contenedor
        ContainerWagon newContainerWagon2 = new ContainerWagon("CW002");
        boolean saveResult2 = containerWagonRepository.saveContainerWagon(newContainerWagon2);
        if (saveResult2) {
            System.out.println("Vagón contenedor 2 guardado correctamente.");
        } else {
            System.out.println("Error al guardar el vagón contenedor 2.");
        }

        // Obtener todos los vagones contenedores
        System.out.println("Obteniendo todos los vagones contenedores:");
        List<ContainerWagon> allContainerWagons = containerWagonRepository.getAllContainerWagons();
        printContainerWagonList(allContainerWagons);

        // Eliminar un vagón contenedor por su ID
        String containerWagonIdToDelete = "CW001";
        boolean deleteResult = containerWagonRepository.deleteContainerWagon(containerWagonIdToDelete);
        if (deleteResult) {
            System.out.println("Vagón contenedor con ID " + containerWagonIdToDelete + " eliminado correctamente.");
        } else {
            System.out.println("Error al eliminar el vagón contenedor con ID " + containerWagonIdToDelete + ".");
        }

        // Mostrar todos los vagones contenedores después de la eliminación
        System.out.println("Obteniendo todos los vagones contenedores después de la eliminación:");
        allContainerWagons = containerWagonRepository.getAllContainerWagons();
        printContainerWagonList(allContainerWagons);
    }

    // Método auxiliar para imprimir la lista de vagones contenedores
    private static void printContainerWagonList(List<ContainerWagon> containerWagonList) {
        if (containerWagonList.isEmpty()) {
            System.out.println("No hay vagones contenedores.");
            return;
        }

        System.out.println("Lista de vagones contenedores:");
        Iterator<ContainerWagon> iterator = containerWagonList.iterator();
        while (iterator.hasNext()) {
            ContainerWagon containerWagon = iterator.next();
            System.out.println("ID: " + containerWagon.getId());
        }
    }
}
