package patitotrains.model.repository;

import patitotrains.model.domain.Luggage;
import patitotrains.model.repository.entity.LuggageEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

/**
 * Repositorio para gestionar las maletas.
 */
public class LuggageRepository {

    private final String LUGGAGE_DATA_FILE = "RMIServer/src/main/java/database/Luggage.Json"; // Ruta del archivo JSON
    private final FileJsonAdapter<LuggageEntity> fileJsonAdapter; // Adaptador de archivos JSON

    /**
     * Constructor de la clase.
     */
    public LuggageRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene una lista de todas las maletas.
     *
     * @return Lista de maletas.
     */
    public List<Luggage> getAllLuggage() {
        List<LuggageEntity> luggageEntities = fileJsonAdapter.getObjects(LUGGAGE_DATA_FILE, LuggageEntity[].class);
        List<Luggage> luggageList = new raul.Model.linkedlist.doubly.circular.LinkedList<>();

        Iterator<LuggageEntity> iterator = luggageEntities.iterator();
        while (iterator.hasNext()) {
            luggageList.add(mapToLuggage(iterator.next()));
        }

        return luggageList;
    }

    /**
     * Guarda una maleta en el archivo JSON.
     *
     * @param luggage Maleta a guardar.
     * @return Verdadero si la maleta se guardó correctamente, falso en caso contrario.
     */
    public boolean saveLuggage(Luggage luggage) {
        List<LuggageEntity> luggageEntities = fileJsonAdapter.getObjects(LUGGAGE_DATA_FILE, LuggageEntity[].class);
        LuggageEntity entity = mapToLuggageEntity(luggage);
        luggageEntities.add(entity);

        return fileJsonAdapter.writeObjects(LUGGAGE_DATA_FILE, luggageEntities);
    }

    /**
     * Mapea un objeto LuggageEntity a un objeto Luggage.
     *
     * @param entity Entidad de maleta.
     * @return Objeto de maleta.
     */
    private Luggage mapToLuggage(LuggageEntity entity) {
        return new Luggage(entity.getId(), entity.getWeight(), entity.getTicketId());
    }

    /**
     * Mapea un objeto Luggage a un objeto LuggageEntity.
     *
     * @param luggage Maleta.
     * @return Entidad de maleta.
     */
    private LuggageEntity mapToLuggageEntity(Luggage luggage) {
        return new LuggageEntity(luggage.getId(), luggage.getWeight(), luggage.getTicketId());
    }

    /**
     * Obtiene una maleta por su id.
     *
     * @param id Id de la maleta.
     * @return Maleta.
     */
    public Luggage getLuggageById(String id) {
        List<LuggageEntity> luggageEntities = fileJsonAdapter.getObjects(LUGGAGE_DATA_FILE, LuggageEntity[].class);
        Iterator<LuggageEntity> iterator = luggageEntities.iterator();
        while (iterator.hasNext()) {
            LuggageEntity luggageEntity = iterator.next();
            if (luggageEntity.getId().equals(id)) {
                return mapToLuggage(luggageEntity);
            }
        }
        return null;
    }

    /**
     * Elimina una maleta por su id.
     *
     * @param id Id de la maleta a eliminar.
     * @return Verdadero si la maleta se eliminó correctamente, falso en caso contrario.
     */
    public boolean deleteLuggage(String id) {
        List<LuggageEntity> luggageEntities = fileJsonAdapter.getObjects(LUGGAGE_DATA_FILE, LuggageEntity[].class);
        Iterator<LuggageEntity> iterator = luggageEntities.iterator();
        while (iterator.hasNext()) {
            LuggageEntity luggageEntity = iterator.next();
            if (luggageEntity.getId().equals(id)) {
                luggageEntities.remove(luggageEntity);
                return fileJsonAdapter.writeObjects(LUGGAGE_DATA_FILE, luggageEntities);
            }
        }
        return false;
    }
}
