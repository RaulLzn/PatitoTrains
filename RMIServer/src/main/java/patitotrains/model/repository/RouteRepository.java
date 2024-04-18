package patitotrains.model.repository;

import patitotrains.model.domain.Route;
import patitotrains.model.domain.Station;
import patitotrains.model.domain.Train;
import patitotrains.model.repository.entity.RouteEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.time.LocalDateTime;

/**
 * Repositorio de rutas
 */
public class RouteRepository {

    private final String ROUTE_DATA_FILE = "ruta/hacia/el/archivo.json"; // Ruta del archivo JSON
    private final FileJsonAdapter<RouteEntity> fileJsonAdapter; // Adaptador de archivos JSON

    public RouteRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Método para obtener todas las rutas
     * @return Lista de rutas
     */
    public List<Route> getAllRoutes() {
        List<Route> routes = new LinkedList<>();
        List<RouteEntity> routeEntities = fileJsonAdapter.getObjects(ROUTE_DATA_FILE, RouteEntity[].class);
        Iterator<RouteEntity> iterator = routeEntities.iterator();

        while (iterator.hasNext()) {
            RouteEntity routeEntity = iterator.next();
            Route route = mapToRoute(routeEntity);
            routes.add(route);
        }

        return routes;
    }

    /**
     * Método para guardar una ruta
     * @param route Ruta a guardar
     * @return True si la ruta se guardó exitosamente, false en caso contrario
     */
    public boolean saveRoute(Route route) {
        RouteEntity routeEntity = mapToRouteEntity(route);
        List<RouteEntity> routeEntities = fileJsonAdapter.getObjects(ROUTE_DATA_FILE, RouteEntity[].class);
        routeEntities.add(routeEntity);
        return fileJsonAdapter.writeObjects(ROUTE_DATA_FILE, routeEntities);
    }

    /**
     * Método para obtener una ruta por su nombre
     * @param name Nombre de la ruta
     * @return Ruta
     */
    public Route getRouteByName(String name) {
        List<RouteEntity> routeEntities = fileJsonAdapter.getObjects(ROUTE_DATA_FILE, RouteEntity[].class);
        Iterator<RouteEntity> iterator = routeEntities.iterator();

        while (iterator.hasNext()) {
            RouteEntity routeEntity = iterator.next();
            if (routeEntity.getName().equals(name)) {
                return mapToRoute(routeEntity);
            }
        }

        return null;
    }

    /**
     * Método para actualizar una ruta
     * @param route Ruta a actualizar
     * @return True si la ruta se actualizó exitosamente, false en caso contrario
     */
    public boolean updateRoute(Route route) {
        List<RouteEntity> routeEntities = fileJsonAdapter.getObjects(ROUTE_DATA_FILE, RouteEntity[].class);
        Iterator<RouteEntity> iterator = routeEntities.iterator();

        while (iterator.hasNext()) {
            RouteEntity routeEntity = iterator.next();
            if (routeEntity.getName().equals(route.getName())) {
                routeEntities.remove(routeEntity);
                routeEntities.add(mapToRouteEntity(route));
                return fileJsonAdapter.writeObjects(ROUTE_DATA_FILE, routeEntities);
            }
        }

        return false;
    }

    /**
     * Método para eliminar una ruta
     * @param name Nombre de la ruta
     * @return True si la ruta se eliminó exitosamente, false en caso contrario
     */
    public boolean deleteRoute(String name) {
        List<RouteEntity> routeEntities = fileJsonAdapter.getObjects(ROUTE_DATA_FILE, RouteEntity[].class);
        Iterator<RouteEntity> iterator = routeEntities.iterator();

        while (iterator.hasNext()) {
            RouteEntity routeEntity = iterator.next();
            if (routeEntity.getName().equals(name)) {
                routeEntities.remove(routeEntity);
                return fileJsonAdapter.writeObjects(ROUTE_DATA_FILE, routeEntities);
            }
        }

        return false;
    }

    /**
     * Método para mapear una entidad de ruta a una ruta
     * @param entity Entidad de ruta
     * @return Ruta
     */
    private Route mapToRoute(RouteEntity entity) {
        TrainRepository trainRepository = new TrainRepository();
        Train train = trainRepository.getTrainById(entity.getTrainId());
        LinkedList<Station> stations = new LinkedList<>();
        LocalDateTime departureTime = entity.getDepartureTime();
        LocalDateTime arrivalTime = entity.getArrivalTime();
        double routeDistance = entity.getRouteDistance();

        return new Route(entity.getName(), train, stations, departureTime, arrivalTime, routeDistance);
    }

    /**
     * Método para mapear una ruta a una entidad de ruta
     * @param route Ruta
     * @return Entidad de ruta
     */
    private RouteEntity mapToRouteEntity(Route route) {
        String trainId = route.getTrain().getId();
        LinkedList<String> stationIds = new LinkedList<>();
        LocalDateTime departureTime = route.getDepartureTime();
        LocalDateTime arrivalTime = route.getArrivalTime();
        double routeDistance = route.getRouteDistance();

        return new RouteEntity(route.getName(), trainId, stationIds, departureTime, arrivalTime, routeDistance);
    }

}
