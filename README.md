## Business Trips Service

- The application secured endpoints can just be just accessed if a user has valid credentials (`username` and `password`) and has autorization roles for it.

  `Business Trips Service` stores its data in [`Postgres`](https://www.postgresql.org/) database.

  `Business Trips Service API` has the following endpoints

  | Endpoint                                             | Secured | Roles       |
    |------------------------------------------------------|---------|-------------|
  | `POST /auth/authenticate -d {"username","password"}` | No      |             |
  | `GET /welcome`                                       | No      |             |
  | `GET /swagger-ui.html`                               | No      |             |
  | `GET /api/admin`                                     | Yes     |             |
  | `GET /api/admin/trips`                               | Yes     | `ADMIN`     |
  | `GET /api/admin/trips/{id}`                          | Yes     | `ADMIN`     |
  | `PATCH /api/admin/trips/{id}`                        | Yes     | `ADMIN`     |
  | `GET /api/profile`                                   | Yes     |             |
  | `GET /api/trips`                                     | Yes     | `USER`      |
  | `POST /api/trips`                                    | Yes     | `USER`      |
  | `GET /api/trips/{id}`                                | Yes     | `USER`      |
  | `PATCH /api/trips/{id}`                              | Yes     | `USER`      |

## Prerequisites

- [`Java 17+`](https://www.oracle.com/java/technologies/downloads/#java17)
- [`Docker`](https://www.docker.com/)

## Start Environment

- In a terminal, make sure you are inside `BusinessTripsBackend` root folder

- Run the following command to start docker compose containers
  ```
  docker compose up -d
  ```

## Running book-app using Maven & Npm

- **Business Trips Service**

    - Open a terminal and navigate to `sBusinessTripsBackend` folder

    - Run the following `Maven` command to build and package the application in JAR
      ```
      ./mvn clean package spring-boot:repackage
      ```
    - Run the following `Java` command to run the application
      ```
      java -jar {Absolute path to the root folder}\BusinessTripsBackend\target\BusinessTripsBackend-1.0-SNAPSHOT.jar
      ```
