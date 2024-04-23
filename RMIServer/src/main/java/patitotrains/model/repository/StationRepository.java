package patitotrains.model.repository;

import patitotrains.model.domain.Station;
import patitotrains.model.repository.entity.StationEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;

import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.io.Serializable;

/**
 * Repositorio para estaciones.
 */
public class StationRepository implements Serializable {
    private static final String FILE_PATH = "RMIServer/src/main/java/database/Station.Json";
    private final FileJsonAdapter<StationEntity> jsonAdapter;

    public StationRepository() {
        this.jsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene todas las estaciones.
     *
     * @return Lista de estaciones.
     */
    public List<Station> getAllStations() {
        List<StationEntity> stationEntities = jsonAdapter.getObjects(FILE_PATH, StationEntity[].class);
        List<Station> stations = new LinkedList<>();

        Iterator<StationEntity> iterator = stationEntities.iterator();
        while (iterator.hasNext()) {
            stations.add(mapToStation(iterator.next()));
        }

        return stations;
    }

    /**
     * Guarda una estación en el archivo JSON.
     *
     * @param station Estación a guardar.
     * @return Verdadero si la estación se guardó correctamente, falso en caso contrario.
     */
    public boolean saveStation(Station station) {
        List<StationEntity> stationEntities = jsonAdapter.getObjects(FILE_PATH, StationEntity[].class);
        StationEntity entity = mapToStationEntity(station);
        stationEntities.add(entity);
        return jsonAdapter.writeObjects(FILE_PATH, stationEntities);
    }

    /**
     * Actualiza una estación en el archivo JSON.
     *
     * @param station Estación a actualizar.
     * @return Verdadero si la estación se actualizó correctamente, falso en caso contrario.
     */
    public boolean updateStation(Station station) {
        List<StationEntity> stationEntities = jsonAdapter.getObjects(FILE_PATH, StationEntity[].class);
        StationEntity entity = mapToStationEntity(station);
        Iterator<StationEntity> iterator = stationEntities.iterator();
        while (iterator.hasNext()) {
            StationEntity stationEntity = iterator.next();
            if (stationEntity.getId().equals(station.getId())) {
                stationEntities.remove(stationEntity);
                stationEntities.add(entity);
                return jsonAdapter.writeObjects(FILE_PATH, stationEntities);
            }
        }
        return false; // Station not found
    }

    /**
     * Elimina una estación del archivo JSON.
     *
     * @param id ID de la estación a eliminar.
     * @return Verdadero si la estación se eliminó correctamente, falso en caso contrario.
     */
    public boolean deleteStation(String id) {
        List<StationEntity> stationEntities = jsonAdapter.getObjects(FILE_PATH, StationEntity[].class);
        Iterator<StationEntity> iterator = stationEntities.iterator();
        while (iterator.hasNext()) {
            StationEntity stationEntity = iterator.next();
            if (stationEntity.getId().equals(id)) {
                stationEntities.remove(stationEntity);
                return jsonAdapter.writeObjects(FILE_PATH, stationEntities);
            }
        }

        return false; // Station not found
    }

    /**
     * Obtiene una estación por su ID.
     *
     * @param id ID de la estación.
     * @return Estación.
     */
    public Station getStationById(String id) {
        List<StationEntity> stationEntities = jsonAdapter.getObjects(FILE_PATH, StationEntity[].class);
        Iterator<StationEntity> iterator = stationEntities.iterator();
        while (iterator.hasNext()) {
            StationEntity stationEntity = iterator.next();
            if (stationEntity.getId().equals(id)) {
                return mapToStation(stationEntity);
            }
        }

        return null;
    }

    /**
     * Mapea una entidad de estación a un objeto de dominio de estación.
     *
     * @param entity Entidad de estación.
     * @return Objeto de dominio de estación.
     */
    private Station mapToStation(StationEntity entity) {
        return new Station(entity.getId(), entity.getName());
    }

    /**
     * Mapea un objeto de dominio de estación a una entidad de estación.
     *
     * @param station Objeto de dominio de estación.
     * @return Entidad de estación.
     */
    private StationEntity mapToStationEntity(Station station) {
        return new StationEntity(station.getId(), station.getName());
    }

    /**
     * Obtiene una lista de estaciones por sus IDs.
     *
     * @param ids IDs de las estaciones.
     * @return Lista de estaciones.
     */
    public LinkedList<Station> getStationsByIds(Array<String> ids){
        LinkedList<Station> stations = new LinkedList<>();
        Iterator<String> idIterator = ids.iterator();
        while (idIterator.hasNext()) {
            String id = idIterator.next();
            Station station = getStationById(id);
            if (station != null) {
                stations.add(station);
            } else {
                throw new RuntimeException("Station not found"); // Manejar el caso en el que no se encuentre una estación con el ID dado
            }
        }
        return stations;
    }
}
