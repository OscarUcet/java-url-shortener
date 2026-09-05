# Java URL Shortener

A URL shortener REST API built with **Java and Spring Boot** as a practical training project.

The goal of this project is to learn and apply the Java/Spring ecosystem through a small but functional backend application while following clean and maintainable development practices.

The project is intentionally kept small so that the focus remains on understanding the technologies and backend concepts rather than building unnecessary features.

---

## 🎯 Project Goals

This project is a practical training exercise focused on:

- Learning Java from a backend development perspective.
- Understanding the Spring Boot ecosystem.
- Building REST APIs with Spring Boot.
- Working with relational databases using JPA/Hibernate.
- Applying a layered backend architecture.
- Practicing validation and error handling.
- Learning backend security with Spring Security and JWT.
- Exploring reactive programming with Spring WebFlux.
- Containerizing the application with Docker.
- Deploying a backend application to a cloud environment.

The implementation prioritizes understanding the responsibility of each component and the reasons behind architectural decisions rather than simply reproducing code.

---

## 📌 Project Scope

The initial version provides a simple URL shortening service.

The core functionality includes:

- Creating shortened URLs.
- Retrieving the original URL using a short code.
- Redirecting users to the original URL.
- Persisting URL information in a relational database.

The following functionality will be implemented as the project progresses:

- Deleting shortened URLs.
- Request validation.
- Centralized error handling.
- API documentation.
- Automated tests.
- Containerized application deployment.

Additional capabilities may be introduced later as part of the training process.

---

## 🛠️ Technology Stack

### Backend

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven

### Database

- PostgreSQL

### API Documentation

- OpenAPI
- Swagger UI

### Development & Deployment

- Git
- GitHub
- Docker
- Cloud deployment

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
Client
  │
  ▼
Controller
  │
  ▼
Service
  │
  ▼
Repository
  │
  ▼
Database
```

### Main responsibilities

**Controller**

Handles HTTP requests and responses.

**Service**

Contains the application's business logic.

**Repository**

Handles persistence and communication with the database through Spring Data JPA.

**Entity**

Represents data persisted in the database.

**DTO**

Defines the data exchanged through the API without exposing database entities directly.

**Exception**

Contains application-specific exceptions and centralized error handling.

The architecture may evolve as the project grows.

---

## 📁 Project Structure

```text
src/
└── main/
    ├── java/
    │   └── com/
    │       └── oscaruceta/
    │           └── urlshortener/
    │               ├── controller/
    │               ├── service/
    │               ├── repository/
    │               ├── entity/
    │               ├── dto/
    │               ├── exception/
    │               └── UrlShortenerApplication.java
    │
    └── resources/
        └── application.properties
```

Additional packages will only be introduced when they have a clear purpose.

---

## 📡 API

### Create a short URL

```http
POST /api/links
```

Example request:

```json
{
  "url": "https://example.com/some/very/long/url"
}
```

Example response:

```text
aB72x
```

The returned code can be used to access the shortened URL.

### Get the original URL

```http
GET /api/links/{code}
```

Example:

```http
GET /api/links/aB72x
```

Example response:

```text
https://example.com/some/very/long/url
```

### Redirect to the original URL

```http
GET /{code}
```

Example:

```http
GET /aB72x
```

The API responds with an HTTP `302 Found` redirect to the original URL.

### Delete a short URL

```http
DELETE /api/links/{code}
```

This endpoint is part of the planned API and will be implemented in a later stage.

> API endpoints will be documented and updated as the implementation progresses.

---

## 🐘 Database

The application uses **PostgreSQL** for persistent storage.

For local development, PostgreSQL can be run using Docker Compose.

The application connects to the local database through Spring Data JPA and Hibernate.

Hibernate is currently configured to automatically update the database schema based on the application entities during development.

---

## 🐳 Docker

Docker is currently used to provide the PostgreSQL development environment.

Start the database with:

```bash
docker compose up -d
```

Check the container:

```bash
docker compose ps
```

Stop the database with:

```bash
docker compose down
```

The API itself can be containerized as part of the deployment stage.

---

## 🌱 Branching Strategy

This project uses a lightweight Git branching strategy designed for a small individual project.

### `main`

The main branch represents the stable version of the project.

Rules:

- Do not develop directly on `main`.
- Changes should be introduced through a feature, fix, or documentation branch.
- `main` should remain in a functional state.

### `feature/*`

Used for new functionality.

Examples:

```text
feature/create-short-url
feature/url-redirect
feature/url-statistics
```

### `fix/*`

Used for correcting bugs or unexpected behavior.

Examples:

```text
fix/invalid-url-validation
fix/expired-link-redirect
```

### `docs/*`

Used for documentation-only changes.

Examples:

```text
docs/update-readme
docs/api-documentation
```

Branches should have a short, descriptive name using lowercase words separated by hyphens.

---

## 📝 Commit Convention

The project follows a lightweight version of **Conventional Commits**.

Format:

```text
type: short description
```

Common types:

| Type | Purpose |
|---|---|
| `feat` | Add a new feature |
| `fix` | Fix a bug |
| `refactor` | Change code structure without changing behavior |
| `test` | Add or modify tests |
| `docs` | Documentation changes |
| `chore` | Maintenance or configuration changes |

Examples:

```text
feat: add URL creation endpoint

fix: validate duplicated short codes

refactor: extract URL generation logic

test: add URL service tests

docs: update API documentation

chore: configure PostgreSQL
```

Commit messages should describe the change clearly and remain concise.

---

## 🔄 Development Workflow

For a new feature:

```text
main
  │
  └── feature/my-feature
          │
          ├── development
          ├── testing
          └── commit
                │
                ▼
              merge
                │
                ▼
              main
```

Typical workflow:

```bash
git switch main
git pull
git switch -c feature/my-feature
```

After completing and testing the change:

```bash
git add .
git commit -m "feat: add my feature"
```

The branch can then be merged into `main`.

After merging, the feature branch can be removed.

---

## 🔐 Configuration & Security

Sensitive information must never be committed to the repository.

This includes:

- Database passwords.
- API keys.
- Access tokens.
- JWT secrets.
- Cloud credentials.
- Environment-specific secrets.

Environment-specific configuration should be kept outside version control whenever possible.

Example:

```text
application.properties
application-local.properties
.env
```

Actual credentials must never be placed in public files.

> The current local development configuration uses development-only database credentials. Production configuration will use environment variables or another secure configuration mechanism.

---

## 🔮 Future Learning Areas

The project may be extended to explore additional backend technologies and concepts.

### Authentication & Security

- Spring Security
- JWT authentication
- User accounts
- Authorization

### URL Management

- URL expiration
- Custom aliases
- User-owned links
- Improved short-code generation

### Analytics

- Click counting
- Access timestamps
- Basic usage statistics

### Reactive Programming

- Spring WebFlux
- Reactive endpoints
- Reactive data access

### Infrastructure

- Docker
- Containerized PostgreSQL
- Cloud deployment

These features are not part of the initial implementation and will only be added when they provide meaningful learning value.

---

## ▶️ Running Locally

### Requirements

- Java 21
- Docker
- Git

Maven does not need to be installed globally because the project includes the Maven Wrapper.

### Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/java-url-shortener.git
```

### Navigate to the project

```bash
cd java-url-shortener
```

### Start PostgreSQL

```bash
docker compose up -d
```

Verify that the database container is running:

```bash
docker compose ps
```

### Run the application

On Linux/macOS:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
./mvnw.cmd spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

### Test URL creation

```bash
curl -X POST http://localhost:8080/api/links \
  -H "Content-Type: application/json" \
  -d '{"url":"https://www.google.com"}'
```

The API will return a short code similar to:

```text
38871e
```

### Test URL retrieval

```bash
curl http://localhost:8080/api/links/38871e
```

Expected response:

```text
https://www.google.com
```

### Test URL redirection

```bash
curl -i http://localhost:8080/38871e
```

Expected response:

```text
HTTP/1.1 302
Location: https://www.google.com
```

Swagger UI will be available once OpenAPI/Swagger support is implemented.

---

## 📚 Learning Philosophy

This project is not intended to be a production-scale URL shortening platform.

It is a practical training project designed to understand how Java and Spring Boot are used to build backend applications.

The project favors:

- Understanding over memorization.
- Simple solutions over unnecessary complexity.
- Clean code over premature abstraction.
- Practical best practices over excessive process.
- Incremental learning over building everything at once.

---

## 📌 Project Status

**Training project — functional MVP in development.**

The current implementation provides:

- REST API built with Java and Spring Boot.
- URL shortening.
- PostgreSQL persistence.
- Spring Data JPA / Hibernate integration.
- URL retrieval by short code.
- HTTP `302` redirection.
- Docker-based PostgreSQL development environment.
- Layered backend architecture.

Next development stages include validation, centralized error handling, deletion, automated tests, API documentation, containerizing the application, and cloud deployment.

---

## 👨‍💻 Author

**Oscar Uceta**

Full Stack Developer.

This project is part of my ongoing training to expand my backend development skills into the Java and Spring ecosystem.

---

## 📄 License

This project is licensed under the **MIT License**.
