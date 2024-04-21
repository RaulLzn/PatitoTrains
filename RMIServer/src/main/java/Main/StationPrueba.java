package Main;

import patitotrains.model.domain.Station;
import patitotrains.model.repository.StationRepository;

import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;



public class StationPrueba {
    public static void main(String[] args) {
        // Crear una instancia del repositorio
        StationRepository repository = new StationRepository();

        // Probar el método getAllStations()
        System.out.println("Obteniendo todas las estaciones:");
        List<Station> stations = repository.getAllStations();
        Iterator<Station> iterator = stations.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();

        // Crear algunas estaciones para probar los métodos saveStation() y updateStation()
        Station newStation1 = new Station("1", "Estación 1");
        Station newStation2 = new Station("2", "Estación 2");

        // Probar el método saveStation()
        System.out.println("Guardando una nueva estación:");
        boolean saved = repository.saveStation(newStation1);
        if (saved) {
            System.out.println("Estación guardada correctamente.");
        } else {
            System.out.println("Error al guardar la estación.");
        }
        System.out.println();

        // Probar el método updateStation()
        System.out.println("Actualizando una estación existente:");
        boolean updated = repository.updateStation(newStation2);
        if (updated) {
            System.out.println("Estación actualizada correctamente.");
        } else {
            System.out.println("Error al actualizar la estación.");
        }
        System.out.println();

        //obtener todas las estaciones
        System.out.println("Obteniendo todas las estaciones:");
        List<Station> stations2 = repository.getAllStations();
        Iterator<Station> iterator2 = stations2.iterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.next());
        }

        // Probar el método deleteStation()
        System.out.println("Eliminando una estación:");
        String stationIdToDelete = "3"; // Suponiendo que existe una estación con este ID
        boolean deleted = repository.deleteStation(stationIdToDelete);
        if (deleted) {
            System.out.println("Estación eliminada correctamente.");
        } else {
            System.out.println("Error al eliminar la estación.");
        }
        System.out.println();

        // Probar el método getStationById()
        System.out.println("Obteniendo una estación por ID:");
        String stationIdToFind = "S1"; // Suponiendo que existe una estación con este ID
        Station foundStation = repository.getStationById(stationIdToFind);
        if (foundStation != null) {
            System.out.println("Estación encontrada: " + foundStation);
        } else {
            System.out.println("Estación no encontrada.");
        }
    }
}
