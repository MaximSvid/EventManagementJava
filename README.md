### Event Management System - Backend
## Ein Spring Boot REST API Backend für das Event Management System. Bietet vollständige Event-Verwaltung mit Benutzer-Authentifizierung und Event-Registrierung.

## 📋 Inhaltsverzeichnis
Überblick
Technologie-Stack
Projektstruktur
Installation & Setup
Datenbank-Konfiguration
API Dokumentation
Architektur
Testing
Konfiguration
Troubleshooting

## 📖 Überblick
Das Backend stellt eine RESTful API zur Verfügung für:

Benutzer-Management - Registrierung und Authentifizierung
Event-Management - CRUD-Operationen für Events
Event-Registrierung - Anmeldung und Verwaltung von Teilnehmern
Datenvalidierung - Input-Validierung und Error-Handling
🛠 Technologie-Stack
Spring Boot 3.2.x - Java Application Framework
Spring Web - REST API Development
Spring Data JPA - Object-Relational Mapping
Spring Validation - Input Validation
MySQL 8.0+ - Produktions-Datenbank
H2 Database - Test- und Development-Datenbank
Maven 3.8+ - Build Management
Java 17+ - Programming Language

## 📁 Projektstruktur
backend/
├── src/
│   ├── main/
│   │   ├── java/com/eventmanagement/
│   │   │   ├── EventManagementApplication.java    # Main Application Class
│   │   │   ├── config/
│   │   │   │   ├── WebConfig.java                 # CORS & Web Configuration
│   │   │   │   └── DatabaseConfig.java            # Database Configuration
│   │   │   ├── controller/
│   │   │   │   ├── UserController.java            # User REST Endpoints
│   │   │   │   ├── EventController.java           # Event REST Endpoints
│   │   │   │   └── RegistrationController.java    # Registration Endpoints
│   │   │   ├── dto/
│   │   │   │   ├── UserDto.java                   # Data Transfer Objects
│   │   │   │   ├── EventDto.java
│   │   │   │   └── RegistrationDto.java
│   │   │   ├── entity/
│   │   │   │   ├── User.java                      # JPA Entities
│   │   │   │   ├── Event.java
│   │   │   │   └── EventRegistration.java
│   │   │   ├── repository/
│   │   │   │   ├── UserRepository.java            # Data Access Layer
│   │   │   │   ├── EventRepository.java
│   │   │   │   └── EventRegistrationRepository.java
│   │   │   ├── service/
│   │   │   │   ├── UserService.java               # Business Logic
│   │   │   │   ├── EventService.java
│   │   │   │   └── RegistrationService.java
│   │   │   └── exception/
│   │   │       ├── GlobalExceptionHandler.java    # Exception Handling
│   │   │       └── ResourceNotFoundException.java
│   │   └── resources/
│   │       ├── application.yml                    # Main Configuration
│   │       ├── application-dev.yml                # Development Profile
│   │       ├── application-test.yml               # Test Profile
│   │       └── data.sql                           # Initial Data
│   └── test/
│       └── java/com/eventmanagement/
│           ├── controller/                        # Controller Tests
│           ├── service/                           # Service Tests
│           └── repository/                        # Repository Tests
├── pom.xml                                        # Maven Dependencies
├── docker-compose.yml                             # Docker MySQL Setup
└── README.md                                      # This file

## 🚀 Installation & Setup
Voraussetzungen
Java 17+ (OpenJDK oder Oracle JDK)
Maven 3.8+
MySQL 8.0+ oder Docker
Git
Schnellstart
Repository klonen:
bash
git clone <repository-url>
cd event-management-challenge/backend
Dependencies installieren:
bash
./mvnw clean install
Datenbank starten (Docker):
bash
docker-compose up -d
Anwendung starten:
bash
./mvnw spring-boot:run
Die API ist verfügbar unter: http://localhost:8080

## 🗄 Datenbank-Konfiguration
Option A: Docker MySQL (Empfohlen)
docker-compose.yml:

yaml
version: '3.8'
services:
  mysql:
    image: mysql:8.0
    container_name: eventmanagement-mysql
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: eventmanagement
      MYSQL_USER: admin
      MYSQL_PASSWORD: password
    ports:
      - "3306:3306"
    volumes:
      - mysql_data:/var/lib/mysql

volumes:
  mysql_data:
Starten:

bash
docker-compose up -d
Option B: Lokale MySQL Installation
MySQL installieren und starten
Datenbank erstellen:
sql
CREATE DATABASE eventmanagement CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON eventmanagement.* TO 'admin'@'localhost';
FLUSH PRIVILEGES;
Option C: H2 In-Memory Database (Development)
application-dev.yml:

yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
    username: sa
    password: 
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
Profil aktivieren:

bash
./mvnw spring-boot:run -Dspring.profiles.active=dev
H2 Console: http://localhost:8080/h2-console

## 🔗 API Dokumentation
Base URL
http://localhost:8080/api
User Management
Benutzer registrieren
http
POST /api/users/register
Content-Type: application/json

{
  "username": "johndoe",
  "email": "john@example.com",
  "role": "PARTICIPANT"
}
Response:

json
{
  "id": 1,
  "username": "johndoe",
  "email": "john@example.com",
  "role": "PARTICIPANT",
  "createdAt": "2024-01-15T10:00:00"
}
Benutzer anmelden
http
POST /api/users/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123"
}
Event Management
Alle Events abrufen
http
GET /api/events
Response:

json
[
  {
    "id": 1,
    "title": "Flutter Meetup Berlin",
    "description": "Monatliches Flutter Entwickler Treffen",
    "date": "2024-07-15T18:00:00",
    "location": "Berlin, Deutschland",
    "maxParticipants": 50,
    "currentParticipants": 12,
    "organizerId": 1,
    "organizerName": "Max Mustermann"
  }
]
Event-Details abrufen
http
GET /api/events/{id}
Neues Event erstellen
http
POST /api/events
Content-Type: application/json

{
  "title": "React Workshop",
  "description": "Hands-on React Development Workshop",
  "date": "2024-08-20T14:00:00",
  "location": "München, Deutschland",
  "maxParticipants": 30,
  "organizerId": 1
}
Event löschen
http
DELETE /api/events/{id}
Event Registration
Für Event anmelden
http
POST /api/events/{eventId}/register
Content-Type: application/json

{
  "userId": 2
}
Benutzer-Anmeldungen abrufen
http
GET /api/users/{userId}/registrations
Error Responses
404 Not Found:

json
{
  "timestamp": "2024-01-15T10:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Event with ID 999 not found",
  "path": "/api/events/999"
}
400 Bad Request:

json
{
  "timestamp": "2024-01-15T10:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": [
    {
      "field": "title",
      "message": "Title is required"
    }
  ]
}

## 🏗 Architektur
Layered Architecture
┌─────────────────┐
│   Controllers   │  ← REST Endpoints, Request/Response Handling
├─────────────────┤
│    Services     │  ← Business Logic, Transaction Management
├─────────────────┤
│  Repositories   │  ← Data Access Layer
├─────────────────┤
│    Entities     │  ← JPA Models
└─────────────────┘
Entity Relationships
User
├── id (PK)
├── username
├── email
├── role (ORGANIZER/PARTICIPANT)
└── createdAt

Event
├── id (PK)
├── title
├── description
├── date
├── location
├── maxParticipants
├── organizerId (FK → User.id)
└── createdAt

EventRegistration
├── id (PK)
├── userId (FK → User.id)  
├── eventId (FK → Event.id)
└── registrationDate
Design Patterns
Repository Pattern: Datenbank-Abstraktionsschicht
Service Layer Pattern: Business Logic Kapselung
DTO Pattern: Data Transfer Objects für API
Builder Pattern: Entity-Erstellung
Strategy Pattern: Verschiedene Authentifizierungs-Strategien

## 🧪 Testing
Unit Tests ausführen
bash
./mvnw test
Spezifische Test-Klassen
bash
# Service Tests
./mvnw test -Dtest=EventServiceTest

# Repository Tests  
./mvnw test -Dtest=EventRepositoryTest

# Controller Tests
./mvnw test -Dtest=EventControllerTest
Integration Tests
bash
./mvnw test -Dtest=EventIntegrationTest
Test Coverage
bash
./mvnw jacoco:report
Report verfügbar unter: target/site/jacoco/index.html

Beispiel Test-Struktur
Service Test:

java
@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    
    @Mock
    private EventRepository eventRepository;
    
    @InjectMocks
    private EventService eventService;
    
    @Test
    void shouldCreateEvent() {
        // Given
        Event event = Event.builder()
            .title("Test Event")
            .description("Test Description")
            .build();
            
        when(eventRepository.save(any(Event.class))).thenReturn(event);
        
        // When
        Event result = eventService.createEvent(event);
        
        // Then
        assertThat(result.getTitle()).isEqualTo("Test Event");
    }
}

## ⚙️ Konfiguration
application.yml
yaml
spring:
  application:
    name: event-management-backend
  
  datasource:
    url: jdbc:mysql://localhost:3306/eventmanagement
    username: admin
    password: password
    driver-class-name: com.mysql.cj.jdbc.Driver
  
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true
  
  jackson:
    time-zone: Europe/Berlin
    date-format: yyyy-MM-dd'T'HH:mm:ss

server:
  port: 8080
  servlet:
    context-path: /api

logging:
  level:
    com.eventmanagement: DEBUG
    org.springframework.web: DEBUG
  pattern:
    file: "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/application.log
Profile-spezifische Konfigurationen
Development (application-dev.yml):

yaml
spring:
  jpa:
    show-sql: true
    hibernate:
      ddl-auto: create-drop
  
logging:
  level:
    root: DEBUG
Production (application-prod.yml):

yaml
spring:
  datasource:
    url: ${DATABASE_URL}
    username: ${DATABASE_USERNAME}
    password: ${DATABASE_PASSWORD}
  
  jpa:
    hibernate:
      ddl-auto: validate
    show-sql: false

logging:
  level:
    root: WARN
    com.eventmanagement: INFO
Profil aktivieren
bash
# Development
./mvnw spring-boot:run -Dspring.profiles.active=dev

# Production
./mvnw spring-boot:run -Dspring.profiles.active=prod
🐛 Troubleshooting
Häufige Probleme
1. Anwendung startet nicht
bash
# Port bereits belegt
./mvnw spring-boot:run -Dserver.port=8081

# Abhängigkeiten neu laden
./mvnw clean install -U
2. Datenbank-Verbindungsfehler
bash
# MySQL Container prüfen
docker-compose ps
docker-compose logs mysql

# Verbindung testen
mysql -h localhost -P 3306 -u admin -p eventmanagement
3. JPA/Hibernate Probleme
yaml
# Debug-Logging aktivieren
logging:
  level:
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
4. CORS-Probleme
java
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EventController {
    // ...
}
Logging und Debugging
Detaillierte Logs aktivieren
bash
./mvnw spring-boot:run -Dlogging.level.com.eventmanagement=DEBUG
Actuator Endpoints (Development)
yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,loggers
Verfügbare Endpoints:

GET /actuator/health - Application Health
GET /actuator/info - Application Info
GET /actuator/metrics - Application Metrics
Performance Monitoring
Slow Query Logging
yaml
spring:
  jpa:
    properties:
      hibernate:
        generate_statistics: true
        session:
          events:
            log:
              LOG_QUERIES_SLOWER_THAN_MS: 1000
## 🚀 Deployment
JAR Build erstellen
bash
./mvnw clean package -DskipTests
Docker Image erstellen
dockerfile
FROM openjdk:17-jre-slim

VOLUME /tmp
COPY target/event-management-backend-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
bash
docker build -t event-management-backend .
docker run -p 8080:8080 event-management-backend
Umgebungsvariablen für Production
bash
export DATABASE_URL=jdbc:mysql://prod-server:3306/eventmanagement
export DATABASE_USERNAME=prod_user  
export DATABASE_PASSWORD=secure_password
export SPRING_PROFILES_ACTIVE=prod

./mvnw spring-boot:run

## 📊 Metriken und Monitoring
Spring Boot Actuator
yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      show-details: always
Wichtige Metriken
Response Times: HTTP-Request Dauer
Database Connections: Aktive DB-Verbindungen
JVM Memory: Heap und Non-Heap Usage
Custom Metrics: Event-Erstellungen pro Tag
🔒 Sicherheit
Basis-Sicherheit
Input-Validierung mit Bean Validation
SQL-Injection-Schutz durch JPA
CORS-Konfiguration für Frontend
Error-Handling ohne Sensitive-Data-Exposure
Erweiterte Sicherheit (Roadmap)
java
// JWT Authentication
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // JWT Token Configuration
}
Backend erfolgreich konfiguriert! 🚀


