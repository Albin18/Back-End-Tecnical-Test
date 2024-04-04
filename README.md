## Simple Application in Spring Boot

This simple application in Spring Boot comes with the following pre-configured dependencies for easy development:

## Dependencys

-spring-boot-starter-jdbc: Proporciona un inicio rápido para trabajar con JDBC (Java Database Connectivity).

-spring-boot-starter-data-jpa: Proporciona un inicio rápido para trabajar con Spring Data JPA, que simplifica el acceso y la manipulación de datos en bases de datos relacionales.

-spring-boot-starter-web: Proporciona un inicio rápido para construir aplicaciones web utilizando Spring MVC.

-spring-boot-devtools: Herramientas de desarrollo de Spring Boot para facilitar el desarrollo y la depuración durante el ciclo de desarrollo.

-mysql-connector-j: Conector MySQL JDBC para conectarse a una base de datos MySQL.

-spring-boot-starter-test: Proporciona un inicio rápido para escribir pruebas unitarias y de integración en aplicaciones Spring Boot.

-spring-data-jdbc: Proporciona soporte para trabajar con bases de datos relacionales utilizando Spring Data JDBC.

-lombok: Biblioteca que ayuda a reducir la verbosidad del código Java generando automáticamente ciertos métodos como getters, setters, constructores, etc.

-h2: Base de datos en memoria para desarrollo y pruebas. Permite ejecutar una base de datos H2 embebida en la aplicación.

## Requirements
-Java Development Kit (JDK 17) instalado en el sistema.

-SpringBoot 3.0 instalado.

-Instalé Maven para gestión de dependencias y construcción de proyectos.

## Database
- H2: la base de datos se genera de forma automatica en el navegador en la URL (http://localhost:8080/h2-ui)
  - Configurar los siguientes campos ->
        JDBC URL: jdbc:h2:mem:usuarios
        User Name: admin
        Password: 
- SQL: en el archivo data.sql se encuentra el SQL para crear la tabla usuarios para la base de datos

- EXTRA: para configurar una conexion a una base de datos MySQL se debe configurar el archivo -> application.properties

## POSTMAN
- La llave primaria es el email por ser un valor unico, para el metodo PUT, GET, DELETE se envia un RequestBody mediante el correo en la URL
        EJEMPLO:  
  - PARA EL METODO: {POST} [http://localhost:8080/api/usuarios]
                      AGREGAMOS UN JSON:  {
                                            "name": "Juan Rodriguez",
                                            "email": "juan@rodriguez.org",
                                            "password": "Hunter270$",
                                            "phones": [
                                                         {
                                                        "number": "1234567",
                                                        "citycode": "1",
                                                        "contrycode": "57"
                                                }
                                            ]
                                        }
 
- PARA EL METODO: {PUT} [http://localhost:8080/api/usuarios/juan@rodriguez.org]
  - AGREGAMOS EL JSON CON LA INFORMACION MODIFICADA: {
                                                        "name": "Albian Perez",
                                                        "email": "juan@rodriguez.org",
                                                        "password": "Aass70554487$",
                                                        "isactive": 1,
                                                        "phones": [
                                                                    {
                                                                    "number": "1112343",
                                                                    "citycode": "2",
                                                                    "contrycode": "20"
                                                                    }
                                                             ]
                                                        }
- PARA EL METODO: {GET} [http://localhost:8080/api/usuarios],  este nos regresa todos los usuarios en la tabla.
- PARA EL METODO: {DELETE} [http://localhost:8080/api/usuarios/juan@rodriguez.org], seleccionamos el email que deseamos eliminar.