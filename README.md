# Kanban Application

This repository contains the code for a Kanban application, a task management tool built with Java, Spring Boot, and a database. The application allows users to create boards, add tasks, and manage task flow using the Kanban methodology.

## Table of Contents

1. [Introduction](#introduction)
2. [Features](#features)
3. [Technologies](#technologies)
4. [Installation](#installation)
5. [Configuration](#configuration)
6. [Running the Application](#running-the-application)
7. [Testing](#testing)
8. [Contributing](#contributing)
9. [License](#license)

## Introduction

The Kanban app provides a user-friendly interface to manage tasks and workflows, allowing users to visualize and track their work. It supports creating boards, adding tasks, moving tasks through columns, and user management for team collaboration.

## Features

- **Create Boards**: Users can create boards to organize their tasks.
- **Task Management**: Add, edit, and delete tasks within a board.
- **Task Movement**: Tasks can be moved between columns to reflect their current status (e.g., "To Do," "In Progress," "Completed").
- **User Assignment**: Assign tasks to users and track progress.
- **Task Filtering**: Filter tasks by status and other parameters.

## Technologies

The Kanban app is built using the following technologies:

- **Java 17**: For the backend.
- **Spring Boot**: For building RESTful services and managing application configuration.
- **Spring Data JPA**: For handling database operations.
- **H2 Database**: For local development and testing (can be replaced with any relational database in production).
- **JUnit 5**: For unit and integration testing.

## Installation

To get started with the Kanban application locally, follow these steps:

1. **Clone the repository**
2. **Build the project using Maven:**  

    ```bash 
    mvn clean install
    ```
3. **Run the application locally:**

    ```bash
   mvn spring-boot:run
   ```

## Configuration

Before running the application, you may need to configure the following:

- Database Configuration: In application.properties, set up the database connection. By default, the application uses an H2 database for local development. You can switch to a production database (e.g., MySQL or PostgreSQL) by changing the connection properties. In the file called postgres-application.properties properties file is already setup and ready to be used. But username, password and url of databse need to be configured.

## Testing

The project includes unit and integration tests using JUnit. To run the tests, execute:

```bash
mvn test
```

