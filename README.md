
# Employee Control System<br>

Employee Control System is a web application built using Rest API, DTO pattern, Hibernate, Lombok, Swagger, and mapStruct.<br>

## Description<br>

The app allows users to perform various CRUD (Create, Read, Update, Delete) operations on a database. The DTO pattern is used to efficiently transfer data between layers, while Hibernate simplifies the database operations. Swagger is used for documentation, and mapStruct streamlines the mapping of DTOs.<br>

The tests cover both unit and integration testing, ensuring the reliability and efficiency of the app.<br>

## Technologies Used<br>

- Rest API<br>
- DTO pattern<br>
- Hibernate<br>
- Lombok<br>
- Swagger<br>
- mapStruct<br>
- flyway<br>
- docker<br>

## Installation<br>

To install the app, follow these steps:<br>

1. Clone the repository.<br>
2. Run `mvn clean install` to install the required dependencies.<br>
3. Start the application using `mvn spring-boot:run`.<br>

You can also run aplication in docker by using next instruction.<br>
1. Clone the repository.<br>
2. Compile project by using `mvn clean install`.<br>
3. Run docker on your computer.<br>
4. Use `docker-compose up --build` to run backend.<br>

## Usage<br>

To use the app, follow these steps:<br>

1. Open the Swagger UI in your web browser: `http://localhost:8087/swagger-ui.html`.<br>
2. Use the API endpoints to perform CRUD operations on the database.<br>
