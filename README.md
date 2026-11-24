# Spring JPA Join Example

A small Spring Boot 2.7.4 application demonstrating a one-to-many relationship between projects and engineers and querying related data with native SQL and JPQL.

## Requirements

- Java 8 or later (the Maven project targets Java 8)
- Maven
- MySQL running locally

## Database configuration

The application currently connects to a MySQL database named `javatechie` on `localhost:3306`. Create that database before starting the application.

Review `src/main/resources/application.properties` and configure the connection for your local environment. **Do not commit real database passwords or other credentials.** The current configuration contains a plaintext password setting and should be migrated to an environment-based configuration in a separate security change, with the existing credential handled securely.

The application uses `spring.jpa.hibernate.ddl-auto=update`, which can change the schema at startup. Use a disposable local database for this example; do not point it at production data.

## Run

From the repository root:

```bash
mvn spring-boot:run
```

The application listens on port **9191** by default.

## HTTP endpoints

| Method | Path | Purpose |
| --- | --- | --- |
| POST | `/projects` | Create a project |
| GET | `/projects` | List projects |
| GET | `/engineers` | List engineers |
| DELETE | `/project/{projectId}` | Delete a project by ID |
| GET | `/join/sql` | Query project/engineer information using native SQL |
| GET | `/join/jpql` | Query project/engineer information using JPQL |

The create endpoint accepts a JSON representation of `Project`, including an optional `engineers` array. Inspect the entity classes before supplying fields.

## Tests

Run the existing Maven test suite with:

```bash
mvn test
```

The existing Spring context test requires database configuration, so a reachable test database may be necessary. This documentation change does not alter application code or test configuration.
