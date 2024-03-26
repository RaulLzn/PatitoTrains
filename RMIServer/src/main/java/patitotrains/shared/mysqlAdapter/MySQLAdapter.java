package patitotrains.shared.mysqlAdapter;

import database.MySQLConnection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import raul.Model.array.Array;
import raul.Model.util.Iterator.Iterator;


/**
 * Adaptador para la base de datos MySQL
 * Implementa la interfaz FileDatabaseInterface
 * @param <E> Tipo de objeto a manejar
 */
public class MySQLAdapter<E> implements FileDatabaseInterface<E> {
    /**
     * Instancia única de la clase
     */
    private static MySQLAdapter<?> instance;
    /**
     * Conexión a la base de datos MySQL
     */
    private MySQLConnection mysqlConnection;

    /**
     * Constructor privado
     */
    private MySQLAdapter() {
        this.mysqlConnection = new MySQLConnection();
    }

    /**
     * Obtener la instancia única de la clase
     * @param <E> Tipo de objeto a manejar
     * @return Instancia única de la clase
     */
    public static synchronized <E> MySQLAdapter<E> getInstance() {
        if (instance == null) {
            instance = new MySQLAdapter<>();
        }
        return (MySQLAdapter<E>) instance;
    }

    /**
     * Obtener un objeto de la base de datos
     * @param tableName Nombre de la tabla
     * @param classOfT Clase del objeto
     * @return Objeto de tipo E
     */
    @Override
    public E getObject(String tableName, Class<E> classOfT) {
        E object = null;
        String selectQuery = createSelectQuery(tableName);

        try (Connection connection = mysqlConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            if (resultSet.next()) {
                object = mapResultSetToObject(resultSet, classOfT);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return object;
    }

    /**
     * Obtener un arreglo de objetos de la base de datos
     * @param tableName Nombre de la tabla
     * @param classOfT Clase del objeto
     * @return Arreglo de objetos de tipo E
     */
    @Override
    public E[] getObjects(String tableName, Class<E[]> classOfT) {
        E[] objects = null;
        String selectQuery = createSelectQuery(tableName);

        try (Connection connection = mysqlConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {
            int rowCount = resultSet.last() ? resultSet.getRow() : 0;
            resultSet.beforeFirst();

            // Crear un arreglo de tu implementación personalizada de Array
            @SuppressWarnings("unchecked")
            E[] tempArray = (E[]) java.lang.reflect.Array.newInstance(classOfT.getComponentType(), rowCount);

            int i = 0;
            while (resultSet.next()) {
                // Mapear cada fila del resultado a un objeto y agregarlo al arreglo
                tempArray[i++] = mapResultSetToObject(resultSet, (Class<E>) classOfT.getComponentType());
            }

            objects = tempArray;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objects;
    }

    /**
     * Escribir un arreglo de objetos en la base de datos, reemplazando los registros existentes
     * @param tableName Nombre de la tabla
     * @param objects Arreglo de objetos a escribir
     * @return Verdadero si se escribieron correctamente, falso en caso contrario
     */
    @Override
    public Boolean writeObjects(String tableName, E[] objects) {
        boolean successful = false;
        // Eliminar todos los registros existentes asociados a la tabla
        String deleteQuery = "DELETE FROM " + tableName;

        try (Connection connection = mysqlConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(deleteQuery);

            // Insertar los nuevos objetos
            String insertQuery = createInsertQuery(tableName, objects[0]);
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                Array<E> array = new Array<>(objects);
                Iterator<E> iterator = array.iterator();
                while (iterator.hasNext()) {
                    E object = iterator.next();
                    setObjectValuesForPreparedStatement(insertStatement, object);
                    insertStatement.addBatch();
                }
                int[] rowsAffected = insertStatement.executeBatch();
                successful = areAllRowsInsertedSuccessfully(rowsAffected);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return successful;
    }

    /**
     * Verifica si todas las inserciones en la base de datos fueron exitosas.
     * @param rowsAffected Array de filas afectadas por las inserciones.
     * @return Verdadero si todas las inserciones fueron exitosas, falso en caso contrario.
     */
    private boolean areAllRowsInsertedSuccessfully(int[] rowsAffected) {
        for (int row : rowsAffected) {
            if (row != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Crear una consulta SELECT
     * @param tableName Nombre de la tabla
     * @return Consulta SELECT
     */
    private String createSelectQuery(String tableName) {
        return "SELECT * FROM " + tableName;
    }

    /**
     * Crear una consulta INSERT
     * @param tableName Nombre de la tabla
     * @param object Objeto de tipo E
     * @return Consulta INSERT
     */
    private String createInsertQuery(String tableName, E object) {
        // Construir la consulta de inserción utilizando la estructura de la tabla y los datos del objeto
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO ").append(tableName).append(" (");

        // Obtener los nombres de las columnas de la tabla
        // Se asuma que los nombres de las columnas en la tabla corresponden a los nombres de las propiedades en el objeto
        // y que están en el mismo orden
        java.lang.reflect.Field[] fields = object.getClass().getDeclaredFields();
        for (java.lang.reflect.Field field : fields) {
            queryBuilder.append(field.getName()).append(", ");
        }
        queryBuilder.setLength(queryBuilder.length() - 2); // Eliminar la última coma y espacio
        queryBuilder.append(") VALUES (");

        // Agregar placeholders para los valores
        for (int i = 0; i < fields.length; i++) {
            queryBuilder.append("?, ");
        }
        queryBuilder.setLength(queryBuilder.length() - 2); // Eliminar la última coma y espacio
        queryBuilder.append(")");

        return queryBuilder.toString();
    }

    /**
     * Mapear los resultados del ResultSet a un objeto de tipo E
     * @param resultSet ResultSet
     * @param classOfT Clase del objeto
     * @return Objeto de tipo E
     * @throws SQLException
     */
    private E mapResultSetToObject(ResultSet resultSet, Class<E> classOfT) throws SQLException {
        // Mapear los resultados del ResultSet a un objeto de tipo E
        // Se asume que los nombres de las columnas en el ResultSet corresponden a los nombres de las propiedades en el objeto
        // y que están en el mismo orden
        E object = null;
        try {
            object = classOfT.getDeclaredConstructor().newInstance();
            java.lang.reflect.Field[] fields = object.getClass().getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                field.setAccessible(true);
                field.set(object, resultSet.getObject(field.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * Establecer los valores del objeto en un PreparedStatement
     * @param statement PreparedStatement
     * @param object Objeto de tipo E
     * @throws SQLException
     */
    private void setObjectValuesForPreparedStatement(PreparedStatement statement, E object) throws SQLException {
        java.lang.reflect.Method[] methods = object.getClass().getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            String methodName = methods[i].getName();
            if (methodName.startsWith("get")) {
                try {
                    Object value = methods[i].invoke(object);
                    statement.setObject(i + 1, value);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
