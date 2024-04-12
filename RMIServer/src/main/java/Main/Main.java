    package Main;

    import patitotrains.model.domain.Employee;
    import patitotrains.model.repository.EmployeeRepository;
    import raul.Model.array.Array;


    public class Main {
        public static void main(String[] args) {
            // Crear una instancia de EmployeeRepository
            EmployeeRepository employeeRepository = new EmployeeRepository();

            // Llamar al método addEmployee y pasarle un objeto de tipo Employee, los numeros de telefono son un array de Strings
            employeeRepository.addEmployee(new Employee("Juan", "Perez", new Array<String>(new String[]{"12345678", "87654321"}), "12345678"));

        }
    }

