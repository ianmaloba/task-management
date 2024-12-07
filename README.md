# Task Management API with Tags and Authentication

## Overview

The **Task Management API** built with **Spring Boot** allows users to create, manage, and organize tasks with tags. It includes features for task creation, updating, deletion, filtering, audit logging, and authentication to secure user operations.


---

## Features

### **Task Management**
- **Create Tasks**: Add tasks with titles, descriptions, and statuses (`completed` or `not completed`) and associate them with tags.
- **Update Tasks**
- **Delete Tasks**: Without deleting their associated tags.
- **Search Tasks**: Search by title, description, or associated tags.
- **View Tasks**: Retrieve tasks, filter by status, and display tags.

### **Tag Management**
- **Create Tags**
- **View Tags**
- **Edit Tags**.
- **Delete Tags**

### **Audit Logs**
- **Track Changes**: Record all changes made to tasks and tags.
- **View Logs**: Access logs of who made changes and when.

### **Authentication & Authorization**
- **User Registration**: Create new users with email and password.
- **User Login**: Login using credentials to generate a JWT (JSON Web Token).
- **Role-Based Access Control (RBAC)**: Users have different roles (e.g., Admin, User) with specific permissions for creating, updating, and deleting tasks or tags.
- **JWT Token Authentication**: Secure API endpoints with JWT tokens for authentication.

---

## Technologies Used

- **Java, Spring Boot, MySQL, JPA/Hibernate, Swagger UI, Thymeleaf, Maven**
---

## Getting Started

### **Prerequisites**
Ensure you have the following installed:
- [Java 21+](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [MySQL](https://www.mysql.com/)
- [Maven](https://maven.apache.org/)

---

### **Setup Instructions**

1. **Clone the Repository**
   ```bash
   git clone https://github.com/ianmaloba/task-management.git
   cd task-management
   ```

1. **Create the Database:**
In MySQL (using Workbench or command line), create a new database:

   ```bash
   CREATE DATABASE task_management;
   ```

2. **Configure the Database**:
Update your MySQL credentials in `src/main/resources/application.properties`:
   ```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/task_management?useSSL=false&serverTimezone=UTC
    spring.datasource.username=<your-mysql-username>
    spring.datasource.password=<your-mysql-password>

   ```
3. **Run the Application**:

   ```bash
    ./mvnw spring-boot:run

   ```
Vist at http://localhost:8080.

4. **Access the API**:

Open http://localhost:8080/swagger-ui.html to explore the API documentation.

### **API Endpoints**
#### **User Authentication Endpoints**

| Method | Endpoint              | Description                           |
|--------|-----------------------|---------------------------------------|
| POST    | /api/auth/register  | Register a new user                   |
| POST    | /api/auth/login     | Login to get JWT token             |


#### **Task Endpoints**

| Method | Endpoint              | Description                           |
|--------|-----------------------|---------------------------------------|
| GET    | /api/tasks            | Retrieve all tasks                   |
| GET    | /api/tasks/search     | Search tasks by criteria             |
| POST   | /api/tasks            | Create a new task                    |
| PUT    | /api/tasks/{id}       | Update an existing task              |
| DELETE | /api/tasks/{id}       | Delete a task                        |

#### **Tag Endpoints**

| Method | Endpoint              | Description                           |
|--------|-----------------------|---------------------------------------|
| GET    | /api/tags             | Retrieve all tags                    |
| GET    | /api/tags/{id}        | Retrieve details for a tag           |
| POST   | /api/tags             | Create a new tag                     |
| PUT    | /api/tags/{id}        | Update a tag                         |
| DELETE | /api/tags/{id}        | Delete a tag                         |

#### **Audit Log Endpoints**

| Method | Endpoint              | Description                           |
|--------|-----------------------|---------------------------------------|
| GET    | /api/audit-logs       | Retrieve all audit logs              |


### **Search Functionality**

To search for tasks, use the `/api/tasks/search` endpoint. Supported query parameters:
- **title**: Search by task title.
- **description**: Search by task description.
- **tags**: Search tasks associated with specific tags.

**Example Request:**
   ```bash
    GET /api/tasks/search?title=meeting&tags=urgent

   ```
   
### **Directory Structure**

```plaintext
📦 task-management  
├── .gitattributes  
├── .gitignore  
├── .mvn  
│   └── wrapper  
│       └── maven-wrapper.properties  
├── LICENSE  
├── README.md  
├── mvnw  
├── mvnw.cmd  
├── pom.xml  
└── src  
    ├── main  
    │   ├── java  
    │   │   └── com  
    │   │       └── example  
    │   │           └── task_management  
    │   │               ├── TaskManagementApplication.java  
    │   │               ├── config  
    │   │               │   ├── SecurityConfig.java  
    │   │               │   └── SwaggerConfig.java  
    │   │               ├── controllers  
    │   │               │   ├── AuditLogController.java  
    │   │               │   ├── AuthController.java  
    │   │               │   ├── HomeController.java  
    │   │               │   ├── TagController.java  
    │   │               │   └── TaskController.java  
    │   │               ├── models  
    │   │               │   ├── AuditLog.java  
    │   │               │   ├── Tag.java  
    │   │               │   ├── Task.java  
    │   │               │   └── User.java  
    │   │               ├── repositories  
    │   │               │   ├── AuditLogRepository.java  
    │   │               │   ├── TagRepository.java  
    │   │               │   ├── TaskRepository.java  
    │   │               │   └── UserRepository.java  
    │   │               └── services  
    │   │                   ├── AuditService.java  
    │   │                   ├── CustomUserDetailsService.java  
    │   │                   ├── TagService.java  
    │   │                   ├── TaskService.java  
    │   │                   └── UserService.java  
    │   └── resources  
    │       ├── application.properties  
    │       ├── static  
    │       │   └── css  
    │       │       └── style.css  
    │       └── templates  
    │           ├── base.html  
    │           ├── create_tag.html  
    │           ├── create_task.html  
    │           ├── edit_tag.html  
    │           ├── edit_task.html  
    │           ├── index.html  
    │           ├── login.html  
    │           ├── signup.html  
    │           ├── tag_tasks.html  
    │           ├── tags.html  
    │           ├── task_detail.html  
    │           └── tasks.html  
    └── test  
        └── java  
            └── com  
                └── example  
                    └── task_management  
                        └── TaskManagementApplicationTests.java  

```

### **Features in Development**
- **Pagination:** For handling large datasets efficiently.
- **Advanced Audit Logging:** Include the ability to export logs to CSV or PDF.

### **License**
This project is licensed under the [MIT License](https://github.com/ianmaloba/task-management/blob/main/LICENSE).

