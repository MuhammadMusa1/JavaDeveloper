To-Do List REST API
Overview
This is a simple Spring Boot application that provides a REST API for managing a To-Do list. It supports basic CRUD operations for tasks using an in-memory H2 database.
Prerequisites

Java 21
Maven

Setup

Clone the repository or copy the project files.
Navigate to the project directory.
Run mvn spring-boot:run to start the application.

API Endpoints

GET /api/todos: Retrieve all to-do items
POST /api/todos: Create a new to-do item
GET /api/todos/{id}: Retrieve a specific to-do item by ID
PUT /api/todos/{id}: Update a to-do item
DELETE /api/todos/{id}: Delete a to-do item

Example JSON Payload for POST/PUT
{
    "title": "Complete project",
    "completed": false
}

H2 Console

Access the H2 database console at http://localhost:8080/h2-console
Use JDBC URL: jdbc:h2:mem:tododb
Username: sa
Password: (leave empty)

Project Structure

Todo.java: Entity class for the To-Do item
TodoRepository.java: JPA repository interface
TodoController.java: REST controller with API endpoints
TodoAppApplication.java: Main Spring Boot application class
application.properties: Configuration for H2 database
