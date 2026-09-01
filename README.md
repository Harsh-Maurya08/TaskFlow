# TaskFlow — Team Task & Project Management API

TaskFlow is a backend REST API for team-based task and project management, inspired by tools like Jira and Trello. Built with Spring Boot and PostgreSQL, it demonstrates core backend engineering practices: JWT-secured authentication, role-based authorization, relational data modeling, input validation, centralized exception handling, and auto-generated API documentation.

This project was built as part of a self-directed backend development internship program, simulating a real-world software development lifecycle.


## Features

- **User authentication** — registration and login secured with JWT (JSON Web Tokens)
- **Role-based access control** — ADMIN, MANAGER, and MEMBER roles with different permissions
- **Project management** — create and manage projects, each with an owner
- **Task management** — tasks belong to projects, are assigned to users, and track status (`TODO`, `IN_PROGRESS`, `DONE`) and priority (`LOW`, `MEDIUM`, `HIGH`)
- **Comments** — threaded comments on tasks, linked to their author
- **Filtering** — retrieve tasks by project or by status
- **Global exception handling** — clean, consistent JSON error responses instead of raw stack traces
- **Input validation** — enforced at the API layer using Jakarta Bean Validation
- **Auto-generated API docs** — live, interactive documentation via Swagger UI
- **Unit tests** — service-layer logic tested with JUnit 5 and Mockito


## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 26 |
| Framework | Spring Boot 4.1 |
| Data Access | Spring Data JPA + Hibernate |
| Database | PostgreSQL |
| Security | Spring Security + JWT (JJWT) |
| Build Tool | Maven |
| Testing | JUnit 5 + Mockito |
| API Docs | Springdoc OpenAPI (Swagger UI) |
| Version Control | Git + GitHub |


## Architecture

The project follows a standard layered architecture:

```
Controller  →  Service  →  Repository  →  Database
   (HTTP)      (Business       (Data
              Logic)          Access)
```

- **Entity layer** — JPA-mapped Java classes representing database tables
- **Repository layer** — Spring Data JPA interfaces handling all database queries
- **Service layer** — business logic, sitting between repositories and controllers
- **Controller layer** — REST endpoints handling HTTP requests/responses
- **Security layer** — JWT generation/validation, filtering every incoming request
- **Exception layer** — global handler converting errors into clean JSON responses

This separation means, for example, the database technology (PostgreSQL) could be swapped for another relational database with changes isolated to the Repository layer alone.


## Data Model

| Entity | Key Fields | Relationships |
|---|---|---|
| **User** | name, email, password (hashed), role | — |
| **Project** | name, description | Many-to-One → owner (User) |
| **Task** | title, description, status, priority, dueDate | Many-to-One → Project, Many-to-One → assignee (User) |
| **Comment** | content, timestamp | Many-to-One → Task, Many-to-One → author (User) |

---

## Getting Started

### Prerequisites

- JDK 17 or higher (built and tested on JDK 26)
- Maven 3.9+
- PostgreSQL 14+

### 1. Clone the repository

```bash
git clone https://github.com/<your-username>/taskflow.git
cd taskflow
```

### 2. Create the database

In pgAdmin or `psql`, create a database:

```sql
CREATE DATABASE taskflow_db;
```

### 3. Set environment variables

This project reads sensitive configuration from environment variables — no secrets are committed to the repository. Set the following on your system before running:

| Variable | Description | Example |
|---|---|---|
| `DB_USERNAME` | PostgreSQL username | `postgres` |
| `DB_PASSWORD` | PostgreSQL password | *(your password)* |
| `JWT_SECRET` | Base64-encoded secret key for signing JWTs | *(a long random string)* |

### 4. Run the application

```bash
mvn clean install
mvn spring-boot:run
```

The app will start on `http://localhost:8080`.

### 5. Explore the API

- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
- Import the Postman collection from `/postman/TaskFlow.postman_collection.json` (if included) for a ready-made set of requests.

---

## API Overview

| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/auth/register` | Register a new user | No |
| POST | `/api/auth/login` | Log in, receive a JWT | No |
| GET | `/api/users` | List all users | Yes (ADMIN) |
| GET | `/api/users/{id}` | Get a user by ID | Yes |
| POST | `/api/projects` | Create a project | Yes |
| GET | `/api/projects` | List all projects | Yes |
| POST | `/api/tasks` | Create a task | Yes |
| GET | `/api/tasks/project/{projectId}` | Get tasks by project | Yes |
| GET | `/api/tasks/status/{status}` | Get tasks by status | Yes |
| POST | `/api/comments` | Add a comment to a task | Yes |
| GET | `/api/comments/task/{taskId}` | Get comments for a task | Yes |

Full endpoint documentation, including request/response schemas, is available live via Swagger UI once the app is running.

---

## Running Tests

```bash
mvn test
```

Unit tests cover service-layer business logic using Mockito to isolate dependencies from the database.

## Security Notes

- Passwords are hashed using BCrypt before storage — plain-text passwords are never persisted.
- Authentication is stateless — every request must carry a valid JWT in the `Authorization: Bearer <token>` header.
- Role-based rules restrict sensitive endpoints (e.g., user management) to ADMIN accounts.

## Roadmap / Future Improvements

- Pagination and sorting on list endpoints
- Expanded test coverage across all service classes
- Docker Compose setup for one-command local environment
- Deployment to a cloud platform (Render/Railway)
- Optional lightweight frontend

## Author

Harsh Maurya