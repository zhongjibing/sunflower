# SunFlower


## Description
Sunflower is a comprehensive auxiliary and management system that provides users with basic configuration, business management, and data analysis functionalities. Through a mini-program interface, users can reserve resources, manage resources, configure options, and view reservation records. Both ordinary users and administrators can efficiently complete various tasks through Sunflower.


## Features
### 1. Open User Management

- Automatic WeChat Login: Users can automatically log in by opening the mini-program through WeChat.
- User Management: View all users who have logged into the mini-program, grant or revoke administrator roles, and block or unblock users.

### 2. Resource Management

- Resource Reservation: Manage resources such as banquet halls, conference rooms, and guest rooms.
- Resource Operations: Add, modify, delete, enable/disable resources.

### 3. Price Management

- Pricing Rules: Establish pricing rules for resources.
- Pricing Plans: Automatically regenerate pricing plans after adding, modifying, or deleting pricing rules.

### 4. Configuration Management

- Configuration Options: The selection options used in mini-program reservations need to be configured through configuration management.

### 5. Reservation Functionality

- User Reservations: Users can reserve resources through the mini-program and browse and manage their own reservations.
- Administrator Reservation Management: Administrators can view user-submitted reservation records, confirm reservation results, and view an overview of the reservation status of all resources.


## Installation and Running

### Environment Requirements

- JDK 17 or higher
- Gradle 8.x or higher
- MySQL Database
- Redis (optional, for caching and session management)
- Docker (optional, for containerized deployment)

### Building and Packaging

1. Clone the project code locally:
```bash
git clone https://github.com/zhongjibing/sunflower.git
cd sunflower
```

2. Build the project using Gradle:
```bash
./gradlew build
```

3. After a successful build, the generated JAR file can be found in the `build/libs` directory.

### Running

1. Ensure that the database is configured and configure the database connection information in the `application.yml` file.

2. Run the project using Gradle:
```bash
./gradlew bootRun
```
Alternatively, you can deploy the generated JAR file to any server that supports Java applications and run it using the following command:
```bash
java -jar build/libs/sunflower-1.0.0.jar
```


## Deployment

### Direct Deployment

1. Upload the generated JAR file to the server.
2. Run the JAR file using a command-line tool.

### Deployment Using Docker

1. Write a Dockerfile:
```bash
FROM openjdk:17.0.2  
VOLUME /tmp  
ARG JAR_FILE=build/libs/sunflower-<version>.jar  
COPY ${JAR_FILE} app.jar  
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

2. Build the Docker image:
```bash
docker build -t sunflower .
```

3. Run the Docker container:
```bash
docker run -d -p 8080:8080 sunflower
```
Here, it is assumed that your application runs on port 8080. You can adjust it according to your actual situation.

