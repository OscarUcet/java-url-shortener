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

The implementation will prioritize understanding the responsibility of each component and the reasons behind architectural decisions rather than simply reproducing code.

---

## 📌 Project Scope

The initial version will provide a simple URL shortening service.

The core functionality will include:

- Creating shortened URLs.
- Retrieving shortened URL information.
- Redirecting users to the original URL.
- Deleting shortened URLs.
- Persisting URL information in a relational database.
- Validating incoming requests.
- Handling application errors.

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

The application will follow a layered architecture:

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

The initial structure will follow this organization:

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

The initial API will expose endpoints similar to:

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

```json
{
  "code": "aB72x",
  "shortUrl": "http://localhost:8080/aB72x"
}
```

### Get link information

```http
GET /api/links/{code}
```

### Redirect to the original URL

```http
GET /{code}
```

### Delete a short URL

```http
DELETE /api/links/{code}
```

> API endpoints will be documented and updated as the implementation progresses.

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

---

## 🔮 Future Learning Areas

The project may be extended to explore additional backend technologies and concepts:

### Authentication & Security

- Spring Security
- JWT authentication
- User accounts
- Authorization

### URL Management

- URL expiration
- Custom aliases
- User-owned links

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

- Java
- Maven
- PostgreSQL

Clone the repository:

```bash
git clone https://github.com/YOUR_USERNAME/java-url-shortener.git
```

Navigate to the project:

```bash
cd java-url-shortener
```

Configure the local database connection and run the application:

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

> Local setup instructions will be updated as the project configuration is implemented.

---

## 📚 Learning Philosophy

This project is not intended to be a production-scale URL shortening platform.

It is a practical training project designed to understand how Java and Spring Boot are used to build backend applications.

The project will favor:

- Understanding over memorization.
- Simple solutions over unnecessary complexity.
- Clean code over premature abstraction.
- Practical best practices over excessive process.
- Incremental learning over building everything at once.

---

## 📌 Project Status

**Training project — initial setup.**

The repository currently contains the project documentation and development guidelines. Implementation will be developed incrementally through feature branches.

---

## 👨‍💻 Author

**Oscar Uceta**

Full Stack Developer.

This project is part of my ongoing training to expand my backend development skills into the Java and Spring ecosystem.

---

## 📄 License

This project is licensed under the **MIT License**.
