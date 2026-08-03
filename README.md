# KAALA Plus ERP

Backend of an ERP system designed to manage the complete purchasing and logistics workflow between international customers and Chinese suppliers.

KAALA Plus ERP is a real-world software project being developed for an import-export business operating in China. The application aims to digitize and simplify the entire purchasing process, from customer orders to supplier management, payments, expenses, and container shipments.

Besides solving a real business problem, this project also serves as a technical showcase of my software engineering skills in Java, Spring Boot, REST API development, database design, and enterprise application architecture.

---

# Features

The backend currently provides REST APIs for managing:

- Customers
- Suppliers
- Products
- Supplier Products
- Orders
- Order Items
- Payments
- Expenses
- Containers
- Currencies

Additional modules will be introduced as the project evolves.

---

# Tech Stack

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven

Planned technologies

- Docker
- Spring Security (JWT)
- OpenAPI / Swagger
- JUnit & Mockito

---

# Project Architecture

The project follows a **feature-based package structure**, where each business domain is isolated into its own package.

```
com.thifuge.kaala_plus
│
├── clients
├── containers
├── expenses
├── order_items
├── orders
├── payments
├── products
├── supplier_products
├── suppliers
└── shared
    ├── entities
    ├── exceptions
    ├── repositories
    └── services
```

Each business module contains its own:

- Entity
- Repository
- Service
- REST Controller

Shared components are grouped inside the `shared` package to promote reuse across multiple modules.

This architecture keeps the project modular, scalable, and easy to maintain.

---

# Database

The application uses PostgreSQL as its relational database.

Current core entities:

- Client
- Supplier
- Product
- SupplierProduct
- Order
- OrderItem
- Payment
- Expense
- Container
- Currency

The database has been designed with extensibility in mind and will later support features such as:

- Multi-user authentication
- Roles & Permissions
- Customer Portal
- Supplier Portal
- Shipment Tracking
- Document Management
- Reporting

---

# Business Rules

Some business rules currently implemented or planned include:

- An order cannot be deleted once a payment has been registered.
- Order items remain editable after validation to support quantity updates requested by customers.
- Total payments cannot exceed the total order amount.
- Every expense must be linked to an order.
- Suppliers can be changed after an order has been created.
- An order can only be completed after the full amount has been paid.

---

# REST API

Example endpoints

```
GET     /api/clients
POST    /api/clients

GET     /api/orders
POST    /api/orders

GET     /api/payments
POST    /api/payments

GET     /api/products
POST    /api/products
```

---

# Running the Project

Clone the repository

```bash
git clone https://github.com/thiewere/ms-kaala-plus.git
```

Navigate to the project directory

```bash
cd ms-kaala-plus
```

Run the application

```bash
./mvnw spring-boot:run
```

---

# Project Goals

The main objectives of this project are:

- Deliver a real ERP solution for an import-export business.
- Build a maintainable and scalable backend architecture.
- Apply software engineering best practices.
- Design a normalized relational database.
- Implement clean RESTful APIs.
- Develop reusable business modules.
- Continuously improve the project using modern Spring Boot technologies.

---

# Roadmap

## Current Phase

- Database Modeling
- CRUD APIs
- Business Rules
- Exception Handling

## Next Steps

- DTO Layer
- Validation
- Authentication (JWT)
- Role Management
- Swagger / OpenAPI
- Unit Tests
- Docker Support
- Reporting Module
- Dashboard Analytics

---

# Author

**Ousmane Sow**

Java Backend Developer

Passionate about building scalable enterprise applications using Java, Spring Boot, PostgreSQL and Angular.

This repository reflects my approach to designing maintainable business applications while solving real-world problems.