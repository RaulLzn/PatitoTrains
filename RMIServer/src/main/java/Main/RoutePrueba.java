package Main;

import patitotrains.model.domain.Route;
import patitotrains.model.domain.Station;
import patitotrains.model.domain.Train;
import patitotrains.model.domain.types.TrainType;
import patitotrains.model.repository.RouteRepository;
import patitotrains.model.repository.StationRepository;
import patitotrains.model.repository.TrainRepository;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.time.LocalTime;

public class RoutePrueba {
    public static void main(String[] args) {
        // Creamos algunos objetos de tren y estación
        Train train1 = new Train("T1", "Expreso del Sol", new TrainType("Tren de pasajeros", "100.0", 200), 20, 14 );

        //guadamos train1 en el archivo
        TrainRepository trainRepository = new TrainRepository();
        trainRepository.saveTrain(train1);

        Station station1 = new Station("S1", "Estación Central");
        Station station2 = new Station("S2", "Estación Norte");
        Station station3 = new Station("S3", "Estación Sur");

        //guarmos las estaciones en el archivo
        StationRepository stationRepository = new StationRepository();
        stationRepository.saveStation(station1);
        stationRepository.saveStation(station2);
        stationRepository.saveStation(station3);

        // Creamos una instancia del repositorio de rutas
        RouteRepository routeRepository = new RouteRepository();


        LocalTime departureTime = LocalTime.of(8, 0);
        LocalTime arrivalTime = LocalTime.of(12, 0);

        //obtenemos los trenes y estaciones usando los repositorios
        LinkedList<Train> trains = new LinkedList<>();
        trains.add(trainRepository.getTrainById("T1"));

        LinkedList<Station> stations = new LinkedList<>();
        stations.add(stationRepository.getStationById("S1"));
        stations.add(stationRepository.getStationById("S2"));
        stations.add(stationRepository.getStationById("S3"));

        Route newRoute = new Route("R1", "Ruta de prueba", trains, stations, departureTime, arrivalTime, 200.0);

        // Probamos el método saveRoute()
        System.out.println("Guardando nueva ruta:");
        boolean saveResult = routeRepository.saveRoute(newRoute);
        System.out.println("Ruta guardada exitosamente: " + saveResult);
        System.out.println();

        // Probamos el método getAllRoutes() nuevamente para verificar que se guardó la nueva ruta
        System.out.println("Listado de todas las rutas después de guardar:");
        List<Route> allRoutes = routeRepository.getAllRoutes();
        Iterator<Route> iterator = allRoutes.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println();

        // Probamos el método getRouteById() para obtener la ruta recién creada por su ID
        System.out.println("Obteniendo ruta por ID:");
        String routeId = "R1";
        Route routeById = routeRepository.getRouteById(routeId);
        System.out.println("Ruta encontrada por ID " + routeId + ": " + routeById);
    }
}
