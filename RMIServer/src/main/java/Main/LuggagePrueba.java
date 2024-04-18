package Main;

import patitotrains.model.domain.Luggage;
import patitotrains.model.repository.LuggageRepository;

import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;



public class LuggagePrueba {

    public static void main(String[] args) {
        // Crear una instancia del repositorio de maletas
        LuggageRepository luggageRepository = new LuggageRepository();



        // Guardar una nueva maleta
        Luggage newLuggage1 = new Luggage("LU001", 20, "TCK001");
        boolean saveResult1 = luggageRepository.saveLuggage(newLuggage1);
        if (saveResult1) {
            System.out.println("Maleta 1 guardada correctamente.");
        } else {
            System.out.println("Error al guardar la maleta 1.");
        }

        // Guardar otra nueva maleta
        Luggage newLuggage2 = new Luggage("LU002", 34, "TCK002");
        boolean saveResult2 = luggageRepository.saveLuggage(newLuggage2);
        if (saveResult2) {
            System.out.println("Maleta 2 guardada correctamente.");
        } else {
            System.out.println("Error al guardar la maleta 2.");
        }

        // Obtener todas las maletas
        System.out.println("Obteniendo todas las maletas:");
        List<Luggage> allLuggage = luggageRepository.getAllLuggage();
        printLuggageList(allLuggage);

        // Eliminar una maleta por su ID
        String luggageIdToDelete = "LU001";
        boolean deleteResult = luggageRepository.deleteLuggage(luggageIdToDelete);
        if (deleteResult) {
            System.out.println("Maleta con ID " + luggageIdToDelete + " eliminada correctamente.");
        } else {
            System.out.println("Error al eliminar la maleta con ID " + luggageIdToDelete + ".");
        }

        // Mostrar todas las maletas después de la eliminación
        System.out.println("Obteniendo todas las maletas después de la eliminación:");
        allLuggage = luggageRepository.getAllLuggage();
        printLuggageList(allLuggage);
    }

    // Método auxiliar para imprimir la lista de maletas
    private static void printLuggageList(List<Luggage> luggageList) {
        if (luggageList.isEmpty()) {
            System.out.println("No hay maletas.");
            return;
        }

        Iterator<Luggage> iterator = luggageList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
