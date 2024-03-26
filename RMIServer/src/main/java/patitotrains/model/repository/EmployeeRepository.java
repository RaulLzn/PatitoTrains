package patitotrains.model.repository;

import patitotrains.model.domain.Employee;
import patitotrains.shared.mysqlAdapter.MySQLAdapter;

import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;
import raul.Model.util.list.List;

import java.io.Serializable;

/**
 * Clase que se encarga de la persistencia de los empleados
 */
public class EmployeeRepository implements Serializable {
    /**
     * Adaptador de MySQL
     */
    private final MySQLAdapter<EmployeeEntity> mySQLAdapter;
    /**
     * Nombre de la tabla en la base de datos
     */
    private final String tableName;
    /**
     * Arreglo de entidades de empleados
     */
    private Array<EmployeeEntity> employeesEntities;

    /**
     * Constructor de la clase
     */
    public EmployeeRepository() {
        this.tableName = "Employee";
        this.mySQLAdapter = MySQLAdapter.getInstance();
        loadEmployees();
    }

    /**
     * Método que carga los empleados de la base de datos
     */
    private void loadEmployees(){
        employeesEntities = new Array<>(mySQLAdapter.getObjects(tableName, EmployeeEntity[].class));
    }

    /**
     * Método que retorna un empleado por su id
     * @param idEmployee id del empleado
     * @return Empleado
     */
    public Employee getEmployeeById(String idEmployee){
        Iterator<EmployeeEntity> iterator = employeesEntities.iterator();
        while (iterator.hasNext()){
            EmployeeEntity employeeEntity = iterator.next();
            if (employeeEntity.idEmployee.equals(idEmployee)){
                // Separar la cadena de números de teléfono y convertirla en un array de números
                String[] phoneNumbersStr = employeeEntity.phones.split(",");
                Array<String> phoneNumbers = new Array<>(phoneNumbersStr.length);
                for (String phoneNumberStr : phoneNumbersStr) {
                    phoneNumbers.add(phoneNumberStr.trim());
                }

                // Crear el objeto Employee con los datos correctos
                return new Employee(employeeEntity.names, employeeEntity.lastNames, phoneNumbers, employeeEntity.idEmployee);
            }
        }
        return Employee.getEmptyEmployee();
    }

    /**
     * Método que retorna una lista de empleados
     * @return Lista de empleados
     */
    public List<Employee> getEmployees(){
        List<Employee> employees = new LinkedList<>();
        Iterator<EmployeeEntity> iterator = employeesEntities.iterator();
        while (iterator.hasNext()){
            EmployeeEntity employeeEntity = iterator.next();
            // Separar la cadena de números de teléfono y convertirla en un array de números
            String[] phoneNumbersStr = employeeEntity.phones.split(",");
            Array<String> phoneNumbers = new Array<>(phoneNumbersStr.length);
            for (String phoneNumberStr : phoneNumbersStr) {
                phoneNumbers.add(phoneNumberStr.trim());
            }

            // Crear el objeto Employee con los datos correctos
            employees.add(new Employee(employeeEntity.names, employeeEntity.lastNames, phoneNumbers, employeeEntity.idEmployee));
        }
        return employees;
    }

    /**
     * Método para agregar un empleado a la base de datos
     * @param newEmployee Nuevo empleado a agregar
     * @return Verdadero si la operación fue exitosa, falso en caso contrario
     */
    public boolean addEmployee(Employee newEmployee) {
        int currentSize = employeesEntities.size();

        EmployeeEntity[] newArray = new EmployeeEntity[currentSize + 1];

        for (int i = 0; i < currentSize; i++) {
            newArray[i] = employeesEntities.get(i);
        }

        newArray[currentSize] = new EmployeeEntity(
                newEmployee.getIdEmployee(),
                newEmployee.getNames(),
                newEmployee.getLastNames(),
                newEmployee.getPhonesAsString()
        );

        // Escribir todos los objetos (empleados existentes y el nuevo empleado) en la base de datos
        return mySQLAdapter.writeObjects(tableName, newArray);
    }

    /**
     * Método para agregar empleados a la base de datos
     * @param newEmployees Lista enlazada de empleados a agregar
     * @return Verdadero si la operación fue exitosa, falso en caso contrario
     */
    public boolean addEmployees(LinkedList<Employee> newEmployees) {
        LinkedList<EmployeeEntity> tempEmployeeList = new LinkedList<>();

        Iterator<EmployeeEntity> iterator = employeesEntities.iterator();
        while (iterator.hasNext()) {
            tempEmployeeList.add(iterator.next());
        }

        Iterator<Employee> newIterator = newEmployees.iterator();
        while (newIterator.hasNext()) {
            Employee employee = newIterator.next();
            tempEmployeeList.add(new EmployeeEntity(
                    employee.getIdEmployee(),
                    employee.getNames(),
                    employee.getLastNames(),
                    employee.getPhonesAsString()
            ));
        }

        EmployeeEntity[] combinedEmployeeArray = tempEmployeeList.toArray();

        // Escribir todos los objetos (empleados existentes y nuevos) en la base de datos
        return mySQLAdapter.writeObjects(tableName, combinedEmployeeArray);
    }








}
