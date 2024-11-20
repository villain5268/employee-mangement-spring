Employee Management System
An Employee Management System built using Spring Boot, Thymeleaf, and Hibernate/JPA, providing a platform to manage employees, departments, projects, attendance, and performance reviews.


![image](https://github.com/user-attachments/assets/276239ca-1574-44da-88d0-b5b056deb668)

![image](https://github.com/user-attachments/assets/05876996-1e24-4be7-8573-534ef113f174)


Features
Employee Management

Add, update, delete, and view employee details.
Export employee data to Excel.
Search employees by name or department.
Department Management

Create, update, and delete departments.
Associate employees with departments.
Project Management

Add, assign, and track ongoing and completed projects.
Attendance Management

Record employee attendance daily.
View individual employee attendance dashboards.
Performance Reviews

Add and manage performance reviews for employees.
Track ratings and comments for specific review periods.
Tech Stack
Backend: Spring Boot, Spring Data JPA, Hibernate
Frontend: Thymeleaf, HTML, JavaScript
Database: MySQL (or any RDBMS)
Build Tool: Maven
Testing: JUnit 5, Spring Boot Test
Deployment: Embedded Tomcat Server
Prerequisites
Ensure the following are installed on your system:

Java JDK 17+
Maven 3.6+
MySQL Server (or another RDBMS)
Git for version control
Getting Started
Clone the Repository

git clone https://github.com/your-username/employee-management-system.git
cd employee-management-system
Configure the Application
Database Configuration:

Update src/main/resources/application.yml with your database credentials:


spring:
  datasource:
    url: jdbc:mysql://localhost:3306/employee_management
    username: your_database_username
    password: your_database_password
  jpa:
    hibernate:
      ddl-auto: update
Port Configuration: By default, the application runs on port 8085. Update application.yml if needed:


server:
  port: 8085
Build and Run the Application

mvn clean install
mvn spring-boot:run
Access the application at http://localhost:8085.

Running Tests
Execute the following command to run the tests:
mvn test

Deployment
You can deploy the application as a JAR or WAR file.

Build JAR:
mvn clean package
java -jar target/employee-management-system-0.0.1-SNAPSHOT.jar
Deploy WAR to Tomcat: Update pom.xml to package as a WAR, then deploy the WAR file to your Tomcat server.

CI/CD Pipeline
This project uses GitHub Actions for continuous integration:

On every push/pull request to the main branch:
Build the project with Maven.
Run the test suite.
Cache dependencies for faster builds.
Example GitHub Actions workflow:

name: Build and Test
on:
  push:
    branches:
      - main
  pull_request:
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v4
    - name: Set up JDK 17
      uses: actions/setup-java@v4
      with:
        java-version: '17'
        distribution: 'temurin'
    - name: Cache Maven dependencies
      uses: actions/cache@v3
      with:
        path: ~/.m2
        key: ${{ runner.os }}-maven-${{ hashFiles('**/pom.xml') }}
        restore-keys: |
          ${{ runner.os }}-maven-
    - name: Build and Test
      run: mvn clean install

Contributions
Contributions are welcome! Follow these steps:

Fork the repository.
Create a feature branch (git checkout -b feature-name).
Commit changes (git commit -m "Add feature").
Push to your branch (git push origin feature-name).
Open a Pull Request.
License
This project is licensed under the MIT License.

Contact
For questions or feedback, feel free to reach out:
Email: dschinku@gmail.com

This README.md provides a professional overview of your project, guiding users and contributors effectively.
