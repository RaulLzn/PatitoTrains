package Main;

import patitotrains.model.domain.User;
import patitotrains.model.repository.UserRepository;
import raul.Model.array.Array;
import raul.Model.util.Iterator.Iterator;


public class UserPrueba {
    public static void main(String[] args) {
        // Crear repositorio de usuarios
        UserRepository userRepository = new UserRepository();

        // Crear y guardar usuarios
        User user1 = new User("John", "Doe", new Array<>(new String[]{"32154", "25455"}), false, "001", "password123", "john_doe");
        userRepository.saveUser(user1);

        User user2 = new User("Jane", "Smith", new Array<>(new String[]{"2455122"}), false, "002", "password456", "jane_smith");
        userRepository.saveUser(user2);

        // Obtener todos los usuarios y mostrarlos
        System.out.println("Todos los usuarios:");
        Iterator<User> userIterator = userRepository.getAllUsers().iterator();
        while (userIterator.hasNext()) {
            System.out.println(userIterator.next());
        }

        // Obtener un usuario por su ID y mostrarlo
        String userId = "001";
        System.out.println("\nUsuario con ID " + userId + ":");
        User retrievedUser = userRepository.getUserById(userId);
        System.out.println(retrievedUser);

        // Actualizar un usuario y mostrarlo
        user1.setDisabled(true);
        userRepository.updateUser(user1);
        System.out.println("\nUsuario actualizado:");
        System.out.println(userRepository.getUserById(user1.getId()));

        // Eliminar un usuario y mostrar si se eliminó correctamente
        String userToDeleteId = "002";
        System.out.println("\nEliminando usuario con ID " + userToDeleteId + "...");
        boolean deleteSuccess = userRepository.deleteUser(userToDeleteId);
        if (deleteSuccess) {
            System.out.println("Usuario eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el usuario.");
        }

        //verificar si el usuario existe

        String userToCheckUsername = "john_doe";
        String userToCheckPassword = "password123";
        System.out.println("\nVerificando si el usuario con ID " + userToCheckUsername + " existe...");
        boolean userExists = userRepository.verifyUser(userToCheckUsername, userToCheckPassword);
        if (userExists) {
            System.out.println("El usuario existe.");
        } else {
            System.out.println("El usuario no existe.");
        }
    }
}
