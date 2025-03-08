# Aplicación de Gestión de Estudiantes

Esta es una aplicación full-stack desarrollada con Angular (frontend) y Spring Boot (backend) para gestionar estudiantes, profesores y cursos. Proporciona funcionalidades CRUD (Crear, Leer, Actualizar, Eliminar) para cada entidad.

Esta version no esta de forma completa porque le hacen falta algunas partes del frontend
---

## **Tabla de Contenidos**

1. [Requisitos](#requisitos)
2. [Estructura del Proyecto](#estructura-del-proyecto)
3. [Instalación y Ejecución](#instalación-y-ejecución)
4. [Endpoints de la API](#endpoints-de-la-api)
5. [Capturas de Pantalla](#capturas-de-pantalla)
6. [Tecnologías Utilizadas](#tecnologías-utilizadas)
7. [Contribución](#contribución)
8. [Licencia](#licencia)

---

## **Requisitos**

Antes de ejecutar la aplicación, asegúrate de tener instalado lo siguiente:

- **Backend (Spring Boot)**:
  - Java 17 o superior.
  - Maven 3.x.
  - Base de datos MySQL (o cualquier otra compatible con JPA).

- **Frontend (Angular)**:
  - Node.js 16.x o superior.
  - Angular CLI 16.x o superior.

---

## **Estructura del Proyecto**

El proyecto está dividido en dos carpetas principales:

- **Backend**: Contiene el código fuente de Spring Boot.
  - `src/main/java/com/jdh/pruebaCodesa`: Paquete principal con las entidades, servicios, controladores y repositorios.
  - `src/main/resources`: Configuración de la aplicación (application.properties) y scripts de la base de datos.

- **Frontend**: Contiene el código fuente de Angular.
  - `src/app`: Módulos, componentes, servicios y rutas de Angular.
  - `src/environments`: Configuración de entornos (development, production).

---

## **Instalación y Ejecución**

### **Backend (Spring Boot)**

1. Clona el repositorio

2. Navega a la carpeta de la BD:

   - cd `pruebaTecnicaCodesa/BD`

   - Ahi dentro encontraras el diagrama de Base de Datos llamado `DiagramaBD_MySQL.mwb`. 
    Recuerda tner MySQL Workbench.

   - Tambien tienes el archivo de la Base de Datos llamado `prueba_codesa.sql`,
    puedes abirilo mediante la herramienta Recuerda tner MySQL Workbench, en la pestaña "Server" hay una opcion de Data Import

3. Navega a la carpeta del backend:

   - puedes navegar desde la interfaz grafica de tu IDE o desde la terminal 

   -cd  `RutaDeDondeDescargasteElArchivo /pruebaTecnicaCodesa\Aplicativo\codesaBackend`

4. Configura la conexión a la base de datos en application.properties:

  - Configura las lineas de codigo  

    Ejemplo:

    spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña

  - durante el desarrollo las lineas fueron:

    spring.datasource.url=jdbc:mysql://localhost:3306/prueba_codesa
    spring.datasource.username=root
    spring.datasource.password=root

5. Compila y ejecuta la aplicación:
  
  - con ayuda de tu IDE puedes ir `src/main/java/com/jdh/pruebaCodesa/PruebaCodesaApplication.java` y darle click derecho y Run o desde la pasrte de arriba a la izaqueirda en el simbolo Run

  - mvn clean install
    mvn spring-boot:run

6. Navega a la carpeta del frontend: 

   - puedes navegar desde la interfaz grafica de tu IDE o desde la terminal 

   - cd  `RutaDeDondeDescargasteElArchivo /pruebaTecnicaCodesa\Aplicativo\codesaFronted`

   - Instala las dependencias: npm install

7. Configura la URL del backend en environment.ts:

  - dentro de `/codesaFronted/src` hay 2 archivos copiar esto:

  export const environment = {
    producton: false,
    backendHost: 'http://localhost:8080'
};

8. Ejecuta la aplicación:

  - ng server -o

  La aplicación estará disponible en http://localhost:420
