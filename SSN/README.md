# SSN Service - Social Security Administration System

A modern, maintainable Social Security Number (SSN) management system built with Java, JavaFX, and H2 database. Designed following **black box design principles** for maximum modularity and maintainability.

![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![JavaFX](https://img.shields.io/badge/JavaFX-21-blue.svg)
![License](https://img.shields.io/badge/License-MIT-green.svg)

## 🎯 Overview

This application replicates core Social Security Administration services for:
- **Applying for new SSNs** - Submit applications with demographic information
- **Approving/Rejecting applications** - Admin review and processing
- **Searching and verifying SSNs** - Lookup by SSN, name, or date of birth
- **Managing person records** - Update information and track changes
- **Audit trail** - Complete logging of all sensitive operations

## ✨ Features

### For Applicants
- ✅ Submit SSN applications online
- ✅ Track application status by reference number
- ✅ View issued SSN details
- ✅ Update personal information

### For Administrators
- ✅ Review pending applications
- ✅ Approve/reject applications with notes
- ✅ Generate unique SSNs automatically
- ✅ Search and verify SSN records
- ✅ View system statistics and audit logs
- ✅ Manage person records

### System Features
- ✅ Embedded H2 database (no installation required)
- ✅ Modern, sleek JavaFX interface
- ✅ Complete audit trail for compliance
- ✅ SSN format validation (XXX-XX-XXXX)
- ✅ Duplicate SSN prevention
- ✅ Privacy controls (SSN masking)

## 🏗️ Architecture

### Black Box Design Principles

This application is built with **replaceable modules**:

```
┌─────────────────┐
│   UI Layer      │ ← JavaFX (replaceable with Web UI, Mobile, CLI)
└────────┬────────┘
         │ Interface: Service methods
┌────────▼────────┐
│ Service Layer   │ ← Business logic (replaceable algorithms)
└────────┬────────┘
         │ Interface: Repository methods
┌────────▼────────┐
│Repository Layer │ ← Data access (replaceable: H2 → PostgreSQL, MongoDB)
└────────┬────────┘
         │ Interface: JDBC
┌────────▼────────┐
│   Database      │ ← H2 embedded (replaceable with any SQL database)
└─────────────────┘
```

**Each layer is a black box** - you can replace the implementation without affecting other layers.

### Design Principles Applied

- **KISS** (Keep It Simple, Stupid) - Straightforward logic, no over-engineering
- **DRY** (Don't Repeat Yourself) - Shared utilities, reusable components
- **SOLID** - Single responsibility, open/closed, interface-based design
- **Black Box** - Hidden implementation, clear interfaces, replaceable modules

## 📋 Prerequisites

- **Java 17 or higher**
- **Maven 3.6+** (for building)
- **No database installation required** (H2 is embedded)

## 🚀 Getting Started

### 1. Clone or Download the Project

```bash
cd ssn-service
```

### 2. Build the Project

```bash
mvn clean compile
```

### 3. Run the Application

```bash
mvn javafx:run
```

### 4. Build Executable JAR

```bash
mvn clean package
java -jar target/ssn-service-1.0.0.jar
```

## 📂 Project Structure

```
ssn-service/
├── src/
│   ├── main/
│   │   ├── java/com/ssa/
│   │   │   ├── model/              # Domain primitives (Person, SSN, Application)
│   │   │   ├── repository/         # Data access interfaces
│   │   │   │   └── impl/           # H2 implementations
│   │   │   ├── service/            # Business logic interfaces
│   │   │   │   └── impl/           # Service implementations
│   │   │   ├── ui/                 # JavaFX UI
│   │   │   │   ├── controller/     # UI controllers
│   │   │   │   └── view/           # UI views
│   │   │   ├── exception/          # Custom exceptions
│   │   │   ├── util/               # Utilities (DB, validation)
│   │   │   └── config/             # Configuration
│   │   └── resources/
│   │       ├── application.properties
│   │       └── css/styles.css      # Modern UI styling
│   └── test/                       # Unit and integration tests
├── data/                           # H2 database files (auto-created)
├── pom.xml                         # Maven configuration
├── README.md                       # This file
├── user_stories.md                 # Detailed requirements
├── architecture_design.md          # Architecture documentation
└── IMPLEMENTATION_STATUS.md        # Development progress
```

## 💾 Database

### H2 Embedded Database

- **Location**: `./data/ssn_db.mv.db` (auto-created on first run)
- **Connection**: `jdbc:h2:./data/ssn_db`
- **User**: `sa`
- **Password**: (empty)

### Schema

Tables:
- `persons` - Individual demographic information
- `ssns` - Social Security Numbers
- `applications` - SSN application records
- `audit_logs` - Audit trail of operations

## 🎨 User Interface

### Modern Design Features

- **Card-based layout** with shadows and depth
- **Responsive forms** with validation
- **Clean typography** (Segoe UI)
- **Color scheme**:
  - Primary: #2C3E50 (Dark Blue-Gray)
  - Secondary: #3498DB (Bright Blue)
  - Accent: #E74C3C (Red)
  - Success: #27AE60 (Green)

### Main Views

1. **Application Form** - Submit new SSN applications
2. **Search** - Look up SSNs by various criteria
3. **Admin Dashboard** - Review pending applications
4. **Statistics** - View system metrics
5. **Audit Logs** - Review system activity

## 🔒 Security & Privacy

- **SSN Masking** - Non-admin users see `***-**-1234`
- **Audit Trail** - All operations logged with user, timestamp, details
- **Input Validation** - Format and business rule validation
- **Role-Based Access** - Admin vs. Public user permissions

## 🧪 Testing

### Run Unit Tests

```bash
mvn test
```

### Run with Coverage

```bash
mvn test jacoco:report
```

Coverage reports will be in `target/site/jacoco/index.html`

## 📖 Usage Examples

### Apply for SSN

1. Open application
2. Click "New Application"
3. Fill in all required fields:
   - First Name, Middle Name (optional), Last Name
   - Date of Birth, Place of Birth
   - Mother's Maiden Name, Father's Name
   - Citizenship Status
4. Submit application
5. Note your reference number (e.g., `APP-2024-ABC123`)

### Admin: Approve Application

1. Log in as admin
2. Go to "Pending Applications"
3. Select an application
4. Review applicant details
5. Click "Approve" or "Reject"
6. If approved, SSN is auto-generated

### Search for SSN

1. Go to "Search" tab
2. Enter search criteria:
   - SSN (if known)
   - First Name + Last Name
   - Date of Birth
3. View results (SSN masked for non-admin)

## 🔧 Configuration

Edit `src/main/resources/application.properties`:

```properties
# Database
db.url=jdbc:h2:./data/ssn_db
db.user=sa
db.password=

# SSN Generation
app.ssn.area.start=001
app.ssn.area.end=899

# UI
ui.window.width=1200
ui.window.height=800
```

## 🐛 Troubleshooting

### Database locked error
- Close all other instances of the application
- Delete `./data/ssn_db.mv.db.lock`

### JavaFX runtime not found
- Ensure Java 17+ is installed
- Maven will download JavaFX automatically

### Port already in use (H2 web console)
- H2 embedded mode doesn't use ports
- If using H2 server mode, change port in properties

## 🚀 Future Enhancements

Thanks to black box design, these can be added independently:

1. **Web Interface** - Replace JavaFX with Spring Boot + React
2. **REST API** - Add API layer without changing services
3. **PostgreSQL** - Swap H2 for production database
4. **Authentication** - Add Spring Security
5. **Email Notifications** - Notify applicants of status
6. **PDF Generation** - Generate SSN cards
7. **Analytics Dashboard** - Advanced reporting

## 📚 Documentation

- [User Stories](user_stories.md) - Complete requirements and acceptance criteria
- [Architecture Design](architecture_design.md) - Detailed technical design
- [Implementation Status](IMPLEMENTATION_STATUS.md) - Development progress
- [Black Box Principles](black_box_design.md) - Design philosophy

## 👥 User Roles

### Public User
- Submit applications
- View own applications
- Search SSNs (masked)

### Administrator
- All public user permissions
- Review and process applications
- View full SSNs
- Access audit logs
- Manage system

## 📊 System Statistics

Current Implementation:
- **Domain Models**: 4 (Person, SSN, Application, AuditLog)
- **Service Interfaces**: 4 (SSN, Application, Person, Audit)
- **Repository Interfaces**: 5 (Generic + 4 specific)
- **Database Tables**: 4
- **Lines of Code**: ~5,000 (estimated when complete)

## 🤝 Contributing

1. Follow black box design principles
2. Each module must have clear interface
3. Hide implementation details
4. Write tests for new features
5. Update documentation

## 📄 License

MIT License - Feel free to use for educational purposes.

## 🙏 Acknowledgments

- Designed using **Eskil Steenberg's black box principles**
- Built with **SOLID** principles
- Follows **KISS** and **DRY** philosophies
- Inspired by real SSA systems

## 📞 Support

For issues or questions:
1. Check [IMPLEMENTATION_STATUS.md](IMPLEMENTATION_STATUS.md)
2. Review [architecture_design.md](architecture_design.md)
3. Examine [user_stories.md](user_stories.md)

---

**Built with ❤️ using Black Box Design Principles**

*"It's faster to write five lines of code today than to write one line today and then have to edit it in the future."* - Eskil Steenberg
