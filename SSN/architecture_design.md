# SSN Service - Architecture Design

## System Overview

This application implements a Social Security Number (SSN) management system using **black box design principles**. Each module is independently replaceable, with clear interfaces and hidden implementation details.

## Core Primitives

The system is built around three fundamental data types:

### 1. SSN (Social Security Number)
```
Properties:
- ssn: String (9 digits, format: XXX-XX-XXXX)
- issuedDate: LocalDateTime
- status: Enum (ACTIVE, SUSPENDED, REVOKED)
```

### 2. Person
```
Properties:
- personId: Long (auto-generated)
- firstName: String
- middleName: String (optional)
- lastName: String
- dateOfBirth: LocalDate
- placeOfBirth: String
- mothersMaidenName: String
- fathersName: String
- citizenshipStatus: Enum (CITIZEN, PERMANENT_RESIDENT, WORK_VISA)
- ssn: SSN (optional, null until issued)
```

### 3. Application
```
Properties:
- applicationId: Long (auto-generated)
- referenceNumber: String (unique, for tracking)
- person: Person
- applicationDate: LocalDateTime
- status: Enum (PENDING, APPROVED, REJECTED)
- reviewDate: LocalDateTime (optional)
- reviewNotes: String (optional)
- reviewedBy: String (admin username)
```

## Black Box Module Architecture

```
┌─────────────────────────────────────────────────────────┐
│                      UI LAYER (Black Box 1)              │
│  - JavaFX Controllers and Views                          │
│  - Event Handlers                                        │
│  - Input Validation (format only)                        │
└─────────────────────┬───────────────────────────────────┘
                      │ Interface: Service Methods
                      ▼
┌─────────────────────────────────────────────────────────┐
│                  SERVICE LAYER (Black Box 2)             │
│  - SSNService: generate, validate, search                │
│  - ApplicationService: submit, approve, reject           │
│  - PersonService: CRUD operations                        │
│  - AuditService: logging operations                      │
└─────────────────────┬───────────────────────────────────┘
                      │ Interface: Repository Methods
                      ▼
┌─────────────────────────────────────────────────────────┐
│                 REPOSITORY LAYER (Black Box 3)           │
│  - SSNRepository: data access for SSNs                   │
│  - PersonRepository: data access for Persons             │
│  - ApplicationRepository: data access for Applications   │
│  - AuditRepository: data access for audit logs           │
└─────────────────────┬───────────────────────────────────┘
                      │ Interface: JDBC/JPA
                      ▼
┌─────────────────────────────────────────────────────────┐
│                   DATABASE (Black Box 4)                 │
│  - H2 Embedded Database                                  │
│  - Schema: persons, ssns, applications, audit_logs       │
└─────────────────────────────────────────────────────────┘
```

## Module Details

### Black Box 1: UI Layer

**What it does:** Presents information and captures user input

**Interface (what other modules see):**
- Views: ApplicationFormView, SearchView, AdminDashboardView, StatisticsView
- Controllers: Handle user interactions, invoke services
- No business logic - only presentation logic

**Hidden implementation:**
- JavaFX scene graphs
- CSS styling
- Layout managers
- Form validation (format only, not business rules)

**Replaceable by:** Web UI (Spring Boot + React), Mobile App, CLI

---

### Black Box 2: Service Layer

**What it does:** Implements business logic and coordinates operations

**Interface (what other modules see):**

```java
// SSNService Interface
public interface ISSNService {
    SSN generateSSN(Person person) throws SSNException;
    boolean validateSSN(String ssn);
    SSN lookupSSN(String ssn);
    List<SSN> searchByPerson(String firstName, String lastName, LocalDate dob);
    void suspendSSN(String ssn, String reason);
}

// ApplicationService Interface
public interface IApplicationService {
    Application submitApplication(Person person) throws ApplicationException;
    Application approveApplication(Long applicationId, String adminUser) throws ApplicationException;
    Application rejectApplication(Long applicationId, String reason, String adminUser);
    List<Application> getPendingApplications();
    Application getApplicationByReference(String referenceNumber);
}

// PersonService Interface
public interface IPersonService {
    Person createPerson(Person person) throws ValidationException;
    Person updatePerson(Person person) throws ValidationException;
    Person findById(Long id);
    List<Person> findByName(String firstName, String lastName);
}

// AuditService Interface
public interface IAuditService {
    void logAccess(String user, String action, String details);
    List<AuditLog> getAuditLogs(LocalDateTime from, LocalDateTime to);
}
```

**Hidden implementation:**
- SSN generation algorithm (area-group-serial logic)
- Business rule validation
- Transaction management
- Error handling

**Replaceable by:** Different business rules, different SSN format, different validation logic

---

### Black Box 3: Repository Layer

**What it does:** Manages data persistence and retrieval

**Interface (what other modules see):**

```java
// Repository Interface (Generic)
public interface IRepository<T, ID> {
    T save(T entity);
    T findById(ID id);
    List<T> findAll();
    void delete(ID id);
    boolean exists(ID id);
}

// SSNRepository Interface (extends IRepository)
public interface ISSNRepository extends IRepository<SSN, String> {
    SSN findBySSN(String ssn);
    List<SSN> findByPersonId(Long personId);
    boolean ssnExists(String ssn);
}

// ApplicationRepository Interface
public interface IApplicationRepository extends IRepository<Application, Long> {
    Application findByReferenceNumber(String refNumber);
    List<Application> findByStatus(ApplicationStatus status);
    List<Application> findByDateRange(LocalDateTime from, LocalDateTime to);
}

// PersonRepository Interface
public interface IPersonRepository extends IRepository<Person, Long> {
    List<Person> findByName(String firstName, String lastName);
    Person findBySSN(String ssn);
    List<Person> findByDateOfBirth(LocalDate dob);
}
```

**Hidden implementation:**
- SQL queries
- Connection pooling
- H2 database specifics
- Object-relational mapping

**Replaceable by:** PostgreSQL, MySQL, MongoDB, in-memory storage

---

### Black Box 4: Database

**What it does:** Stores and retrieves data persistently

**Interface (what other modules see):**
- Standard JDBC or JPA interface
- Connection URL and credentials

**Hidden implementation:**
- H2 storage engine
- Indexing strategies
- File format

**Replaceable by:** Any JDBC-compliant database

---

## Database Schema

```sql
-- Persons Table
CREATE TABLE persons (
    person_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    middle_name VARCHAR(100),
    last_name VARCHAR(100) NOT NULL,
    date_of_birth DATE NOT NULL,
    place_of_birth VARCHAR(200) NOT NULL,
    mothers_maiden_name VARCHAR(100) NOT NULL,
    fathers_name VARCHAR(100) NOT NULL,
    citizenship_status VARCHAR(50) NOT NULL,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- SSNs Table
CREATE TABLE ssns (
    ssn VARCHAR(11) PRIMARY KEY,  -- XXX-XX-XXXX
    person_id BIGINT NOT NULL,
    issued_date TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    FOREIGN KEY (person_id) REFERENCES persons(person_id)
);

-- Applications Table
CREATE TABLE applications (
    application_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reference_number VARCHAR(50) UNIQUE NOT NULL,
    person_id BIGINT NOT NULL,
    application_date TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    review_date TIMESTAMP,
    review_notes TEXT,
    reviewed_by VARCHAR(100),
    FOREIGN KEY (person_id) REFERENCES persons(person_id)
);

-- Audit Logs Table
CREATE TABLE audit_logs (
    log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    timestamp TIMESTAMP NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    action VARCHAR(100) NOT NULL,
    details TEXT,
    ip_address VARCHAR(50)
);

-- Indexes for performance
CREATE INDEX idx_person_name ON persons(last_name, first_name);
CREATE INDEX idx_person_dob ON persons(date_of_birth);
CREATE INDEX idx_ssn_person ON ssns(person_id);
CREATE INDEX idx_app_status ON applications(status);
CREATE INDEX idx_app_refnum ON applications(reference_number);
CREATE INDEX idx_audit_date ON audit_logs(timestamp);
```

## Data Flow Examples

### Flow 1: Apply for New SSN
```
User (UI) → ApplicationController.submitApplication()
           → ApplicationService.submitApplication(Person)
           → PersonRepository.save(person)
           → ApplicationRepository.save(application)
           → AuditService.logAccess("user", "SUBMIT_APP", details)
           ← Return Application with referenceNumber
```

### Flow 2: Approve Application & Generate SSN
```
Admin (UI) → AdminController.approveApplication(applicationId)
            → ApplicationService.approveApplication(applicationId, admin)
            → SSNService.generateSSN(person)
            → SSNRepository.save(ssn)
            → ApplicationRepository.update(application)
            → AuditService.logAccess("admin", "APPROVE_APP", details)
            ← Return approved Application with SSN
```

### Flow 3: Search for SSN
```
User (UI) → SearchController.searchSSN(criteria)
           → SSNService.searchByPerson(firstName, lastName, dob)
           → PersonRepository.findByName(firstName, lastName)
           → SSNRepository.findByPersonId(personId)
           → AuditService.logAccess("user", "SEARCH_SSN", criteria)
           ← Return List<SSN> (masked for non-admin)
```

## Design Patterns Applied

### 1. Repository Pattern
- Abstracts data access
- Allows database switching without code changes
- Single source of truth for queries

### 2. Service Layer Pattern
- Encapsulates business logic
- Coordinates multiple repositories
- Transaction boundaries

### 3. Dependency Injection
- Loose coupling between modules
- Easy to test with mocks
- Constructor injection for required dependencies

### 4. Strategy Pattern
- SSN generation algorithm can be swapped
- Validation rules can be customized
- Different authentication strategies

### 5. Observer Pattern (for UI updates)
- UI updates automatically when data changes
- Decouples UI from business logic

## SOLID Principles Application

### Single Responsibility Principle (SRP)
- SSNService: Only generates and validates SSNs
- PersonRepository: Only handles Person data access
- ApplicationService: Only manages application workflow
- Each class has one reason to change

### Open/Closed Principle (OCP)
- Services implement interfaces
- New features added through new implementations
- Existing code not modified for extensions

### Liskov Substitution Principle (LSP)
- All repository implementations interchangeable
- Mock repositories for testing
- Different UI implementations (JavaFX, Web) work same way

### Interface Segregation Principle (ISP)
- Small, focused interfaces
- Clients don't depend on unused methods
- ISSNService separate from IApplicationService

### Dependency Inversion Principle (DIP)
- High-level modules depend on abstractions (interfaces)
- Low-level modules implement interfaces
- No direct dependencies on concrete classes

## Error Handling Strategy

### Exception Hierarchy
```
SSNException (checked)
  ├─ InvalidSSNFormatException
  ├─ DuplicateSSNException
  └─ SSNGenerationException

ApplicationException (checked)
  ├─ InvalidApplicationException
  ├─ ApplicationNotFoundException
  └─ ApplicationAlreadyProcessedException

RepositoryException (unchecked - runtime)
  ├─ DataAccessException
  └─ DatabaseConnectionException

ValidationException (checked)
  ├─ InvalidDateException
  ├─ MissingRequiredFieldException
  └─ InvalidFormatException
```

### Error Handling Rules
1. **Service Layer:** Catches repository exceptions, wraps in service exceptions
2. **UI Layer:** Catches service exceptions, displays user-friendly messages
3. **Repository Layer:** Throws specific exceptions for data issues
4. **Logging:** All exceptions logged with context

## Security Considerations

### Data Protection
- SSNs masked in UI for non-admin users (XXX-XX-1234)
- Role-based access control (RBAC)
- Audit trail for all sensitive operations

### Input Validation
- UI: Format validation (length, characters)
- Service: Business rule validation
- Repository: Database constraints

### Authentication & Authorization
- Simple role system: PUBLIC, ADMIN
- Session management
- Password hashing (for future expansion)

## Testing Strategy

### Unit Tests
- Each service method tested independently
- Mock repositories using Mockito
- Test all validation rules

### Integration Tests
- Test service + repository integration
- Use in-memory H2 database
- Test complete workflows

### UI Tests
- Manual testing for initial version
- Future: TestFX for automated UI tests

## Configuration Management

### Application Properties
```properties
# Database Configuration
db.url=jdbc:h2:./data/ssn_db
db.user=sa
db.password=
db.driver=org.h2.Driver

# Application Settings
app.ssn.area.start=001
app.ssn.area.end=899
app.audit.enabled=true
app.session.timeout=30

# UI Settings
ui.theme=modern
ui.window.width=1200
ui.window.height=800
```

### Configuration Loading
- Properties file in classpath
- Environment variables override
- Default values for missing properties

## Project Structure

```
ssn-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ssn/
│   │   │       ├── model/           # Domain primitives
│   │   │       │   ├── SSN.java
│   │   │       │   ├── Person.java
│   │   │       │   ├── Application.java
│   │   │       │   ├── AuditLog.java
│   │   │       │   └── enums/       # Enums
│   │   │       ├── repository/      # Data access black box
│   │   │       │   ├── IRepository.java
│   │   │       │   ├── ISSNRepository.java
│   │   │       │   ├── IPersonRepository.java
│   │   │       │   ├── IApplicationRepository.java
│   │   │       │   └── impl/        # H2 implementations
│   │   │       ├── service/         # Business logic black box
│   │   │       │   ├── ISSNService.java
│   │   │       │   ├── IApplicationService.java
│   │   │       │   ├── IPersonService.java
│   │   │       │   ├── IAuditService.java
│   │   │       │   └── impl/        # Service implementations
│   │   │       ├── ui/              # Presentation black box
│   │   │       │   ├── MainApp.java
│   │   │       │   ├── controller/
│   │   │       │   └── view/
│   │   │       ├── config/          # Configuration
│   │   │       │   └── AppConfig.java
│   │   │       ├── exception/       # Custom exceptions
│   │   │       └── util/            # Utilities
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── schema.sql
│   │       └── css/
│   │           └── styles.css
│   └── test/
│       └── java/
│           └── com/ssn/
│               ├── service/
│               └── repository/
├── pom.xml
└── README.md
```

## Build and Deployment

### Maven Dependencies
- JavaFX 17+ (UI)
- H2 Database (embedded database)
- JUnit 5 (testing)
- Mockito (mocking)
- SLF4J + Logback (logging)

### Running the Application
```bash
mvn clean package
java -jar target/ssn-service-1.0.0.jar
```

### Database Initialization
- Auto-create schema on first run
- Seed data for testing (optional)
- Database file: `./data/ssn_db.mv.db`

## Future Enhancements (Modular)

Thanks to black box design, these can be added independently:

1. **Replace UI:** Swap JavaFX for web interface (Spring Boot + React)
2. **Replace Database:** Swap H2 for PostgreSQL for production
3. **Add Authentication:** Add Spring Security without changing business logic
4. **Add API Layer:** Add REST API without modifying services
5. **Add Reporting:** New reporting service using existing repositories
6. **Add Notifications:** Email/SMS service for application updates

Each enhancement is a new module or replacement of existing module via its interface.
