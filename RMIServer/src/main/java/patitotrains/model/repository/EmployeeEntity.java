package patitotrains.model.repository;

/**
 * Clase que representa la entidad de empleado
 */
public class EmployeeEntity {
    String idEmployee;
    String names;
    String lastNames;
    String phones;

    /**
     * Constructor de la clase
     */
    public EmployeeEntity(String idEmployee, String names, String lastNames, String phones) {
        this.idEmployee = idEmployee;
        this.names = names;
        this.lastNames = lastNames;
        this.phones = phones;
    }
}
