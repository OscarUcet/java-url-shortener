# Java URL Shortener

A URL shortener REST API built with **Java 21 and Spring Boot 4** as a practical backend training and portfolio project.

The project focuses on building a maintainable backend application with REST endpoints, PostgreSQL persistence, request validation, centralized error handling, automated tests, API documentation, and cloud deployment.

The scope is intentionally kept small to prioritize practical learning and avoid unnecessary complexity.

## 🎯 Project Goals

This project explores the following backend concepts:

- Building REST APIs with Spring Boot.
- Applying a layered backend architecture.
- Persisting data with PostgreSQL and Spring Data JPA.
- Validating incoming requests.
- Handling application errors consistently.
- Documenting APIs with OpenAPI.
- Writing automated tests.
- Managing changes through Git and GitHub.
- Containerizing and deploying a backend application.
- Configuring a remote PostgreSQL database using environment variables.

Future stages may explore authentication, URL expiration, rate limiting, and other backend concepts.

## 📌 Project Status

**Functional MVP deployed and undergoing final portfolio preparation.**

The application supports creating shortened URLs, retrieving their original destinations, and redirecting clients to those destinations.

### Implemented features

- URL shortening with PostgreSQL persistence.
- URL retrieval by short code.
- HTTP `302 Found` redirection.
- HTTP/HTTPS URL validation.
- Request validation and centralized exception handling.
- HTTP `404 Not Found` handling for missing short links.
- Automated tests.
- OpenAPI specification generation.
- Docker image for the application.
- Docker Compose for the local PostgreSQL environment.
- Deployment on Render.
- Remote PostgreSQL database hosted on Neon.
- Environment-based database configuration.
- Git feature-branch workflow and integration into `main`.

### Current limitations

- Swagger UI's public route still needs to be verified and its previous `404` response resolved.
- The README and public demo documentation are undergoing final review.
- Authentication, rate limiting, URL expiration, and abuse prevention are not yet implemented.

This is a learning and portfolio project. It is not intended to be a production-hardened public URL shortening service.

## 🌐 Live Demo

**Base URL:** https://java-url-shortener-azwh.onrender.com

**OpenAPI specification:** [View OpenAPI JSON](https://java-url-shortener-azwh.onrender.com/v3/api-docs)

The backend is deployed on Render and connects to PostgreSQL hosted on Neon.

The OpenAPI JSON endpoint is available. Interactive Swagger UI is still being investigated and should not be considered available until its route has been verified.

Free-tier hosting may introduce startup delays after periods of inactivity and is subject to provider limits.

## 🛠️ Technology Stack

### Backend

- Java 21
- Spring Boot 4.0.8
- Spring Web
- Spring Data JPA
- Hibernate ORM
- Jakarta Bean Validation
- Maven

### Database

- PostgreSQL
- Docker Compose for the local database environment
- Neon for the hosted PostgreSQL database

### API Documentation

- OpenAPI
- springdoc-openapi
- Swagger UI integration

### Deployment and Development Tools

- Docker
- Docker Compose
- Render
- Git
- GitHub
- Maven Wrapper

## 🏗️ Architecture

The application follows a layered architecture:

```text
Client
  |
  v
Controller
  |
  v
Service
  |
  v
Repository
  |
  v
PostgreSQL
```

### Main responsibilities

**Controller**

Handles HTTP requests and responses and exposes the API endpoints.

**Service**

Contains the application's business logic, including link creation and URL retrieval.

**Repository**

Handles persistence through Spring Data JPA.

**Entity**

Represents data persisted in the database.

**DTO**

Defines request data exchanged through the API without exposing database entities directly.

**Exception**

Contains application-specific exceptions and centralized error handling.

**Config**

Contains application configuration, including OpenAPI metadata.

## 📁 Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── Git_Hub_User/
│   │           └── urlshortener/
│   │               ├── config/
│   │               │   └── OpenApiConfig.java
│   │               ├── controller/
│   │               │   ├── LinkController.java
│   │               │   └── RedirectController.java
│   │               ├── dto/
│   │               ├── entity/
│   │               ├── exception/
│   │               ├── repository/
│   │               ├── service/
│   │               └── UrlShortenerApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── Git_Hub_User/
                └── urlshortener/
```

The test directory contains automated tests for application behavior and validation.

## 📡 API Reference

The API currently exposes three endpoints.

### 1. Create a shortened link

**Endpoint:** `POST /api/links`

Request:

```http
POST /api/links
Content-Type: application/json
```

Request body:

```json
{
  "url": "https://example.com/some/very/long/url"
}
```

Example response:

```text
aB3xYz
```

The response contains the generated short code as plain text.

Responses:

- `200 OK` — short link created successfully.
- `400 Bad Request` — invalid request data or URL.

### 2. Retrieve the original URL

**Endpoint:** `GET /api/links/{code}`

Example:

```http
GET /api/links/aB3xYz
```

Example response:

```text
https://example.com/some/very/long/url
```

Responses:

- `200 OK` — original URL retrieved successfully.
- `404 Not Found` — the supplied short code does not exist.

### 3. Redirect to the original URL

**Endpoint:** `GET /{code}`

Example:

```http
GET /aB3xYz
```

The API responds with HTTP `302 Found` and a `Location` header containing the original URL. The client can then navigate to that destination.

Responses:

- `302 Found` — redirect to the original URL.
- `404 Not Found` — the supplied short code does not exist.

### API Documentation

The OpenAPI specification is available at:

- **OpenAPI JSON:** https://java-url-shortener-azwh.onrender.com/v3/api-docs
- **Local OpenAPI JSON:** http://localhost:8080/v3/api-docs

Swagger UI is intended to provide an interactive interface for inspecting and testing the endpoints. Its public URL remains pending verification.

## 🐘 Database

The application uses PostgreSQL for persistent storage and Spring Data JPA/Hibernate for database access.

The project uses two database environments:

- **Local development:** PostgreSQL managed with Docker Compose.
- **Hosted environment:** PostgreSQL hosted on Neon.

Database connection settings are supplied through environment variables:

| Variable | Purpose |
|---|---|
| `DB_URL` | JDBC connection URL |
| `DB_USERNAME` | Database username |
| `DB_PASSWORD` | Database password |

The local configuration provides development defaults. Production credentials must be supplied through the hosting provider's environment configuration and must never be committed to GitHub.

Hibernate is currently configured with `spring.jpa.hibernate.ddl-auto=update`. This simplifies development but should be reviewed before treating the application as production-ready. A migration tool such as Flyway may be considered in a future stage.

## 🐳 Docker

The application includes a multi-stage Dockerfile that builds the Spring Boot application and runs the resulting JAR on a Java 21 runtime image.

Docker Compose is used to manage the local PostgreSQL database.

Start the database:

```bash
docker compose up -d
```

Check the container status:

```bash
docker compose ps
```

Stop the database:

```bash
docker compose down
```

The database data may persist in a Docker volume, depending on the Compose configuration.

Build the application image from the project root:

```bash
docker build -t java-url-shortener .
```

The hosted Render service uses the Dockerfile to build and run the application.

## ☁️ Deployment

The backend is deployed on Render, with PostgreSQL hosted on Neon.

**Deployment URL:** https://java-url-shortener-azwh.onrender.com

The application reads the database connection settings from environment variables. Production credentials are configured in Render rather than stored in the source repository.

The service has successfully started in Render and connected to the remote database. The public API has also been tested for link creation, retrieval, and HTTP redirection.

Free hosting services may have usage limits, cold starts, or other restrictions. Availability is subject to the hosting providers' terms and limits.

## ▶️ Running Locally

### Requirements

- Java 21
- Docker with Docker Compose
- Git

Maven does not need to be installed globally because the repository includes the Maven Wrapper.

### 1. Clone the repository

```bash
git clone https://github.com/OscarUcet/java-url-shortener.git
```

### 2. Navigate to the project

```bash
cd java-url-shortener
```

### 3. Start PostgreSQL

```bash
docker compose up -d
```

Verify that the database container is running:

```bash
docker compose ps
```

Ensure that `src/main/resources/application.properties` matches your local PostgreSQL configuration.

The local development defaults are:

```properties
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/url_shortener}
spring.datasource.username=${DB_USERNAME:postgres}
spring.datasource.password=${DB_PASSWORD:postgres}
```

These defaults are for local development only. Do not use development credentials as production secrets.

### 4. Run the tests

On Windows using the Maven Wrapper:

```powershell
.\mvnw.cmd test
```

On Linux or macOS:

```bash
./mvnw test
```

### 5. Start the application

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

On Linux or macOS:

```bash
./mvnw spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

### 6. Test URL creation

Using `curl`:

```bash
curl -X POST http://localhost:8080/api/links \
  -H "Content-Type: application/json" \
  -d '{"url":"https://www.google.com"}'
```

The response will contain the generated short code.

### 7. Test URL retrieval

Replace `YOUR_CODE` with the code returned by the creation endpoint:

```bash
curl http://localhost:8080/api/links/YOUR_CODE
```

The response will contain the original URL as plain text.

### 8. Test URL redirection

```bash
curl -i http://localhost:8080/YOUR_CODE
```

The response should include an HTTP `302` status and a `Location` header pointing to the original URL.

## 🧪 Testing

The project includes automated tests for request validation, URL handling, and missing short links.

The latest confirmed test run completed with **11 tests, 0 failures, and 0 errors**.

Run the complete test suite with the Maven Wrapper.

On Windows:

```powershell
.\mvnw.cmd test
```

On Linux or macOS:

```bash
./mvnw test
```

Tests should pass before changes are merged into `main`.

## 🌱 Branching Strategy

The project uses a lightweight feature-branch workflow.

**`main`**

The primary integration branch. Changes should be merged after implementation and verification.

**`feat/...`**

Used for new features and capabilities.

Examples:

```text
feat/url-expiration
feat/rate-limiting
feat/click-statistics
```

**`fix/...`**

Used for bug fixes.

Examples:

```text
fix/invalid-url-validation
fix/not-found-handling
```

**`docs/...`**

Used for documentation-only changes.

Examples:

```text
docs/update-readme
docs/deployment-guide
```

Typical workflow:

```bash
git switch main
git pull origin main
git switch -c feat/my-feature
```

After implementation and testing:

```bash
git add .
git commit -m "feat: describe the new feature"
```

Merge the completed branch into `main` after verifying the changes:

```bash
git switch main
git pull origin main
git merge --no-ff feat/my-feature
./mvnw test
git push origin main
```

After a successful merge, remove the local feature branch:

```bash
git branch -d feat/my-feature
```

Delete the remote feature branch when it is no longer needed:

```bash
git push origin --delete feat/my-feature
```

## 📝 Commit Convention

The project follows Conventional Commits.

Format:

```text
type: short description
```

Common types:

| Type | Purpose |
|---|---|
| `feat` | Add a feature |
| `fix` | Fix a bug |
| `refactor` | Improve internal structure |
| `test` | Add or modify tests |
| `docs` | Update documentation |
| `chore` | Maintenance or configuration |

Examples:

```text
feat: add URL creation endpoint
fix: handle missing short links
test: add URL validation tests
docs: document API with OpenAPI
chore: configure PostgreSQL
```

## 🔐 Configuration and Security

Sensitive information must never be committed to the public repository.

Examples include:

- Production database passwords.
- API keys and access tokens.
- JWT secrets.
- Cloud credentials.

Use environment variables or an appropriate secrets-management mechanism for production configuration.

The current application is intended for learning and demonstration. It has not been hardened against public abuse.

Rate limiting, URL expiration, resource cleanup, and other abuse-prevention measures remain future work.

## 🔮 Future Development

Planned improvements may include the following.

### URL Management

- URL expiration and automatic cleanup.
- Custom aliases.
- Improved short-code generation.
- Link deletion.

### Abuse Prevention

- Rate limiting for link creation.
- Request limits and abuse controls.
- Resource cleanup for expired links.
- Restrictions on excessive demo usage.

### Backend Security

- Spring Security and JWT.
- User accounts and authorization.
- Production-oriented configuration and database migrations.

### Analytics

- Click counting.
- Link usage statistics.
- Basic analytics for shortened URLs.

### Advanced Backend Learning

- Exploring Spring WebFlux and reactive programming where appropriate.
- Integrating an AI-powered feature when it provides meaningful value to the project.

These features will be added incrementally after the current MVP and portfolio documentation are finalized.

## 📚 Learning Philosophy

This project favors:

- Understanding over memorization.
- Simple solutions over unnecessary complexity.
- Clean code over premature abstraction.
- Practical best practices over excessive process.
- Incremental progress over unfinished feature accumulation.

The goal is to build a demonstrable backend portfolio project while developing practical Java and Spring Boot skills.

## 👨‍💻 Author

**Oscar Uceta**

Full Stack Developer

This project is part of my ongoing training to expand my backend development skills into the Java and Spring ecosystem.

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for details.
