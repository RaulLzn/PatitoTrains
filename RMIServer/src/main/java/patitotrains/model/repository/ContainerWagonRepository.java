package patitotrains.model.repository;

import patitotrains.model.domain.ContainerWagon;
import patitotrains.model.repository.entity.ContainerWagonEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

/**
 * Repositorio de vagones contenedores.
 */
public class ContainerWagonRepository {

    /**
     * Nombre del archivo JSON.
     */
    private final String CONTAINER_WAGON_DATA_FILE = "RMIServer/src/main/java/database/ContainerWagon.Json";

    /**
     * Adaptador de archivos JSON.
     */
    private final FileJsonAdapter<ContainerWagonEntity> fileJsonAdapter;

    /**
     * Constructor de la clase.
     */
    public ContainerWagonRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene una lista de todos los vagones contenedores.
     *
     * @return Lista de vagones contenedores.
     */
    public List<ContainerWagon> getAllContainerWagons() {
        List<ContainerWagonEntity> containerWagonEntities = fileJsonAdapter.getObjects(CONTAINER_WAGON_DATA_FILE, ContainerWagonEntity[].class);
        List<ContainerWagon> containerWagons = new raul.Model.linkedlist.doubly.circular.LinkedList<>();

        Iterator<ContainerWagonEntity> iterator = containerWagonEntities.iterator();
        while (iterator.hasNext()) {
            containerWagons.add(mapToContainerWagon(iterator.next()));
        }

        return containerWagons;
    }

    /**
     * Guarda un vagon contenedor en el archivo JSON.
     *
     * @param containerWagon Vagon contenedor.
     * @return Verdadero si el vagon contenedor se guardó correctamente, falso en caso contrario.
     */
    public boolean saveContainerWagon(ContainerWagon containerWagon) {
        List<ContainerWagonEntity> containerWagonEntities = fileJsonAdapter.getObjects(CONTAINER_WAGON_DATA_FILE, ContainerWagonEntity[].class);
        ContainerWagonEntity entity = mapToContainerWagonEntity(containerWagon);
        containerWagonEntities.add(entity);

        return fileJsonAdapter.writeObjects(CONTAINER_WAGON_DATA_FILE, containerWagonEntities);
    }

    /**
     * Mapea un objeto de tipo ContainerWagonEntity a un objeto de tipo ContainerWagon.
     *
     * @param entity Entidad de vagon contenedor.
     * @return Objeto de vagon contenedor.
     */
    private ContainerWagon mapToContainerWagon(ContainerWagonEntity entity) {
        return new ContainerWagon(entity.getId());
    }

    /**
     * Mapea un objeto de tipo ContainerWagon a un objeto de tipo ContainerWagonEntity.
     *
     * @param containerWagon Objeto de vagon contenedor.
     * @return Entidad de vagon contenedor.
     */
    private ContainerWagonEntity mapToContainerWagonEntity(ContainerWagon containerWagon) {
        return new ContainerWagonEntity(containerWagon.getId());
    }

    /**
     * Obtiene un vagon contenedor por su id.
     *
     * @param id Id del vagon contenedor.
     * @return Vagon contenedor.
     */
    public ContainerWagon getContainerWagonById(String id) {
        List<ContainerWagonEntity> containerWagonEntities = fileJsonAdapter.getObjects(CONTAINER_WAGON_DATA_FILE, ContainerWagonEntity[].class);
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
     * Elimina un vagon contenedor por su id.
     *
     * @param id Id del vagon contenedor.
     * @return Verdadero si el vagon contenedor se eliminó correctamente, falso en caso contrario.
     */
    public boolean deleteContainerWagon(String id) {
        List<ContainerWagonEntity> containerWagonEntities = fileJsonAdapter.getObjects(CONTAINER_WAGON_DATA_FILE, ContainerWagonEntity[].class);
        Iterator<ContainerWagonEntity> iterator = containerWagonEntities.iterator();
        while (iterator.hasNext()) {
            ContainerWagonEntity containerWagonEntity = iterator.next();
            if (containerWagonEntity.getId().equals(id)) {
                containerWagonEntities.remove(containerWagonEntity);
                return fileJsonAdapter.writeObjects(CONTAINER_WAGON_DATA_FILE, containerWagonEntities);
            }
        }
        return false;
    }
}
