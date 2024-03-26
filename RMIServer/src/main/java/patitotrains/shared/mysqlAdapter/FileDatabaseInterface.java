package patitotrains.shared.mysqlAdapter;

/**
 * Interfaz para la base de datos de archivos
 * @param <E> Tipo de objeto a manejar
 */
public interface FileDatabaseInterface<E> {
    /**
     * Obtener un objeto de la base de datos
     * @param query Consulta SQL
     * @param classOfT Clase del objeto
     * @return Objeto de tipo E
     */
    E getObject(String query, Class<E> classOfT);

    /**
     * Obtener un arreglo de objetos de la base de datos
     * @param query Consulta SQL
     * @param classOfT Clase del objeto
     * @return Arreglo de objetos de tipo E
     */
    E[] getObjects(String query, Class<E[]> classOfT);

    /**
     * Escribir un arreglo de objetos en la base de datos
     * @param query Consulta SQL
     * @param objects Arreglo de objetos a escribir
     * @return True si se escribieron correctamente, false en caso contrario
     */
    Boolean writeObjects(String query, E[] objects);
}

