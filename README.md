# Java URL Shortener

A URL shortener REST API built with **Java 21 and Spring Boot** as a practical backend training and portfolio project.

The project focuses on understanding the Java and Spring ecosystem through a functional backend application, emphasizing maintainable code, automated testing, validation, error handling, and API documentation.

The scope is intentionally kept small to prioritize practical learning and avoid unnecessary complexity.

---

## 🎯 Project Goals

This project explores the following backend concepts:

- Building REST APIs with Spring Boot.
- Applying a layered backend architecture.
- Persisting data with PostgreSQL and Spring Data JPA.
- Validating incoming requests.
- Handling application errors consistently.
- Documenting APIs with OpenAPI and Swagger UI.
- Writing automated tests.
- Managing changes through Git and GitHub.
- Preparing a backend application for cloud deployment.

Future stages may explore authentication, URL expiration, rate limiting, and other backend concepts.

---

## 📌 Project Status

**Functional MVP in development.**

The current implementation supports creating shortened URLs, retrieving their original destinations, and redirecting clients to those destinations.

Implemented features:

- URL shortening and PostgreSQL persistence.
- URL retrieval by short code.
- HTTP `302 Found` redirection.
- HTTP/HTTPS URL validation.
- Request validation and centralized error handling.
- HTTP `404 Not Found` handling for missing short links.
- Automated tests.
- OpenAPI specification and Swagger UI.
- Git feature-branch workflow.

The application is a learning and portfolio project, not a production-ready URL shortening service.

---

## 🛠️ Technology Stack

### Backend

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Jakarta Bean Validation
- Maven

### Database

- PostgreSQL 15
- Docker Compose for the local database environment

### API Documentation

- OpenAPI 3.1
- springdoc-openapi
- Swagger UI

### Development Tools

- Git
- GitHub
- Maven Wrapper
- Docker

---

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

---

## 📁 Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── USER_GITHUB/
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
            └── USER_GITHUB/
                └── urlshortener/
```

The test directory contains automated tests for application behavior and validation.

---

## 📡 API Reference

The API currently exposes three endpoints.

### 1. Create a shortened link

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

Documented responses:

- `200 OK` — short link created successfully.
- `400 Bad Request` — invalid request data or URL.

### 2. Retrieve the original URL

```http
GET /api/links/{code}
```

Example:

```http
GET /api/links/aB3xYz
```

Example response:

```text
https://example.com/some/very/long/url
```

Documented responses:

- `200 OK` — original URL retrieved successfully.
- `404 Not Found` — the supplied short code does not exist.

### 3. Redirect to the original URL

```http
GET /{code}
```

Example:

```http
GET /aB3xYz
```

The API responds with an HTTP `302 Found` status and a `Location` header containing the original URL. The client can then navigate to that destination.

Documented responses:

- `302 Found` — redirect to the original URL.
- `404 Not Found` — the supplied short code does not exist.

### Interactive API documentation

Swagger UI allows developers to inspect and try the API endpoints through a browser.

When running locally:

- **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

---

## 🐘 Database

The application uses PostgreSQL for persistent storage and Spring Data JPA/Hibernate for database access.

The local database environment is managed through Docker Compose.

Hibernate is currently configured to update the database schema automatically during development. This is a development convenience and should be reviewed before a production deployment.

Database credentials and environment-specific configuration should not be committed as production secrets.

---

## 🐳 Docker

Docker Compose is currently used to run PostgreSQL locally. The API itself is not yet containerized.

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

---

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

Ensure the database configuration in `src/main/resources/application.properties` matches the local PostgreSQL setup.

### 4. Run the tests

On Windows:

```bash
./mvnw.cmd test
```

On Linux or macOS:

```bash
./mvnw test
```

### 5. Start the application

On Windows:

```bash
./mvnw.cmd spring-boot:run
```

On Linux or macOS:

```bash
./mvnw spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

Swagger UI will be available at:

```text
http://localhost:8080/swagger-ui/index.html
```

### 6. Test URL creation

Using `curl`:

```bash
curl -X POST http://localhost:8080/api/links \
  -H "Content-Type: application/json" \
  -d '{"url":"https://www.google.com"}'
```

The response will contain a generated short code.

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

---

## 🧪 Testing

The project includes automated tests for request validation, URL handling, and missing short links.

Run the complete test suite with the Maven Wrapper:

```bash
./mvnw test
```

On Windows:

```bash
./mvnw.cmd test
```

Tests should pass before changes are merged into `main`.

---

## 🌱 Branching Strategy

The project uses a lightweight feature-branch workflow.

**`main`**

The stable integration branch. Changes should be merged only after implementation and verification.

**`feat/...`**

Used for new features and capabilities.

Examples:

```text
feat/url-validation
feat/not-found-handling
feat/api-documentation
```

**`fix/...`**

Used for bug fixes.

Examples:

```text
fix/invalid-url-validation
fix/expired-link-redirect
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

---

## 📝 Commit Convention

The project follows Conventional Commits.

Format:

```text
type: short description
```

Common types:

| Type       | Purpose                      |
| ---------- | ---------------------------- |
| `feat`     | Add a feature                |
| `fix`      | Fix a bug                    |
| `refactor` | Improve internal structure   |
| `test`     | Add or modify tests          |
| `docs`     | Update documentation         |
| `chore`    | Maintenance or configuration |

Examples:

```text
feat: add URL creation endpoint
fix: handle missing short links
test: add URL validation tests
docs: document API with OpenAPI
chore: configure PostgreSQL
```

---

## 🔐 Configuration and Security

Sensitive information must never be committed to the public repository.

Examples include:

- Production database passwords.
- API keys and access tokens.
- JWT secrets.
- Cloud credentials.

Use environment variables or an appropriate secrets-management mechanism for production configuration.

The current local development configuration uses development-only database credentials. It must not be reused as a production configuration.

Rate limiting, abuse prevention, link expiration, and production deployment security remain future work. The current implementation should not be considered hardened against public abuse.

---

## 🔮 Future Development

Planned improvements may include:

### URL Management

- URL expiration and automatic cleanup.
- Custom aliases.
- Improved short-code generation.
- Link deletion.

### Abuse Prevention

- Rate limiting for link creation.
- Request limits and abuse controls.
- Resource cleanup for expired links.

### Deployment

- Containerizing the API.
- Production database configuration.
- Free-tier cloud deployment, subject to provider limits.
- Public demo URL and deployment documentation.

### Advanced Backend Learning

- Spring Security and JWT.
- User accounts and authorization.
- Click counting and usage statistics.
- Exploring Spring WebFlux and reactive programming where appropriate.

These features will be added incrementally when they provide meaningful learning value.

---

## 📚 Learning Philosophy

This project favors:

- Understanding over memorization.
- Simple solutions over unnecessary complexity.
- Clean code over premature abstraction.
- Practical best practices over excessive process.
- Incremental progress over unfinished feature accumulation.

The goal is to build a demonstrable backend portfolio project while developing practical Java and Spring Boot skills.

---

## 👨‍💻 Author

**Oscar Uceta**

Full Stack Developer

This project is part of my ongoing training to expand my backend development skills into the Java and Spring ecosystem.

---

## 📄 License

This project is licensed under the MIT License. See the `LICENSE` file for details.
