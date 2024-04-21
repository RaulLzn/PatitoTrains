package patitotrains.model.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import patitotrains.model.domain.Route;
import patitotrains.model.domain.Station;
import patitotrains.model.domain.Train;
import patitotrains.model.repository.entity.RouteEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;

import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.time.LocalTime;

/**
 * Repositorio para rutas.
 */
public class RouteRepository {
    private static final String FILE_PATH = "RMIServer/src/main/java/database/Route.Json";
    private final FileJsonAdapter<RouteEntity> jsonAdapter;
    private Gson gson;

    public RouteRepository() {
        this.jsonAdapter = FileJsonAdapter.getInstance();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
                .create();
    }

    /**
     * Obtiene todas las rutas.
     *
     * @return Lista de rutas.
     */
    public List<Route> getAllRoutes() {
        List<RouteEntity> routeEntities = jsonAdapter.getObjects(FILE_PATH, RouteEntity[].class);
        List<Route> routes = new LinkedList<>();

        Iterator<RouteEntity> iterator = routeEntities.iterator();
        while (iterator.hasNext()) {
            routes.add(mapToRoute(iterator.next()));
        }

        return routes;
    }

    /**
     * Guarda una ruta en el archivo JSON.
     *
     * @param route Ruta a guardar.
     * @return Verdadero si la ruta se guardó correctamente, falso en caso contrario.
     */
    public boolean saveRoute(Route route) {
        List<RouteEntity> routeEntities = jsonAdapter.getObjects(FILE_PATH, RouteEntity[].class);
        RouteEntity entity = mapToRouteEntity(route);
        routeEntities.add(entity);
        return jsonAdapter.writeObjects(FILE_PATH, routeEntities);
    }

    /**
     * Actualiza una ruta en el archivo JSON.
     *
     * @param route Ruta a actualizar.
     * @return Verdadero si la ruta se actualizó correctamente, falso en caso contrario.
     */
    public boolean updateRoute(Route route) {
        List<RouteEntity> routeEntities = jsonAdapter.getObjects(FILE_PATH, RouteEntity[].class);
        RouteEntity entity = mapToRouteEntity(route);
        Iterator<RouteEntity> iterator = routeEntities.iterator();
        while (iterator.hasNext()) {
            RouteEntity routeEntity = iterator.next();
            if (routeEntity.getId().equals(route.getId())) {
                routeEntities.remove(routeEntity);
                routeEntities.add(entity);
                return jsonAdapter.writeObjects(FILE_PATH, routeEntities);
            }
        }
        return false; // Route not found
    }

    /**
     * Elimina una ruta del archivo JSON.
     *
     * @param id ID de la ruta a eliminar.
     * @return Verdadero si la ruta se eliminó correctamente, falso en caso contrario.
     */
    public boolean deleteRoute(String id) {
        List<RouteEntity> routeEntities = jsonAdapter.getObjects(FILE_PATH, RouteEntity[].class);
        Iterator<RouteEntity> iterator = routeEntities.iterator();
        while (iterator.hasNext()) {
            RouteEntity routeEntity = iterator.next();
            if (routeEntity.getId().equals(id)) {
                routeEntities.remove(routeEntity);
                return jsonAdapter.writeObjects(FILE_PATH, routeEntities);
            }
        }

        return false; // Route not found
    }

    /**
     * Obtiene una ruta por su ID.
     *
     * @param id ID de la ruta.
     * @return Ruta.
     */
    public Route getRouteById(String id) {
        List<RouteEntity> routeEntities = jsonAdapter.getObjects(FILE_PATH, RouteEntity[].class);
        Iterator<RouteEntity> iterator = routeEntities.iterator();
        while (iterator.hasNext()) {
            RouteEntity routeEntity = iterator.next();
            if (routeEntity.getId().equals(id)) {
                return mapToRoute(routeEntity);
            }
        }

        return null;
    }

    private Route mapToRoute(RouteEntity entity) {
        LinkedList<Train> trains = new LinkedList<>();
        LinkedList<Station> stations = new LinkedList<>();

        // Lógica para obtener los objetos de tren y estación basados en sus IDs
        TrainRepository trainRepository = new TrainRepository();
        StationRepository stationRepository = new StationRepository();

        for (String trainId : entity.getTrainIds()) {
            Train train = trainRepository.getTrainById(trainId);
            if (train != null) {
                trains.add(train);
            } else {
                throw new RuntimeException("Train not found"); // Manejar el caso en el que no se encuentre un tren con el ID dado
            }
        }

        for (String stationId : entity.getStationIds()) {
            Station station = stationRepository.getStationById(stationId);
            if (station != null) {
                stations.add(station);
            } else {
                throw new RuntimeException("Station not found"); // Manejar el caso en el que no se encuentre una estación con el ID dado
            }
        }

        LocalTime departureTime = LocalTime.parse(entity.getDepartureTime());
        LocalTime arrivalTime = LocalTime.parse(entity.getArrivalTime());

        // Se crea la instancia de Route con las listas de trenes y estaciones obtenidas
        return new Route(entity.getId(), entity.getName(), trains, stations, departureTime, arrivalTime, entity.getRouteDistance());
    }

    private RouteEntity mapToRouteEntity(Route route) {
        RouteEntity entity = new RouteEntity(route.getId(), route.getName(), null, null, route.getDepartureTime().toString(), route.getArrivalTime().toString(), route.getRouteDistance(), route.isDisabled());

        // Mapear los IDs de los trenes
        entity.setTrainIds(new String[route.getTrains().size()]);
        Iterator<Train> trainIterator = route.getTrains().iterator();
        for (int i = 0; trainIterator.hasNext(); i++) {
            entity.getTrainIds()[i] = trainIterator.next().getId();
        }

        // Mapear los IDs de las estaciones
        entity.setStationIds(new String[route.getStations().size()]);
        Iterator<Station> stationIterator = route.getStations().iterator();
        for (int i = 0; stationIterator.hasNext(); i++) {
            entity.getStationIds()[i] = stationIterator.next().getId();
        }

        return entity;
    }

    public boolean disableRoute(String id) {
        List<RouteEntity> routeEntities = jsonAdapter.getObjects(FILE_PATH, RouteEntity[].class);
        Iterator<RouteEntity> iterator = routeEntities.iterator();
        while (iterator.hasNext()) {
            RouteEntity routeEntity = iterator.next();
            if (routeEntity.getId().equals(id)) {
                routeEntity.setDisabled(true);
                return jsonAdapter.writeObjects(FILE_PATH, routeEntities);
            }
        }

        return false; // Route not found
    }

    public boolean enableRoute(String id) {
        List<RouteEntity> routeEntities = jsonAdapter.getObjects(FILE_PATH, RouteEntity[].class);
        Iterator<RouteEntity> iterator = routeEntities.iterator();
        while (iterator.hasNext()) {
            RouteEntity routeEntity = iterator.next();
            if (routeEntity.getId().equals(id)) {
                routeEntity.setDisabled(false);
                return jsonAdapter.writeObjects(FILE_PATH, routeEntities);
            }
        }

        return false; // Route not found
    }


}
