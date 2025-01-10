
# Project Management

Project Management is a comprehensive application designed to help manage projects, employees, and their assignments efficiently. It features a robust backend implemented in Java and Spring Boot, with a user-friendly frontend to ensure seamless project tracking and management.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [Directory Structure](#directory-structure)
- [Technologies Used](#technologies-used)
- [Running the Application](#running-the-application)
- [License](#license)

## Features
- **Project Management**: Create, update, and track projects.
- **Employee Management**: Manage employee information and their project assignments.
- **Role-Based Access Control**: Secure different parts of the application based on user roles.
- **Data Visualization**: View project timelines and employee assignments with interactive charts.
- **Error Handling**: User-friendly error pages for a better user experience.

## Getting Started

### Prerequisites
- Java 8 or higher
- Maven 3.6 or higher
- PostgreSQL or any other relational database

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Farooq2018/project-management.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Farooq2018-project-management
   ```
3. Build the project using Maven:
   ```bash
   ./mvnw clean install
   ```
4. Configure the database connection in `src/main/resources/application.properties`.

## Directory Structure
```
Farooq2018-project-management/
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── farooq/
│   │   │           └── project_management/
│   │   │               ├── ProjectManagementApplication.java
│   │   │               ├── api/
│   │   │               │   └── controller/
│   │   │               │       ├── EmployeeApiController.java
│   │   │               │       └── ProjectApiController.java
│   │   │               ├── controller/
│   │   │               │   ├── AppErrorController.java
│   │   │               │   ├── EmployeeController.java
│   │   │               │   ├── HomeController.java
│   │   │               │   ├── ProjectController.java
│   │   │               │   └── SecurityController.java
│   │   │               ├── dto/
│   │   │               │   ├── ChartData.java
│   │   │               │   ├── EmployeeProject.java
│   │   │               │   └── TimeChartData.java
│   │   │               ├── entity/
│   │   │               │   ├── Employee.java
│   │   │               │   ├── Project.java
│   │   │               │   ├── Role.java
│   │   │               │   └── UserAccount.java
│   │   │               ├── logging/
│   │   │               │   └── ApplicationLoggerAspect.java
│   │   │               ├── repository/
│   │   │               │   ├── EmployeeRepository.java
│   │   │               │   ├── ProjectRepository.java
│   │   │               │   ├── RoleRepository.java
│   │   │               │   └── UserAccountRepository.java
│   │   │               ├── security/
│   │   │               │   └── SecurityConfiguration.java
│   │   │               ├── service/
│   │   │               │   ├── EmployeeService.java
│   │   │               │   ├── ProjectService.java
│   │   │               │   └── UserAccountDetailsService.java
│   │   │               └── validator/
│   │   │                   ├── UniqueValidator.java
│   │   │                   └── UniqueValue.java
│   │   └── resources/
│   │       ├── application-dev.properties
│   │       ├── application-prod.properties
│   │       ├── application-test.properties
│   │       ├── application.properties
│   │       ├── META-INF/
│   │       │   └── MANIFEST.MF
│   │       ├── static/
│   │       │   └── myChart.js
│   │       └── templates/
│   │           ├── layouts.html
│   │           ├── employees/
│   │           │   ├── list-employees.html
│   │           │   └── new-employee.html
│   │           ├── errorpages/
│   │           │   ├── error-403.html
│   │           │   ├── error-404.html
│   │           │   ├── error-405.html
│   │           │   ├── error-500.html
│   │           │   └── error.html
│   │           ├── main/
│   │           │   └── home.html
│   │           ├── projects/
│   │           │   ├── list-projects.html
│   │           │   ├── new-project.html
│   │           │   └── project-timelines.html
│   │           └── security/
│   │               └── register.html
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── farooq/
│       │           └── project_management/
│       │               └── ProjectManagementApplicationTests.java
│       └── resources/
│           ├── application.properties
│           ├── data.sql
│           ├── drop.sql
│           └── schema.sql
└── .mvn/
    └── wrapper/
        └── maven-wrapper.properties
```

## Technologies Used
- **Java**: Core programming language
- **Spring Boot**: Framework for building the backend
- **Maven**: Build automation tool
- **Thymeleaf**: Template engine for the frontend
- **PostgreSQL**: Database management
- **HTML/CSS/JavaScript**: Frontend technologies

## Running the Application
1. Ensure your database is up and running.
2. Configure your `application.properties` file with the correct database credentials.
3. Build and run the application using the following command:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Access the application at `http://localhost:8080`.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
