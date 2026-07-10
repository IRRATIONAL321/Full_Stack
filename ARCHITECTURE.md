# Spring Boot 6-Layer Architecture

## Project Structure Overview

This Spring Boot project implements a clean 6-layer architecture pattern that separates concerns and promotes maintainability, testability, and scalability.

```
src/main/java/com/resume/portal/
├── model/                 # 1. MODEL LAYER
│   └── User.java         # Entity/Domain models
├── dto/                  # 2. DTO LAYER
│   └── UserDTO.java      # Data Transfer Objects
├── repository/           # 3. REPOSITORY LAYER
│   ├── UserRepository.java       # Interface
│   └── impl/
│       └── UserRepositoryImpl.java # Implementation
├── service/              # 4. SERVICE LAYER
│   ├── UserService.java          # Interface
│   └── impl/
│       └── UserServiceImpl.java   # Implementation
├── controller/           # 5. CONTROLLER LAYER
│   └── UserController.java       # REST/Web endpoints
├── exception/            # 6. EXCEPTION LAYER
│   ├── ResourceNotFoundException.java  # Custom exceptions
│   └── GlobalExceptionHandler.java     # Global exception handling
└── PortalApplication.java        # Main application class
```

---

## Layer Descriptions

### 1. MODEL LAYER (`model/`)
**Responsibility**: Define the core business entities and domain objects.

- Contains `@Entity` classes (JPA entities for database)
- Represents the database schema in Java objects
- Contains only state (fields) and basic getters/setters
- **Example**: `User.java` - represents a user in the system

**When to use**:
- Map database tables to Java objects
- Define relationships (One-to-One, One-to-Many, etc.)
- Add validations at entity level

---

### 2. DTO LAYER (`dto/`)
**Responsibility**: Transfer data between layers without exposing internal details.

- Contains `DTO` (Data Transfer Object) classes
- Used for API requests and responses
- Can differ from Model classes (e.g., exclude sensitive data)
- Acts as a contract between frontend and backend
- **Example**: `UserDTO.java` - data structure for API payload

**Benefits**:
- Hides internal model structure
- Controls API contract independently
- Prevents exposing unnecessary fields
- Enables versioning of APIs

---

### 3. REPOSITORY LAYER (`repository/`)
**Responsibility**: Handle database operations and data access logic.

- Contains `Repository` interfaces (contract definition)
- Implements CRUD operations (Create, Read, Update, Delete)
- Abstracts database interaction logic
- **Example**: 
  - `UserRepository.java` - interface defining operations
  - `UserRepositoryImpl.java` - database implementation

**Key Methods**:
- `save()` - Create/insert
- `findById()` - Read single record
- `findAll()` - Read all records
- `update()` - Update record
- `delete()` - Delete record

---

### 4. SERVICE LAYER (`service/`)
**Responsibility**: Implement business logic and orchestrate operations.

- Contains `Service` interfaces (contract definition)
- Implements business rules and workflows
- Validates input data
- Calls repository for data operations
- Handles transactions and exception handling
- **Example**:
  - `UserService.java` - interface defining services
  - `UserServiceImpl.java` - business logic implementation

**Key Responsibilities**:
- Data validation
- Business rule enforcement
- DTO ↔ Model conversion
- Exception handling
- Logging and auditing

---

### 5. CONTROLLER LAYER (`controller/`)
**Responsibility**: Handle HTTP requests and responses.

- Contains REST endpoints or MVC controllers
- Receives requests from clients
- Calls service layer for processing
- Returns responses in appropriate format
- **Example**: `UserController.java` - exposes user operations as APIs

**Typical Endpoints**:
- `POST /users` - Create user
- `GET /users/{id}` - Get user
- `GET /users` - Get all users
- `PUT /users/{id}` - Update user
- `DELETE /users/{id}` - Delete user

---

### 6. EXCEPTION LAYER (`exception/`)
**Responsibility**: Handle and manage application errors uniformly.

- Contains custom exception classes
- Implements global exception handling
- Maps exceptions to appropriate HTTP responses
- **Example**:
  - `ResourceNotFoundException.java` - custom exception
  - `GlobalExceptionHandler.java` - centralized error handling

**Exception Types**:
- Business logic exceptions (validation, not found)
- Technical exceptions (database, network)
- Security exceptions (authorization, authentication)

---

## Data Flow Example

### Create User Request
```
1. CLIENT sends POST /users with UserDTO
   ↓
2. CONTROLLER receives request
   - Validates basic input
   ↓
3. SERVICE processes request
   - Validates business rules
   - Converts DTO → Model
   - Calls repository to save
   ↓
4. REPOSITORY executes database operation
   - Saves User entity to database
   ↓
5. SERVICE converts response
   - Model → DTO
   ↓
6. CONTROLLER returns response
   - Returns UserDTO to client
```

---

## Best Practices

### ✅ DO's
- Keep layers independent and loosely coupled
- Use interfaces for abstraction
- Keep business logic in service layer
- Use DTOs for API communication
- Handle exceptions at global level
- Validate input at service layer
- Use dependency injection

### ❌ DON'Ts
- Don't expose models directly in API responses
- Don't put business logic in controller
- Don't mix database logic with business logic
- Don't skip exception handling
- Don't call controller from service
- Don't create circular dependencies

---

## Adding New Features

### 1. Create Model
```java
@Entity
public class Product {
    private Long id;
    private String name;
    // ... fields
}
```

### 2. Create DTO
```java
public class ProductDTO {
    private Long id;
    private String name;
    // ... fields
}
```

### 3. Create Repository Interface
```java
public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    // ... methods
}
```

### 4. Create Repository Implementation
```java
public class ProductRepositoryImpl implements ProductRepository {
    // Implement database operations
}
```

### 5. Create Service Interface
```java
public interface ProductService {
    ProductDTO createProduct(ProductDTO dto);
    // ... methods
}
```

### 6. Create Service Implementation
```java
public class ProductServiceImpl implements ProductService {
    // Implement business logic
}
```

### 7. Create Controller
```java
public class ProductController {
    private ProductService productService;
    // Implement endpoints
}
```

---

## Technology Stack

- **Framework**: Spring Boot 4.1.0
- **Language**: Java 17
- **Build Tool**: Maven
- **Architecture**: 6-Layer Clean Architecture

---

## Dependencies

To use with database and REST features, add these to `pom.xml`:

```xml
<!-- JPA/Hibernate for database -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- REST support -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Database (e.g., MySQL) -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>

<!-- Validation -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

---

## Example Usage

```java
// In main application
UserRepositoryImpl repository = new UserRepositoryImpl();
UserServiceImpl service = new UserServiceImpl(repository);
UserController controller = new UserController(service);

// Create user
UserDTO userDTO = new UserDTO(null, "John Doe", "john@example.com", "123456789");
UserDTO created = controller.createUser(userDTO);

// Get all users
List<UserDTO> allUsers = controller.getAllUsers();

// Update user
userDTO.setName("Jane Doe");
UserDTO updated = controller.updateUser(1L, userDTO);

// Delete user
controller.deleteUser(1L);
```

---

## Testing Strategy

### Unit Tests
- Test each layer independently
- Mock dependencies
- Focus on business logic

### Integration Tests
- Test layer interactions
- Test end-to-end flows
- Test database operations

### Sample Test
```java
@Test
public void testCreateUser() {
    UserDTO userDTO = new UserDTO(null, "Test User", "test@example.com", "9999999999");
    UserDTO result = userService.createUser(userDTO);
    
    assertNotNull(result.getId());
    assertEquals("Test User", result.getName());
}
```

---

## Conclusion

This 6-layer architecture provides:
- **Separation of Concerns**: Each layer has a specific responsibility
- **Maintainability**: Easy to understand and modify
- **Testability**: Each layer can be tested independently
- **Scalability**: Easy to add new features
- **Reusability**: Components can be reused across projects
