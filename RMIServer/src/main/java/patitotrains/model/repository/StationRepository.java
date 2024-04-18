package patitotrains.model.repository;

import patitotrains.model.domain.Station;
import patitotrains.model.repository.entity.StationEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;

import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;
import raul.Model.linkedlist.doubly.circular.LinkedList;

/**
 * Repositorio de estaciones.
 */
public class StationRepository {

    private final String STATION_DATA_FILE = "RMIServer/src/main/java/database/Station.Json"; // Ruta del archivo JSON
    private final FileJsonAdapter<StationEntity> fileJsonAdapter; // Adaptador de archivos JSON

    /**
     * Constructor de la clase.
     */
    public StationRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene una lista de todas las estaciones.
     *
     * @return Lista de estaciones.
     */
    public List<Station> getAllStations() {
        List<StationEntity> stationEntities = fileJsonAdapter.getObjects(STATION_DATA_FILE, StationEntity[].class);
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
        List<StationEntity> stationEntities = fileJsonAdapter.getObjects(STATION_DATA_FILE, StationEntity[].class);
        StationEntity entity = mapToStationEntity(station);
        stationEntities.add(entity);

        return fileJsonAdapter.writeObjects(STATION_DATA_FILE, stationEntities);
    }

    /**
     * Obtiene una estación por su ID.
     *
     * @param id ID de la estación.
     * @return Estación correspondiente al ID especificado.
     */
    public Station getStationById(String id) {
        List<StationEntity> stationEntities = fileJsonAdapter.getObjects(STATION_DATA_FILE, StationEntity[].class);
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
     * Actualiza una estación.
     *
     * @param station Estación a actualizar.
     * @return Verdadero si la estación se actualizó correctamente, falso en caso contrario.
     */
    public boolean updateStation(Station station) {
        List<StationEntity> stationEntities = fileJsonAdapter.getObjects(STATION_DATA_FILE, StationEntity[].class);
        Iterator<StationEntity> iterator = stationEntities.iterator();
        while (iterator.hasNext()) {
            StationEntity stationEntity = iterator.next();
            if (stationEntity.getId().equals(station.getId())) {
                stationEntity.setName(station.getName());
                return fileJsonAdapter.writeObjects(STATION_DATA_FILE, stationEntities);
            }
        }
        return false;
    }

    /**
     * Elimina una estación por su ID.
     *
     * @param id ID de la estación a eliminar.
     * @return Verdadero si la estación se eliminó correctamente, falso en caso contrario.
     */
    public boolean deleteStation(String id) {
        List<StationEntity> stationEntities = fileJsonAdapter.getObjects(STATION_DATA_FILE, StationEntity[].class);
        Iterator<StationEntity> iterator = stationEntities.iterator();
        while (iterator.hasNext()) {
            StationEntity stationEntity = iterator.next();
            if (stationEntity.getId().equals(id)) {
                stationEntities.remove(stationEntity);
                return fileJsonAdapter.writeObjects(STATION_DATA_FILE, stationEntities);
            }
        }
        return false;
    }

    /**
     * Mapea un objeto StationEntity a un objeto Station.
     *
     * @param entity Objeto StationEntity.
     * @return Objeto Station.
     */
    private Station mapToStation(StationEntity entity) {
        return new Station(entity.getId(), entity.getName());
    }

    /**
     * Mapea un objeto Station a un objeto StationEntity.
     *
     * @param station Objeto Station.
     * @return Objeto StationEntity.
     */
    private StationEntity mapToStationEntity(Station station) {
        return new StationEntity(station.getId(), station.getName());
    }
}
