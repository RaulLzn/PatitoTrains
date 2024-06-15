package patitotrains.model.repository;

import patitotrains.model.domain.ContainerWagon;
import patitotrains.model.domain.Luggage;
import patitotrains.model.remote.interfaces.ContainerWagonServiceRemote;
import patitotrains.model.repository.entity.ContainerWagonEntity;
import patitotrains.model.repository.entity.LuggageEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;
import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * Repositorio de vagones contenedores
 */
public class ContainerWagonRepository implements ContainerWagonServiceRemote , Serializable {
    private static final String FILE_PATH = "ContainerWagon.Json";
    private final FileJsonAdapter<ContainerWagonEntity> jsonAdapter;

    /**
     * Constructor de la clase
     */
    public ContainerWagonRepository() {
        this.jsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Método que retorna todos los vagones contenedores
     * @return Lista de vagones contenedores
     */
    public List<ContainerWagon> getAllContainerWagons() {
        List<ContainerWagonEntity> containerWagonEntities = jsonAdapter.getObjects(FILE_PATH, ContainerWagonEntity[].class);
        List<ContainerWagon> containerWagons = new LinkedList<>();

        Iterator<ContainerWagonEntity> iterator = containerWagonEntities.iterator();
        while (iterator.hasNext()) {
            containerWagons.add(mapToContainerWagon(iterator.next()));
        }

        return containerWagons;
    }

    /**
     * Método que guarda un vagón contenedor
     * @param containerWagon Vagón contenedor a guardar
     * @return true si se guardó correctamente, false en caso contrario
     */
    public boolean saveContainerWagon(ContainerWagon containerWagon) {
        List<ContainerWagonEntity> containerWagonEntities = jsonAdapter.getObjects(FILE_PATH, ContainerWagonEntity[].class);
        ContainerWagonEntity entity = mapToContainerWagonEntity(containerWagon);
        containerWagonEntities.add(entity);

        return jsonAdapter.writeObjects(FILE_PATH, containerWagonEntities);
    }

    /**
     * Método que mapea un objeto ContainerWagonEntity a un objeto ContainerWagon
     * @param entity Objeto ContainerWagonEntity
     * @return Objeto ContainerWagon
     */
    private ContainerWagon mapToContainerWagon(ContainerWagonEntity entity) {
        ContainerWagon containerWagon = new ContainerWagon(entity.getId());
        LuggageEntity[] luggages = entity.getLuggages();
        Iterator<LuggageEntity> iterator = new Array<>(luggages).iterator();
        while (iterator.hasNext()) {
            containerWagon.getLuggages().add(mapToLuggage(iterator.next()));
        }
        return containerWagon;
    }

    /**
     * Método que mapea un objeto ContainerWagon a un objeto ContainerWagonEntity
     * @param containerWagon Objeto ContainerWagon
     * @return Objeto ContainerWagonEntity
     */
    private ContainerWagonEntity mapToContainerWagonEntity(ContainerWagon containerWagon) {
        ContainerWagonEntity entity = new ContainerWagonEntity(containerWagon.getId());
        Iterator<Luggage> iterator = containerWagon.getLuggages().iterator();
        LuggageEntity[] luggages = new LuggageEntity[containerWagon.getLuggages().size()];
        int index = 0; // Mantener un índice para insertar elementos en el arreglo
        while (iterator.hasNext()) {
            luggages[index++] = new LuggageEntity(iterator.next());
        }
        entity.setLuggages(luggages); // Aquí se asignan los luggages al objeto ContainerWagonEntity
        return entity;
    }

    /**
     * Método que mapea un objeto LuggageEntity a un objeto Luggage
     * @param entity Objeto LuggageEntity
     * @return Objeto Luggage
     */
    private Luggage mapToLuggage(LuggageEntity entity) {
        return new Luggage(entity.getId(), entity.getWeight(), entity.getTicketId());
    }

    /**
     * Método que mapea un objeto Luggage a un objeto LuggageEntity
     * @param luggage Objeto Luggage
     * @return Objeto LuggageEntity
     */
    private LuggageEntity mapToLuggageEntity(Luggage luggage) {
        return new LuggageEntity(luggage.getId(), luggage.getWeight(), luggage.getTicketId());
    }

    /**
     * Método que obtiene un vagón contenedor por ID
     * @param id ID del vagón contenedor
     * @return Objeto ContainerWagon
     */
    public ContainerWagon getContainerWagonById(String id) {
        List<ContainerWagonEntity> containerWagonEntities = jsonAdapter.getObjects(FILE_PATH, ContainerWagonEntity[].class);
        Iterator<ContainerWagonEntity> iterator = containerWagonEntities.iterator();
        while (iterator.hasNext()) {
            ContainerWagonEntity containerWagonEntity = iterator.next();
            if (containerWagonEntity.getId().equals(id)) {
                return mapToContainerWagon(containerWagonEntity);
            }
        }
        return null;
    }

    /**
     * Método que obtiene una lista de vagones contenedores por ID
     * @param ids Lista de IDs de vagones contenedores
     * @return Lista de vagones contenedores
     */
    public List<ContainerWagon> getContainerWagonsByIds(Array<String> ids) {
        List<ContainerWagonEntity> containerWagonEntities = jsonAdapter.getObjects(FILE_PATH, ContainerWagonEntity[].class);
        List<ContainerWagon> containerWagons = new LinkedList<>();
        Iterator<String> iterator = ids.iterator();
        while (iterator.hasNext()) {
            String id = iterator.next();
            Iterator<ContainerWagonEntity> containerWagonIterator = containerWagonEntities.iterator();
            while (containerWagonIterator.hasNext()) {
                ContainerWagonEntity containerWagonEntity = containerWagonIterator.next();
                if (containerWagonEntity.getId().equals(id)) {
                    containerWagons.add(mapToContainerWagon(containerWagonEntity));
                }
            }
        }
        return containerWagons;
    }

    /**
     * Método que agrega un equipaje a un vagón contenedor
     * @param containerWagonId ID del vagón contenedor
     * @param luggage Equipaje a agregar
     * @return true si se agregó correctamente, false en caso contrario
     */
    public boolean addLuggageToContainerWagon(String containerWagonId, Luggage luggage) {
        List<ContainerWagonEntity> containerWagonEntities = jsonAdapter.getObjects(FILE_PATH, ContainerWagonEntity[].class);
        Iterator<ContainerWagonEntity> iterator = containerWagonEntities.iterator();
        while (iterator.hasNext()) {
            ContainerWagonEntity containerWagonEntity = iterator.next();
            if (containerWagonEntity.getId().equals(containerWagonId)) {
                LuggageEntity[] luggages = containerWagonEntity.getLuggages();
                LuggageEntity[] newLuggages = new LuggageEntity[luggages.length + 1];
                for (int i = 0; i < luggages.length; i++) {
                    newLuggages[i] = luggages[i];
                }
                newLuggages[luggages.length] = mapToLuggageEntity(luggage);
                containerWagonEntity.setLuggages(newLuggages);
                return jsonAdapter.writeObjects(FILE_PATH, containerWagonEntities);
            }
        }
        return false;
    }

    /**
     * Método que elimina un vagón contenedor
     * @param id ID del vagón contenedor
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean deleteContainerWagon(String id) {
        List<ContainerWagonEntity> containerWagonEntities = jsonAdapter.getObjects(FILE_PATH, ContainerWagonEntity[].class);
        Iterator<ContainerWagonEntity> iterator = containerWagonEntities.iterator();
        while (iterator.hasNext()) {
            ContainerWagonEntity containerWagonEntity = iterator.next();
            if (containerWagonEntity.getId().equals(id)) {
                containerWagonEntities.remove(containerWagonEntity);
                return jsonAdapter.writeObjects(FILE_PATH, containerWagonEntities);
            }
        }
        return false;
    }

    /**
     * Método que actualiza un vagón contenedor
     * @param containerWagon Vagón contenedor a actualizar
     * @return true si se actualizó correctamente, false en caso contrario
     */
    @Override
    public boolean updateContainerWagon(ContainerWagon containerWagon) throws RemoteException {
        List<ContainerWagonEntity> containerWagonEntities = jsonAdapter.getObjects(FILE_PATH, ContainerWagonEntity[].class);
        Iterator<ContainerWagonEntity> iterator = containerWagonEntities.iterator();
        while (iterator.hasNext()) {
            ContainerWagonEntity containerWagonEntity = iterator.next();
            if (containerWagonEntity.getId().equals(containerWagon.getId())) {
                containerWagonEntity.setLuggages(new LuggageEntity[containerWagon.getLuggages().size()]);
                Iterator<Luggage> luggageIterator = containerWagon.getLuggages().iterator();
                int index = 0;
                while (luggageIterator.hasNext()) {
                    containerWagonEntity.getLuggages()[index++] = new LuggageEntity(luggageIterator.next());
                }
                return jsonAdapter.writeObjects(FILE_PATH, containerWagonEntities);
            }
        }
        return false;
    }

    /**
     * Método que elimina un equipaje de un vagón contenedor
     * @param containerWagonId ID del vagón contenedor
     * @param luggageId ID del equipaje
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean deleteLuggageFromContainerWagon(String containerWagonId, String luggageId) {
        List<ContainerWagonEntity> containerWagonEntities = jsonAdapter.getObjects(FILE_PATH, ContainerWagonEntity[].class);
        Iterator<ContainerWagonEntity> iterator = containerWagonEntities.iterator();
        while (iterator.hasNext()) {
            ContainerWagonEntity containerWagonEntity = iterator.next();
            if (containerWagonEntity.getId().equals(containerWagonId)) {
                LuggageEntity[] luggages = containerWagonEntity.getLuggages();
                LuggageEntity[] newLuggages = new LuggageEntity[luggages.length - 1];
                int index = 0;
                for (int i = 0; i < luggages.length; i++) {
                    if (!luggages[i].getId().equals(luggageId)) {
                        newLuggages[index++] = luggages[i];
                    }
                }
                containerWagonEntity.setLuggages(newLuggages);
                return jsonAdapter.writeObjects(FILE_PATH, containerWagonEntities);
            }
        }
        return false;
    }


}
