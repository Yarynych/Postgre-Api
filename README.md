# Postgre-Api

This project is an example of setting up a connection to a PostgreSQL database and to several datasources in a Spring Boot application.

## Description

This system connects to PostgreSQL databases and allows you to work with several data sources at the same time.

## Settings

Before starting the system, you need to configure the PostgreSQL server:

1. **Configure PostgreSQL**:
   - Install PostgreSQL if not already installed. Installation instructions can be found on the official website: [PostgreSQL Install](https://www.postgresql.org/download/).
     
   - Create a database named `data-base-1`:
     ```bash
     psql -U postgres
     CREATE DATABASE data-base-1;
     CREATE TABLE users (
       user_id SERIAL PRIMARY KEY,
       login VARCHAR(50) NOT NULL UNIQUE,
       first_name VARCHAR(100) NOT NULL,
       last_name VARCHAR(100) NOT NULL);

   - Create a database named `data-base-2`:
     ```bash
     psql -U postgres
     CREATE DATABASE data-base-2;
     CREATE TABLE user_table (
       ldap_login VARCHAR(50) PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       surname VARCHAR(100) NOT NULL);
     ```
     
     - Add any number of test data in the table

2. **Configure the database connection in the project**:
   In the `src/main/resources/application.yml` file, add the following settings:
   ```yaml
   spring:
      application:
      name: Api-Postgre
    data-sources:
      - name: data-base-1
      strategy: postgres
      url: jdbc:postgresql://localhost:5432/data-base-1
      table: users
      user: postgres
      password: smile2222
      driver-class-name: org.postgresql.Driver
      mapping:
        id: user_id
        username: login
        name: first_name
        surname: last_name
    - name: data-base-2
      strategy: postgres
      url: jdbc:postgresql://localhost:5432/data-base-2
      table: user_table
      user: postgres
      password: smile2222
      driver-class-name: org.postgresql.Driver
      mapping:
        id: ldap_login
        username: ldap_login
        name: name
        surname: surname
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        show_sql: true
        dialect: org.hibernate.dialect.PostgreSQLDialect

3. **Assembly and launch**
   Тo clone the repository and set up the project locally, run the following commands:
   git clone https://github.com/Yarynych/Postgre-Api.git
   cd Postgre-Api

4. **Verify with OpenAPI**
   To verify the API is working correctly, you can use the OpenAPI documentation.
     1) Start the application (refer to the specific instructions in the previous sections).
     2) Open your browser and go to http://localhost:8080/swagger-ui.html.
     3) You should see the OpenAPI Swagger UI, which allows you to test the available API endpoints.
