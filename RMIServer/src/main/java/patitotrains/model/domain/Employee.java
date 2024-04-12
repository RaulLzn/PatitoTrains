package patitotrains.model.domain;

import raul.Model.array.Array;

/**
 * Clase que representa un empleado
 */
public class Employee extends AbstractPerson{
    private String idEmployee;

    /**
     * Constructor de la clase
     * @param names nombres del empleado
     * @param lastNames apellidos del empleado
     * @param phones teléfonos del empleado
     * @param idEmployee identificación del empleado
     */
    public Employee(String names, String lastNames, Array<String> phones, String idEmployee) {
        super(names, lastNames, phones);
        this.idEmployee = idEmployee;
    }

    /**
     * Constructor vacio
     */
    public Employee() {
        super();
        this.idEmployee = "";
    }

    public Employee(int i, String emp001, String john, String doe, String number) {
        super();
        this.idEmployee = "";
    }

    //Getters y Setters
    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    /**
     * Método que retorna un empleado vacío
     * @return empleado vacío
     */
    public static Employee getEmptyEmployee() {
        return new Employee();
    }

}
