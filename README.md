# taskManagementApi
A A Spring Boot backend API for managing users, tasks, tags, comments 
Demonstrates understanding of RESTful APIs, JPA relationships (1:1, 1:N, N:M), input validation, pagination, and custom exception handling. 

## Technology Stack

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- MySQL Database
- Maven
- Swagger / OpenAPI (Springdoc)
## Database Setup

This project uses **MySQL** as the database.

### 1. Create Database

```sql
CREATE DATABASE task;

### 2. Add MySQL Connector Dependency

In your `pom.xml`:

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.1.0</version>
</dependency>
```

### 3. Configure `application.properties`

```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/task
spring.datasource.username=root
spring.datasource.password=*****
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Server
server.port=8080
```

---

## Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

The application will start at: [http://localhost:8080](http://localhost:8080)

---

## Database Relationships

- **User → Tasks (1:N)**: A user can have multiple tasks. Each task belongs to one user.  
- **Task → Comments (1:N)**: A task can have multiple comments. Each comment belongs to one task and one user.  
- **Task ↔ Tags (M:N)**: A task can have multiple tags. A tag can belong to multiple tasks. Implemented via join table `task_tags`.  

**Schema Overview:**

```
User → 1:N → Task
Task → 1:N → Comment
Task ↔ M:N ↔ Tag (via task_tags)
Comment → 1:1 → User


## API Endpoints

### Users
Post- /api/users
Get-/api/users/{userId}/tasks` | Get all tasks for a user |

### Tasks

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/tasks` | Create a new task |
| GET    | `/api/tasks` | Get all tasks (supports pagination) |
| GET    | `/api/tasks/{id}` | Get task by ID |
| GET    | `/api/tasks?status=TODO` | Filter tasks by status |
| GET    | `/api/tasks?tag=tagName` | Filter tasks by tag |
| PUT    | `/api/tasks/{id}` | Update a task (title, description, status) |
| DELETE | `/api/tasks/{id}` | Delete a task |

### Tags

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/tags` | Create a new tag |
| GET    | `/api/tags` | List all tags |
| POST   | `/api/tasks/{taskId}/tags/{tagId}` | Assign tag to task |
| DELETE | `/api/tasks/{taskId}/tags/{tagId}` | Remove tag from task |

### Comments

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/tasks/{taskId}/comments` | Add comment to a task |
| GET    | `/api/tasks/{taskId}/comments` | Get all comments for a task |

## Sample Requests

**Create User:**

```json
POST /api/users
{
  "name": "Abhigna",
  "email": "abhi18@gmail.com"
}
```

**Create Task:**

```json
POST /api/tasks
{
  "title": "Complete assignment",
  "description": "Finish the backend intern assignment",
  "status": "TODO",
  "userId": 1
}
```

**Add Comment to Task:**

```json
POST /api/tasks/1/comments
{
  "text": "Started working on this task",
  "userId": 1
}
```

**Create Tag:**

```json
POST /api/tags
{
  "name": "urgent"
}
```

**Assign Tag to Task:**

```
POST /api/tasks/1/tags/2
```

**Filter Tasks by Tag:**

```
GET /api/tasks?tag=urgent
```

---

## Pagination Example

Retrieve tasks with pagination:

```
GET /api/tasks?page=0&size=5
GET /api/tasks?status=TODO&page=1&size=10
```

---

## Error Responses

Example of custom exception response:

```json
{
  "timestamp": "2025-11-21T10:15:30.123",
  "status": 404,
  "error": "Not Found",
  "message": "Task not found with id: 10"
}
```
| URL                                                                                | Description      |
| ---------------------------------------------------------------------------------- | ---------------- |
| **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)** | Swagger UI       |
| **[http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)**         | Raw OpenAPI JSON |


## Notes

- All endpoints return proper HTTP status codes (`200 OK`, `201 Created`, `404 Not Found`, `400 Bad Request`).  
- DTOs are used to prevent lazy loading issues and circular references.  
- Pagination and filtering supported for task listing.  
- CascadeType.ALL ensures comments are deleted when a task is removed.  

---

## Assumptions

- Each task is assigned to a single user.  
- Comments are always linked to both a user and a task.  
- TaskMetadata is optional.  
