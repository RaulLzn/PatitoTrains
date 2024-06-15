package patitotrains.shared.jsonAdapter;

import raul.Model.util.list.List;

/**
 * Interfaz para el adaptador de archivos JSON
 * @param <E> Tipo de objeto a manejar
 */
public interface FileJsonAdapterInterface<E> {

    /**
     * Método para obtener objetos de un archivo JSON
     * @param pathFile Ruta del archivo
     * @param classOfT Clase del objeto
     * @return Lista de objetos de tipo E
     */
    List<E> getObjects(String pathFile, Class<E[]> classOfT);

    /**
     * Método para escribir objetos en un archivo JSON
     * @param pathFile Ruta del archivo
     * @param objects Lista de objetos a escribir
     * @return True si la escritura fue exitosa, false en caso contrario
     */
    Boolean writeObjects(String pathFile, List<E> objects);
}
