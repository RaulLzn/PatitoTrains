# Sistema de Gestión de Trenes de Transporte de Pasajeros

Este proyecto es un sistema desarrollado en Java para gestionar trenes de transporte de pasajeros. Utiliza JavaFX para las interfaces de usuario, RMI para la comunicación entre el servidor y los clientes, implementa estructuras de datos propias y utiliza JSON como base de datos para administrar la información.

## Características

- **Módulos:**
  - **Servidor Centralizado:** Encargado de gestionar la comunicación entre los diferentes módulos y los clientes.
  - **Módulo de Administración:** Permite administrar la información relacionada con los trenes, rutas y horarios.
  - **Módulo de Gestión de Tickets:** Facilita la compra y gestión de tickets por parte de los usuarios.
  - **Módulo de Abordaje:** Se encarga de gestionar el abordaje de los pasajeros a los trenes.

- **Grafos y Algoritmos:**
  - Se utilizan grafos para representar las rutas de los trenes.
  - Implementación de algoritmos para calcular la ruta más corta, independientemente de la ubicación del pasajero.

- **Tecnologías utilizadas:**
  - JavaFX: Para las interfaces de usuario.
  - RMI: Para la comunicación cliente-servidor.
  - JSON: Como base de datos para almacenar y administrar la información.

## Instalación

1. Clona el repositorio: `https://github.com/RaulLzn/PatitoTrains.git`
2. Abre el proyecto en tu IDE favorito.
3. Ejecuta el servidor centralizado.
4. Ejecuta los diferentes módulos según sea necesario.

## Uso

- Inicia sesión en el módulo correspondiente.
- Explora las opciones disponibles para gestionar trenes, tickets y abordajes.
- Realiza pruebas con diferentes escenarios para verificar el funcionamiento del sistema.

## Contribución

Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama: `git checkout -b feature/nueva_funcionalidad`
3. Haz tus cambios y realiza un commit: `git commit -am 'Agrega nueva funcionalidad'`
4. Haz push a la rama: `git push origin feature/nueva_funcionalidad`
5. Envía un pull request.

## Créditos

- Desarrolladores:
  - [Sebastian Granados (Estudiante Ing. Sitemas UPB)](https://github.com/SebastianGranadosJ)
  - [Raúl Lozano (Estudiante Ing. Sitemas UPB)](https://github.com/RaulLzn)

## Licencia

Este proyecto está bajo la Licencia [MIT](https://opensource.org/licenses/MIT).
