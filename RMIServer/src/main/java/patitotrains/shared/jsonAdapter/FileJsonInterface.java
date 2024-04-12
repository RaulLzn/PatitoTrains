package patitotrains.shared.jsonAdapter;


import raul.Model.util.list.List;

public interface FileJsonInterface<E> {

    /**
     * Método que obtiene objetos de un archivo JSON
     * @param pathFile Ruta del archivo
     * @param classOfT Clase del objeto
     * @return Objeto de tipo E
     */
    List<E> getObjects(String pathFile, Class<E[]> classOfT);

    /**
     * Método que escribe objetos en un archivo JSON
     * @param pathFile Ruta del archivo
     * @param objects Objeto a escribir
     * @return True si se escribió correctamente, false en caso contrario
     */
    Boolean writeObjects(String pathFile, List<E> objects);






}

