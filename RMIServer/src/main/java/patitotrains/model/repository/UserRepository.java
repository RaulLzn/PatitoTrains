package patitotrains.model.repository;

import patitotrains.model.domain.User;
import patitotrains.model.repository.entity.UserEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

public class UserRepository {

    private final String USER_DATA_FILE = "path/to/user/data.json"; // Ruta del archivo JSON
    private final FileJsonAdapter<UserEntity> fileJsonAdapter; // Adaptador de archivos JSON

    public UserRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene una lista de todos los usuarios.
     *
     * @return Lista de usuarios.
     */
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = fileJsonAdapter.getObjects(USER_DATA_FILE, UserEntity[].class);
        List<User> users = new LinkedList<>();

        Iterator<UserEntity> iterator = userEntities.iterator();
        while (iterator.hasNext()) {
            users.add(mapToUser(iterator.next()));
        }

        return users;
    }

    /**
     * Guarda un usuario en el archivo JSON.
     *
     * @param user Usuario.
     * @return Verdadero si el usuario se guardó correctamente, falso en caso contrario.
     */
    public boolean saveUser(User user) {
        List<UserEntity> userEntities = fileJsonAdapter.getObjects(USER_DATA_FILE, UserEntity[].class);
        UserEntity entity = mapToUserEntity(user);
        userEntities.add(entity);

        return fileJsonAdapter.writeObjects(USER_DATA_FILE, userEntities);
    }

    /**
     * Obtiene un usuario por su ID.
     *
     * @param id ID del usuario.
     * @return Usuario.
     */
    public User getUserById(String id) {
        List<UserEntity> userEntities = fileJsonAdapter.getObjects(USER_DATA_FILE, UserEntity[].class);
        Iterator<UserEntity> iterator = userEntities.iterator();
        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();
            if (userEntity.getId().equals(id)) {
                return mapToUser(userEntity);
            }
        }
        return null;
    }

    /**
     * Elimina un usuario por su ID.
     *
     * @param id ID del usuario.
     * @return Verdadero si el usuario se eliminó correctamente, falso en caso contrario.
     */
    public boolean deleteUser(String id) {
        List<UserEntity> userEntities = fileJsonAdapter.getObjects(USER_DATA_FILE, UserEntity[].class);
        Iterator<UserEntity> iterator = userEntities.iterator();
        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();
            if (userEntity.getId().equals(id)) {
                userEntities.remove(userEntity);
                return fileJsonAdapter.writeObjects(USER_DATA_FILE, userEntities);
            }
        }
        return false;
    }

    /**
     * Actualiza un usuario.
     *
     * @param user Usuario.
     * @return Verdadero si el usuario se actualizó correctamente, falso en caso contrario.
     */
    public boolean updateUser(User user) {
        List<UserEntity> userEntities = fileJsonAdapter.getObjects(USER_DATA_FILE, UserEntity[].class);
        Iterator<UserEntity> iterator = userEntities.iterator();
        while (iterator.hasNext()) {
            UserEntity userEntity = iterator.next();
            if (userEntity.getId().equals(user.getId())) {
                userEntities.remove(userEntity);
                userEntities.add(mapToUserEntity(user));
                return fileJsonAdapter.writeObjects(USER_DATA_FILE, userEntities);
            }
        }
        return false;
    }

    /**
     * Mapea un objeto UserEntity a un objeto User.
     *
     * @param entity Entidad UserEntity.
     * @return Objeto User.
     */
    private User mapToUser(UserEntity entity) {
        // Mapear los datos de la entidad UserEntity a un objeto User
        return new User(entity.getNames(), entity.getLastNames(), entity.getNumbers(), entity.isDisabled(), entity.getId(), entity.getPassword(), entity.getUserName());
    }

    /**
     * Mapea un objeto User a un objeto UserEntity.
     *
     * @param user Objeto User.
     * @return Objeto UserEntity.
     */
    private UserEntity mapToUserEntity(User user) {
        // Mapear los datos del objeto User a una entidad UserEntity
        return new UserEntity(user.getId(), user.getUserName(), user.getPassword(), user.isDisabled(), user.getNames(), user.getLastNames(), user.getNumbers());
    }
}
