package patitotrains.model.repository;

import patitotrains.model.domain.Employee;
import patitotrains.model.repository.entity.EmployeeEntity;
import patitotrains.shared.jsonAdapter.FileJsonAdapter;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;
import raul.Model.linkedlist.doubly.circular.LinkedList;

/**
 * Repositorio de empleados.
 */
public class EmployeeRepository {

    private final String EMPLOYEE_DATA_FILE = "RMIServer/src/main/java/database/Employee.Json"; // Ruta del archivo JSON
    private final FileJsonAdapter<EmployeeEntity> fileJsonAdapter; // Adaptador de archivos JSON

    /**
     * Constructor de la clase.
     */
    public EmployeeRepository() {
        this.fileJsonAdapter = FileJsonAdapter.getInstance();
    }

    /**
     * Obtiene una lista de todos los empleados.
     *
     * @return Lista de empleados.
     */
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = fileJsonAdapter.getObjects(EMPLOYEE_DATA_FILE, EmployeeEntity[].class);
        List<Employee> employees = new LinkedList<>();

        Iterator<EmployeeEntity> iterator = employeeEntities.iterator();
        while (iterator.hasNext()) {
            employees.add(mapToEmployee(iterator.next()));
        }

        return employees;
    }

    /**
     * Guarda un empleado en el archivo JSON.
     *
     * @param employee Empleado.
     * @return Verdadero si el empleado se guardó correctamente, falso en caso contrario.
     */
    public boolean saveEmployee(Employee employee) {
        List<EmployeeEntity> employeeEntities = fileJsonAdapter.getObjects(EMPLOYEE_DATA_FILE, EmployeeEntity[].class);
        EmployeeEntity entity = mapToEmployeeEntity(employee);
        employeeEntities.add(entity);

        return fileJsonAdapter.writeObjects(EMPLOYEE_DATA_FILE, employeeEntities);
    }

    /**
     * Mapea un objeto EmployeeEntity a un objeto Employee.
     *
     * @param entity Objeto EmployeeEntity.
     * @return Objeto Employee.
     */
    private Employee mapToEmployee(EmployeeEntity entity) {
        return new Employee(
                entity.getNames(),
                entity.getLastNames(),
                entity.getNumbers(),
                entity.getId()
        );
    }

    /**
     * Mapea un objeto Employee a un objeto EmployeeEntity.
     *
     * @param employee Objeto Employee.
     * @return Objeto EmployeeEntity.
     */
    private EmployeeEntity mapToEmployeeEntity(Employee employee) {
        return new EmployeeEntity(
                employee.getId(),
                employee.getNames(),
                employee.getLastNames(),
                employee.getNumbers()
        );
    }

    /**
     * Obtiene un empleado por su identificación.
     *
     * @param id Identificación del empleado.
     * @return Empleado.
     */
    public Employee getEmployeeById(String id) {
        List<EmployeeEntity> employeeEntities = fileJsonAdapter.getObjects(EMPLOYEE_DATA_FILE, EmployeeEntity[].class);
        Iterator<EmployeeEntity> iterator = employeeEntities.iterator();
        while (iterator.hasNext()) {
            EmployeeEntity employeeEntity = iterator.next();
            if (employeeEntity.getId().equals(id)) {
                return mapToEmployee(employeeEntity);
            }
        }
        return null;
    }

    /**
     * Elimina un empleado por su identificación.
     *
     * @param id Identificación del empleado.
     * @return Verdadero si el empleado se eliminó correctamente, falso en caso contrario.
     */
    public boolean deleteEmployee(String id) {
        List<EmployeeEntity> employeeEntities = fileJsonAdapter.getObjects(EMPLOYEE_DATA_FILE, EmployeeEntity[].class);
        Iterator<EmployeeEntity> iterator = employeeEntities.iterator();
        while (iterator.hasNext()) {
            EmployeeEntity employeeEntity = iterator.next();
            if (employeeEntity.getId().equals(id)) {
                employeeEntities.remove(employeeEntity);
                return fileJsonAdapter.writeObjects(EMPLOYEE_DATA_FILE, employeeEntities);
            }
        }
        return false;
    }
}
