<h1>#Student Management API</h1>

This project is a simple Student Management API built using Spring Boot. It handles student and subject enrollment, student details management, and user authentication. The system supports functionalities such as user registration, login, student creation, updating details, deleting students, and enrolling students in subjects.
Features

    User Registration: Allows users to register with a username and password.
    User Authentication: Authenticate users and generate a JWT token for secure access.
    Student Management: Create, update, delete, and fetch student details.
    Enrollment: Enroll students in subjects.
    Security: JWT-based authentication and role-based access control.

Tech Stack

    Java 17
    Spring Boot: For creating RESTful APIs
    Spring Security: For authentication and authorization
    JPA (Hibernate): For database interactions
    JWT: For token-based authentication
    H2 Database (or any relational DB of your choice)

Prerequisites

Before running the application, make sure you have the following installed:

    Java 17 or later
    Maven
    IDE (IntelliJ IDEA, Eclipse, or any Java IDE)

Setup & Installation
Clone the repository

`git clone https://github.com/yourusername/student-management-api.git`

Navigate to the project directory

`cd student-management-api`

Build the project

Use Maven to build the project:

`mvn clean install`

Run the application

To run the Spring Boot application:

`mvn spring-boot:run`

The application will be available at http://localhost:8080.
Endpoints
User Endpoints

    POST /register/user: Register a new user.
        Request body: { "username": "user1", "password": "password", "adgroup": "group1" }
        Response: User details along with the ID.
    POST /authenticate: Authenticate and generate a JWT token.
        Query Parameters: username, password
        Response: JWT token (Bearer token).

Student Endpoints

    POST /studentdetails: Create a new student.
        Request body: { "srn": "123", "fname": "John", "lname": "Doe", "emailid": "john.doe@example.com", "phoneNumber": "1234567890" }

    GET /studentdetails/{studentID}: Get student details by SRN.
        Path Parameter: studentID

    GET /studentdetails: Get all student details.

    PUT /studentdetails/{studentID}: Update student details.
        Path Parameter: studentID
        Request body: Updated student details.

    DELETE /studentdetails/{studentID}: Delete student details by SRN.
        Path Parameter: studentID

Enrollment Endpoints

    POST /enrollment/enroll: Enroll a student in a subject.
        Query Parameters: studentId, subjectId

Database Configuration

This project uses an in-memory H2 database by default. If you want to use a different database, update the application.properties file with the appropriate database configurations.
Error Handling

Exception Handling

    MissingInputFieldException: Thrown when a required field is missing.
    StudentNotFoundException: Thrown when a student is not found.
    StudentAlreadyExistsException: Thrown when a student already exists.

Security

The API uses JWT for authentication. After registering or logging in, users receive a JWT token that needs to be included in the Authorization header as Bearer <token> for protected endpoints.
Example Header:

Authorization: Bearer <JWT_TOKEN>
