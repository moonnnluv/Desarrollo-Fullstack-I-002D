# EduTech Innovators SPA

Repositorio del proyecto de microservicios de la empresa ficticia EduTech Innovators SPA creado por Alejandra Reyes y Benjamín Torrejón para la asignatura Desarrollo Fullstack I - Duoc UC con la profesora Mariangeles Robinson.

## Descripción del proyecto

El proyecto consiste en transformar una arquitectura monolítica a una basada en microservicios, para mejorar la escalabilidad y mantenibilidad. Se implementaron tres microservicios independientes para gestionar usuarios, cursos y evaluaciones, con un frontend que interactúa mediante llamadas REST.

## Arquitectura general

- **Frontend**: HTML, CSS, JS (servido en `http://localhost:5500` con Live Server).
- **Microservicios**:
  - **Usuarios**: Spring Boot (`http://localhost:8083/api/usuarios`)
  - **Cursos**: Spring Boot (`http://localhost:8081/api/cursos`)
  - **Evaluaciones**: Spring Boot (`http://localhost:8082/api/evaluaciones`)
- **Base de datos**: MySQL con tablas separadas para cada microservicio.

## Estructura de carpetas

├── cursos/
├── evaluaciones/
├── usuarios/
├── script_base_datos_edutech/
├── frontend/
└── evidencias postman.docx

Cada carpeta de microservicio contiene:
- `entity/`
- `repository/`
- `service/`
- `controller/`
- `resources/`
- `pom.xml`

## Dependencias principales

- `spring-boot-starter-web`
- `spring-boot-starter-data-jpa`
- `mysql-connector-java`
- `spring-boot-devtools`

## Endpoints disponibles

| Servicio   | Método | Endpoint                            | Descripción                  |
|------------|--------|-------------------------------------|------------------------------|
| Usuarios   | GET    | `/api/usuarios`                    | Obtener todos los usuarios   |
| Usuarios   | GET    | `/api/usuarios/{id}`               | Obtener usuario por ID       |
| Usuarios   | POST   | `/api/usuarios`                    | Crear un nuevo usuario       |
| Usuarios   | PUT    | `/api/usuarios/{id}`               | Actualizar usuario           |
| Usuarios   | DELETE | `/api/usuarios/{id}`               | Eliminar usuario             |
| Cursos     | GET    | `/api/cursos`                      | Obtener todos los cursos     |
| Cursos     | GET    | `/api/cursos/{id}`                 | Obtener curso por ID         |
| Cursos     | POST   | `/api/cursos`                      | Crear un nuevo curso         |
| Cursos     | PUT    | `/api/cursos/{id}`                 | Actualizar curso             |
| Cursos     | DELETE | `/api/cursos/{id}`                 | Eliminar curso               |
| Evaluaciones | GET  | `/api/evaluaciones`                | Obtener todas las evaluaciones|
| Evaluaciones | GET  | `/api/evaluaciones/{id}`           | Obtener evaluación por ID    |
| Evaluaciones | POST | `/api/evaluaciones`                | Crear evaluación             |
| Evaluaciones | PUT  | `/api/evaluaciones/{id}`           | Actualizar evaluación        |
| Evaluaciones | DELETE| `/api/evaluaciones/{id}`          | Eliminar evaluación          |

## Ejecución de los servicios con Postman

Se validó el funcionamiento de los endpoints con Postman realizando las operaciones CRUD de cada servicio. Las evidencias se encuentran en el archivo `evidencias postman.docx` del repositorio.

## Contribución
Este repositorio corresponde a un proyecto de evaluación. Las contribuciones están deshabilitadas.

## Licencia
Este proyecto es parte de un curso universitario y no tiene licencia comercial.
