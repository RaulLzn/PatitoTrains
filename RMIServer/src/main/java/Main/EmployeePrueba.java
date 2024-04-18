package Main;

import patitotrains.model.domain.Employee;
import patitotrains.model.repository.EmployeeRepository;

import raul.Model.array.Array;
import raul.Model.util.list.List;
import raul.Model.util.Iterator.Iterator;

public class EmployeePrueba {

        public static void main(String[] args) {
            // Crear una instancia del repositorio de empleados
            EmployeeRepository employeeRepository = new EmployeeRepository();

            // Obtener todos los empleados
            System.out.println("Obteniendo todos los empleados:");
            List<Employee> allEmployees = employeeRepository.getAllEmployees();
            printEmployeeList(allEmployees);

            // Guardar un nuevo empleado
            Employee newEmployee = new Employee("Juan", "Perez", new Array<>("564812"), "EMP001");
            boolean saveResult = employeeRepository.saveEmployee(newEmployee);
            if (saveResult) {
                System.out.println("Empleado guardado correctamente.");
            } else {
                System.out.println("Error al guardar el empleado.");
            }

            // Obtener un empleado por su identificación
            String employeeIdToGet = "EMP001";
            Employee retrievedEmployee = employeeRepository.getEmployeeById(employeeIdToGet);
            if (retrievedEmployee != null) {
                System.out.println("Empleado encontrado por ID " + employeeIdToGet + ":");
                System.out.println(retrievedEmployee);
            } else {
                System.out.println("No se encontró ningún empleado con el ID " + employeeIdToGet + ".");
            }

        }

        // Método auxiliar para imprimir la lista de empleados
        private static void printEmployeeList(List<Employee> employees) {
            if (employees.isEmpty()) {
                System.out.println("No hay empleados.");
                return;
            }

            System.out.println("Lista de empleados:");
            Iterator<Employee> iterator = employees.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
}
