package patitotrains.model.repository;

import patitotrains.model.domain.Employee;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;

import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.list.List;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que se encarga de la persistencia de los empleados
 */
public class EmployeeRepository {
    /**
     * Ruta del archivo JSON para almacenar los empleados
     */
    private static final String EMPLOYEE_FILE_PATH = "employees.json";

    /**
     * Adaptador de archivos JSON
     */
    private final FileJsonAdapter<Employee> fileJsonAdapter;

    /**
     * Constructor de la clase
     */
    public EmployeeRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Método que carga los empleados desde el archivo JSON
     *
     * @return Lista de empleados cargados
     */
    public LinkedList<Employee> getEmployees() {
        List<Employee> employees = fileJsonAdapter.getObjects(EMPLOYEE_FILE_PATH, Employee[].class);
        return (LinkedList<Employee>) employees;
    }

    /**
     * Método para agregar un empleado al archivo JSON
     *
     * @param newEmployee Nuevo empleado a agregar
     * @return Verdadero si la operación fue exitosa, falso en caso contrario
     */
    public boolean addEmployee(Employee newEmployee) {
        try {
            LinkedList<Employee> employees = getEmployees();
            employees.add(newEmployee);
            return fileJsonAdapter.writeObjects(EMPLOYEE_FILE_PATH, employees);
        } catch (Exception e) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, "Error adding employee", e);
            return false;
        }
    }

    /**
     * Método para agregar varios empleados al archivo JSON
     *
     * @param newEmployees Lista de nuevos empleados a agregar
     * @return Verdadero si la operación fue exitosa, falso en caso contrario
     */
    public boolean addEmployees(LinkedList<Employee> newEmployees) {
        try {
            LinkedList<Employee> employees = getEmployees();
            employees.add(newEmployees);
            return fileJsonAdapter.writeObjects(EMPLOYEE_FILE_PATH, employees);
        } catch (Exception e) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, "Error adding employees", e);
            return false;
        }
    }
}
