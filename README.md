## Introduction

<p align="justify">
With a specialization in AI, I built a strong foundation in 
Machine Learning and Deep Learning, driving high-impact technological solutions. 
At Brazil’s largest social e-commerce, part of the Global Saving Group, I led 
research and implementation of transformative technologies, directly impacting 
over 25 million users worldwide.
</p>

<p align="justify">
This project was developed for the IT-EAM test, 
providing a robust RESTful API for product management. 
It enables authenticated users to perform CRUD operations 
(Create, Read, Update, and Delete) with strong validation and
role-based access control for security and proper authorization. 
To challenge myself, I applied Clean Architecture principles, creating 
a modular and flexible application adaptable to rapid changes.
</p>

**Links**
- [LinkedIn](www.linkedin.com/in/willianrsouza/)
- [Machine Learning - Projects](https://dagshub.com/willianrsouza)


---
## Table of contents
- [Applied Concepts](#Applied Concepts)
  - [Clean Architecture](#Clean Architecture)
  - [Modules Organization](#Modules Organization)
- [Technologies](#Technologies)
- [Running the Project](#Running the Project)
- [Database](#Authorization)
- [Endpoints](#Endpoints)
- [Authenticate](#Authenticate)
---

# Applied Concepts

**"_Uma arquitetura bem estruturada adia decisões, evitando escolhas prematuras e facilitando futuras mudanças." - Robert C. Martin_** 

<p align="justify">
Clean Architecture has gained popularity for its ability to create flexible, scalable, and maintainable systems.
Its core principles emphasize the separation of concerns and independence from frameworks, prioritizing domain 
logic and business rules. This approach allows the system to evolve without impacting other components, ensuring 
long-term adaptability and quality. This project was developed following this pattern, ensuring extreme modularity 
and technological independence, as only the outermost layer interacts with frameworks and libraries.
</p>

<div align="center"><a href="image/clean-architecture-cone.jfif"><img src="image/clean-architecture-cone.jfif" alt="Diagram showing IoT-to-Cloud (Nebula) network connections" style="width:70%;height:70%"/></a></div>

## Clean Architecture

### 1. Entity (Core)

**Purpose:** Represents the main entities or domain objects of the application, encapsulating the core business logic. These entities are independent of technical details such as databases, frameworks, or user interfaces.

**Dependencies:** Should not depend on any other layer, especially on specific technical implementations.

---

### 2. Use Case
**Purpose:** Defines the application's use cases, meaning the functionalities and business rules that govern interactions between entities. Each use case represents a specific operation that the application can perform.

**Dependencies:** May depend on entities but should not have direct dependencies on technical details such as user interfaces or data persistence.

---

### 3. Interface and Adapters (Application)
**Purpose:** Acts as an intermediary between the application's logic and the external world, handling user interfaces, external APIs, and databases. Interfaces define the necessary contracts, while adapters implement these contracts to connect the application with external systems.

**Dependencies:** May depend on use cases and entities but should not be coupled to specific frameworks or technologies.

---

### 4. Framework and Infrastructure (Infrastructure)
**Purpose:** Contains concrete technical implementations, including frameworks, libraries, and external integrations such as databases and web services. This layer is responsible for providing technical support to the other layers without impacting the core application logic.

**Dependencies:** May depend on internal layers, but these should not depend directly on it.

---

## Modules Organization

### Core
- `Entity`
- `Enums`

### UseCase
- `UseCase Interfaces`

### Application
- `UseCase Implementation`
- `Gateways Interfaces`

### Infrastructure
- `Config`
- `Controller`
- `DTO`
- `Entity`
- `Exception`
- `Mapper`
- `Repository`
- `Service`
- `Utils`
- `Validators`

> [!NOTE]
> The validation of entities and objects was carried out using Fluent Validation, aiming to centralize this process and create modular handling. This approach allows entities to focus solely on deep business rules derived from the domain. Since Fluent Validation is an external library, I adopted a strategy where these superficial validations are handled in the outermost layer. I believe this was a well-thought-out decision, and I’m quite pleased with the results!

<div align="right"><kbd><a href="#table-of-contents">↑ Back to top ↑</a></kbd></div>

---

# Technologies

- **Spring Boot** - Web, Data JPA, Security, and Validation
- **Hibernate** - ORM for database management
- **Jakarta Persistence API** - JPA specification for entity persistence
- **Flyway** - Database migrations
- **MySQL** - Primary database
- **H2 Database** - In-memory database for testing
- **Spring Security** - Authentication and authorization
- **Lombok** - Reduces boilerplate code in Java classes
- **Apache Commons Lang** - Additional utilities for Java development
- **Fluent Validation** - Centralized and modular validation
- **SpringDoc OpenAPI** - API documentation with Swagger UI
- **JUnit 5** - Unit testing framework

<div align="right"><kbd><a href="#table-of-contents">↑ Back to top ↑</a></kbd></div>

---

## Running the Project

### Prerequisites
Ensure you have the following installed:
- **Java 17** (or compatible version)
- **Gradle** 
- **Git**

### Clone the Repository
```sh
git clone https://github.com/willianrsouza/ITeam-ProductManagerAPI-Challenge.git
cd ITeam-ProductManagerAPI-Challenge
```

### Configure the Database
This project uses **H2 Database** for local development. If you want to use a different database (e.g., MySQL), update `application.yml` or `application.properties` accordingly.

### Run the Project
Using **Gradle Wrapper**:
```sh
./gradlew bootRun
```
On Windows (PowerShell or Command Prompt):
```sh
gradlew.bat bootRun
```

### Access the Application
- API Base URL: `http://localhost:8081`
- Swagger UI (API Documentation): `http://localhost:8081/swagger-ui/index.html`
- H2 Database Console: `http://localhost:8081/h2-console` (Configure using provided JDBC settings)

<div align="right"><kbd><a href="#table-of-contents">↑ Back to top ↑</a></kbd></div>

---

## Database
Use the following credentials to connect to the database:

- **Saved Settings:** Generic H2 (Embedded)
- **Driver Class:** `org.h2.Driver`
- **JDBC URL:** `jdbc:h2:file:C:/Projects/product-manager-api/testdb`
- **User Name:** `sa`
- **Password:** (leave empty)

### Database Schema
The database schema was created using Flyway migrations. The following tables were defined in the migration scripts located in:

```
infrastructure/src/main/resources
```

### Tables Created:
#### Users Table
```sql
CREATE TABLE Users (
   Id UUID DEFAULT UUID() PRIMARY KEY,
   Name VARCHAR(100) NOT NULL,
   Email VARCHAR(100) NOT NULL UNIQUE,
   Password VARCHAR(255) NOT NULL,
   Role VARCHAR(10) NOT NULL DEFAULT 'user',
   IsActive BOOLEAN DEFAULT TRUE,
   CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Categories Table
```sql
CREATE TABLE Categories (
    Id UUID DEFAULT UUID() PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Products Table
```sql
CREATE TABLE Products (
    Id UUID DEFAULT UUID() PRIMARY KEY,
    CategoryId UUID,
    Name VARCHAR(100) NOT NULL,
    Description TEXT NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    Stock INTEGER NOT NULL,
    CreatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (CategoryId) REFERENCES Categories(Id) ON DELETE CASCADE
);
```

## Initial Data
Some initial data was inserted into the database for testing purposes.

#### Insert Users
```sql
INSERT INTO Users (Name, Email, Password, Role, IsActive, CreatedAt, UpdatedAt) VALUES
    ('Willian Souza', 'admin@iteam.com',
     '$2a$10$E62tvSFK0/ehdvF4CdzGUuI9x8veq/AQl5sAI1B/6CCu0UtANcqim',
     'ROLE_ADMIN', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Tiago Ferreira', 'user@iteam.com',
     '$2a$10$E62tvSFK0/ehdvF4CdzGUuI9x8veq/AQl5sAI1B/6CCu0UtANcqim',
     'ROLE_USER', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
```

#### Insert Categories
```sql
INSERT INTO Categories (Name, CreatedAt, UpdatedAt) VALUES
    ('Electronics', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Clothing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Food', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
```

#### Insert Products
```sql
INSERT INTO Products (CategoryId, Name, Description, Price, Stock, CreatedAt, UpdatedAt) VALUES
    ('c911976c-5488-4ffc-ae1e-e4e15a98d4d1', 'Laptop', 'Powerful laptop for work and gaming.', 1299.99, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
```

<div align="right"><kbd><a href="#table-of-contents">↑ Back to top ↑</a></kbd></div>

---

# Endpoints

<p align="justify">
The application documentation was generated using Swagger. One of the key advantages of using 
tools like Swagger is that the documentation remains constantly updated, ensuring accuracy and alignment
with the latest API changes.
</p>

<div align="center"><a href="https://raw.githubusercontent.com/willianrsouza/ITeam-ProductManagerAPI-Challenge/refs/heads/master/image/swagger.png?token=GHSAT0AAAAAADAINYUFRVJLI6DEBFUGTWTAZ7OZSBA">
<img src="https://raw.githubusercontent.com/willianrsouza/ITeam-ProductManagerAPI-Challenge/refs/heads/master/image/swagger.png?token=GHSAT0AAAAAADAINYUFRVJLI6DEBFUGTWTAZ7OZSBA" alt="Diagram showing IoT-to-Cloud (Nebula) network connections" style="width:70%;height:70%"/></a></div>

## Product Endpoints

### Create a Product
**Endpoint:** `POST /api/products/createProduct`
- **Description:** Creates a new product.
- **Request Body:**
  ```json
  {
    "name": "Product Name",
    "description": "Product Description",
    "price": 100.0,
    "categoryId": "uuid"
  }
  ```
- **Responses:**
    - `200 OK` - Product created successfully.
    - `400 Bad Request` - Invalid input data.
    - `403 Forbidden` - User does not have permission.
    - `500 Internal Server Error` - Unexpected server error.

### Find Product by ID
**Endpoint:** `GET /api/products/{id}`
- **Description:** Retrieves a product by its ID.
- **Path Parameter:** `id` (UUID)
- **Responses:**
    - `200 OK` - Returns the product data.
    - `404 Not Found` - Product not found.
    - `500 Internal Server Error` - Unexpected server error.

### Delete Product by ID
**Endpoint:** `DELETE /api/products/{id}`
- **Description:** Deletes a product by its ID.
- **Path Parameter:** `id` (UUID)
- **Responses:**
    - `204 No Content` - Product successfully deleted.
    - `403 Forbidden` - User does not have permission.
    - `404 Not Found` - Product not found.
    - `500 Internal Server Error` - Unexpected server error.

### Update Product by ID
**Endpoint:** `PUT /api/products/{id}`
- **Description:** Updates an existing product by ID.
- **Path Parameter:** `id` (UUID)
- **Request Body:**
  ```json
  {
    "name": "Updated Product Name",
    "description": "Updated Description",
    "price": 150.0
  }
  ```
- **Responses:**
    - `200 OK` - Product successfully updated.
    - `400 Bad Request` - Invalid input data.
    - `403 Forbidden` - User does not have permission.
    - `404 Not Found` - Product not found.
    - `500 Internal Server Error` - Unexpected server error.

### Search Products with Filters
**Endpoint:** `GET /api/products/search`
- **Description:** Retrieves a paginated list of products based on filters.
- **Query Parameters (Optional):**
    - `categoryId` (UUID)
    - `minPrice` (Decimal)
    - `maxPrice` (Decimal)
    - `sortBy` (String, default: `name`)
    - `order` (String, default: `asc`)
    - `page` (Integer, default: `0`)
    - `pageSize` (Integer, default: `5`)
- **Responses:**
    - `200 OK` - Returns a list of products.
    - `404 Not Found` - No products found.
    - `500 Internal Server Error` - Unexpected server error.

## Category Endpoints

### Get All Categories
**Endpoint:** `GET /api/categories`
- **Description:** Retrieves all categories.
- **Responses:**
    - `200 OK` - Returns a list of categories.
    - `404 Not Found` - No categories found.
    - `500 Internal Server Error` - Unexpected server error.

### Get Category by ID
**Endpoint:** `GET /api/categories/{id}`
- **Description:** Retrieves a specific category by its ID.
- **Path Parameter:** `id` (UUID)
- **Responses:**
    - `200 OK` - Returns the category data.
    - `404 Not Found` - Category not found.
    - `500 Internal Server Error` - Unexpected server error.


<div align="right"><kbd><a href="#table-of-contents">↑ Back to top ↑</a></kbd></div>

---

# Authentication

The API uses **Basic Authentication** for securing endpoints. To authenticate in Swagger UI, follow the steps below:

## 1. Access Swagger UI
After starting the application, open Swagger in your browser:

```
http://localhost:8081/swagger-ui/index.html
```

## 2. Authenticate using Basic Auth
To access protected endpoints, you need to provide valid credentials.

### Available Users:

#### Administrator

- **Email:** `admin@iteam.com`
- **Password:** `password`

#### Regular User

- **Email:** `user@iteam.com`
- **Password:** `password`

### Steps to Authenticate:

1. Click on the **Authorize** button in the Swagger UI.
2. A pop-up will appear asking for **Username** and **Password**.
3. Enter one of the credentials provided above.
4. Click **Authorize**.
5. Close the pop-up and now you can access protected endpoints!

_Once authenticated, Swagger will automatically send the credentials with each request.
To log out, click **Authorize** again and then click **Logout**._

<div align="right"><kbd><a href="#table-of-contents">↑ Back to top ↑</a></kbd></div>

---
<!-- markdownlint-enable -->