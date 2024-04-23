package patitotrains.model.repository;

import patitotrains.model.domain.Luggage;
import patitotrains.model.repository.entity.LuggageEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;

import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.io.Serializable;

/**
 * Repository for luggage.
 */
public class LuggageRepository implements Serializable {
    private static final String FILE_PATH = "RMIServer/src/main/java/database/Luggage.Json";
    private final FileJsonAdapter<LuggageEntity> jsonAdapter;

    public LuggageRepository() {
        this.jsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Get all luggage.
     *
     * @return List of luggage.
     */
    public List<Luggage> getAllLuggage() {
        List<LuggageEntity> luggageEntities = jsonAdapter.getObjects(FILE_PATH, LuggageEntity[].class);
        List<Luggage> luggageList = new LinkedList<>();

        Iterator<LuggageEntity> iterator = luggageEntities.iterator();
        while (iterator.hasNext()) {
            luggageList.add(mapToLuggage(iterator.next()));
        }

        return luggageList;
    }

    /**
     * Save luggage to JSON file.
     *
     * @param luggage Luggage.
     * @return True if luggage was saved successfully, false otherwise.
     */
    public boolean saveLuggage(Luggage luggage) {
        List<LuggageEntity> luggageEntities = jsonAdapter.getObjects(FILE_PATH, LuggageEntity[].class);
        LuggageEntity entity = mapToLuggageEntity(luggage);
        luggageEntities.add(entity);

        return jsonAdapter.writeObjects(FILE_PATH, luggageEntities);
    }

    /**
     * Update luggage in JSON file.
     *
     * @param luggage Luggage.
     * @return True if luggage was updated successfully, false otherwise.
     */
    public boolean updateLuggage(Luggage luggage) {
        List<LuggageEntity> luggageEntities = jsonAdapter.getObjects(FILE_PATH, LuggageEntity[].class);

        Iterator<LuggageEntity> iterator = luggageEntities.iterator();
        while (iterator.hasNext()) {
            LuggageEntity luggageEntity = iterator.next();
            if (luggageEntity.getId().equals(luggage.getId())) {
                luggageEntities.remove(luggageEntity); // Remove old luggage.
                luggageEntities.add(mapToLuggageEntity(luggage));
                return jsonAdapter.writeObjects(FILE_PATH, luggageEntities);
            }
        }

        return false; // Luggage not found
    }

    /**
     * Map luggage to luggage entity.
     *
     * @param entity Luggage entity.
     * @return Luggage.
     */
    private Luggage mapToLuggage(LuggageEntity entity) {
        return new Luggage(entity.getId(), entity.getWeight(), entity.getTicketId());
    }

    /**
     * Map luggage to luggage entity.
     *
     * @param luggage Luggage.
     * @return Luggage entity.
     */
    private LuggageEntity mapToLuggageEntity(Luggage luggage) {
        return new LuggageEntity(luggage.getId(), luggage.getWeight(), luggage.getTicketId());
    }

    /**
     * Delete luggage from JSON file.
     *
     * @param id Luggage ID.
     * @return True if luggage was deleted successfully, false otherwise.
     */
    public boolean deleteLuggage(String id) {
        List<LuggageEntity> luggageEntities = jsonAdapter.getObjects(FILE_PATH, LuggageEntity[].class);
        Iterator<LuggageEntity> iterator = luggageEntities.iterator();
        while (iterator.hasNext()) {
            LuggageEntity luggageEntity = iterator.next();
            if (luggageEntity.getId().equals(id)) {
                luggageEntities.remove(luggageEntity);
                return jsonAdapter.writeObjects(FILE_PATH, luggageEntities);
            }
        }

        return false; // Luggage not found
    }

    /**
     * Get luggage by ID.
     *
     * @param id Luggage ID.
     * @return Luggage.
     */
    public Luggage getLuggageById(String id) {
        List<LuggageEntity> luggageEntities = jsonAdapter.getObjects(FILE_PATH, LuggageEntity[].class);
        Iterator<LuggageEntity> iterator = luggageEntities.iterator();
        while (iterator.hasNext()) {
            LuggageEntity luggageEntity = iterator.next();
            if (luggageEntity.getId().equals(id)) {
                return mapToLuggage(luggageEntity);
            }
        }
        return null;
    }
}
