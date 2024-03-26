package patitotrains.model.repository;

import patitotrains.model.domain.Luggage;
import patitotrains.shared.mysqlAdapter.MySQLAdapter;

import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.list.List;
import raul.Model.util.Iterator.Iterator;

/**
 * Clase que se encarga de la persistencia de los equipajes
 */
public class LuggageRepository {
    /**
     * Adaptador de MySQL
     */
    private final MySQLAdapter<LuggageEntity> mySQLAdapter;
    /**
     * Nombre de la tabla en la base de datos
     */
    private final String tableName;
    /**
     * Arreglo de entidades de equipajes
     */
    private Array<LuggageEntity> luggageEntities;

    /**
     * Constructor de la clase
     */
    public LuggageRepository() {
        this.tableName = "Luggage";
        this.mySQLAdapter = MySQLAdapter.getInstance();
        loadLuggage();
    }

    /**
     * Método que carga los equipajes de la base de datos
     */
    private void loadLuggage() {
        luggageEntities = new Array<>(mySQLAdapter.getObjects(tableName, LuggageEntity[].class));
    }

    /**
     * Método que retorna una lista de equipajes asociados a un vagón
     * @param wagonId ID del vagón
     * @return Lista de equipajes asociados al vagón
     */
    public List<Luggage> getLuggageByIdWagon(String wagonId) {
        List<Luggage> luggageList = new LinkedList<>();
        Iterator<LuggageEntity> iterator = luggageEntities.iterator();
        while (iterator.hasNext()) {
            LuggageEntity luggageEntity = iterator.next();
            if (luggageEntity.getWagonId().equals(wagonId)) {
                Luggage luggage = new Luggage(
                        luggageEntity.getId(),
                        luggageEntity.getContents(),
                        luggageEntity.getWeight(),
                        luggageEntity.getWagonId()
                );
                luggageList.add(luggage);
            }
        }
        return luggageList;
    }

    /**
     * Método que retorna una lista de equipajes asociados a un pasajero
     * @param passengerId ID del pasajero
     * @return Lista de equipajes asociados al pasajero
     */
    public List<Luggage> getLuggageByIdPassenger(String passengerId) {
        List<Luggage> luggageList = new LinkedList<>();
        for (LuggageEntity luggageEntity : luggageEntities) {
            if (luggageEntity.getPassengerId().equals(passengerId)) {
                Luggage luggage = new Luggage(
                        luggageEntity.getId(),
                        luggageEntity.getContents(),
                        luggageEntity.getWeight(),
                        luggageEntity.getWagonId()
                );
                luggageList.add(luggage);
            }
        }
        return luggageList;
    }
}
