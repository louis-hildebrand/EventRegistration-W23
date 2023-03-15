# EventRegistration

Example project for the ECSE 321 tutorials.

## Setup

Check [`application.properties`](https://github.com/louis-hildebrand/EventRegistration-W23/blob/master/EventRegistration-Backend/src/main/resources/application.properties) for the database configuration (port, database name, username, and password). You can change the values to match your own setup if needed.

This project was developed with the following versions (but it should still work if your versions are not too far off).
- PostgreSQL 15.2
- JavaSE-17
- Gradle 7.6
- Spring Boot 3.0.2
- Windows 10

## Running the App

- To run the backend tests: `./gradlew test`
- To start the backend: `./gradlew bootRun`
    - The backend will be running on the port specified in `server.port` in [`application.properties`](https://github.com/louis-hildebrand/EventRegistration-W23/blob/master/EventRegistration-Backend/src/main/resources/application.properties).
