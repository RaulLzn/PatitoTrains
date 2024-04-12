package patitotrains.model.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import patitotrains.model.domain.Employee;
import raul.Model.array.Array;
import raul.Model.linkedlist.doubly.circular.LinkedList;
import raul.Model.util.Iterator.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;

    @BeforeEach
    public void setUp() {
        // Inicializamos el repositorio de empleados antes de cada test
        employeeRepository = new EmployeeRepository();
    }

    @Test
    public void testGetEmployeeById() {
        // Creamos un empleado de prueba
        Array<String> phoneNumbers = new Array<>(1);
        phoneNumbers.set(0, "+1234567890");
        Employee testEmployee = new Employee("John", "Doe", phoneNumbers, "EMP001");

        // Agregamos el empleado de prueba al repositorio
        employeeRepository.addEmployee(testEmployee);

        // Obtenemos el empleado del repositorio utilizando su ID
        Employee retrievedEmployee = employeeRepository.getEmployeeById("EMP001");

        // Verificamos que el empleado recuperado sea igual al empleado de prueba
        assertEquals(testEmployee, retrievedEmployee);
    }

    @Test
    public void testAddEmployee() {
        // Creamos un nuevo empleado
        Array<String> phoneNumbers = new Array<>(1);
        phoneNumbers.set(0, "+9876543210");
        Employee newEmployee = new Employee("Jane", "Smith", phoneNumbers, "EMP002");

        // Agregamos el nuevo empleado al repositorio
        boolean success = employeeRepository.addEmployee(newEmployee);

        // Verificamos que la operación de agregar haya sido exitosa
        assertTrue(success);

        // Obtenemos la lista de empleados del repositorio después de agregar el nuevo empleado
        LinkedList<Employee> employees = employeeRepository.getEmployees();

        // Verificamos que la lista de empleados contenga al nuevo empleado
        assertTrue(employees.contains(newEmployee));
    }

    @Test
    public void testAddEmployees() {
        // Creamos una lista de empleados nuevos
        LinkedList<Employee> newEmployees = new LinkedList<>();
        Array<String> phoneNumbers1 = new Array<>(1);
        phoneNumbers1.set(0, "+1111111111");
        newEmployees.add(new Employee("Alice", "Johnson", phoneNumbers1, "EMP003"));
        Array<String> phoneNumbers2 = new Array<>(1);
        phoneNumbers2.set(0, "+2222222222");
        newEmployees.add(new Employee("Bob", "Williams", phoneNumbers2, "EMP004"));

        // Agregamos los empleados nuevos al repositorio
        boolean success = employeeRepository.addEmployees(newEmployees);

        // Verificamos que la operación de agregar haya sido exitosa
        assertTrue(success);

        // Obtenemos la lista de empleados del repositorio después de agregar los nuevos empleados
        LinkedList<Employee> employees = employeeRepository.getEmployees();

        // Verificamos que la lista de empleados contenga todos los nuevos empleados
        boolean allNewEmployeesFound = true;
        Iterator<Employee> iterator = newEmployees.iterator();
        while (iterator.hasNext()) {
            Employee newEmployee = iterator.next();
            if (!employees.contains(newEmployee)) {
                allNewEmployeesFound = false;
                break;
            }
        }
        assertTrue(allNewEmployeesFound);

    }
}
