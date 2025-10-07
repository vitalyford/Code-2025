# SSN Service Application - User Stories

## Overview
This application replicates core Social Security Administration (SSA) services for managing Social Security Numbers (SSNs). It follows black box design principles with clear module boundaries and replaceable components.

## Core Primitives
1. **SSN** - A 9-digit unique identifier (XXX-XX-XXXX format)
2. **Person** - Individual with demographic information
3. **Application** - Request for SSN issuance
4. **Verification** - Proof of identity and eligibility

## User Stories

### Epic 1: SSN Application & Issuance

#### Story 1.1: Apply for New SSN
**As a** first-time applicant  
**I want to** submit an application for a new Social Security Number  
**So that** I can receive a unique identifier for social security services

**Acceptance Criteria:**
- User can enter: First Name, Middle Name, Last Name, Date of Birth, Place of Birth
- User can enter: Mother's Maiden Name, Father's Name, Citizenship Status
- System validates all required fields are filled
- System validates date of birth is reasonable (not future, not too old)
- System validates SSN doesn't already exist for this person
- Application is saved with "Pending" status
- User receives confirmation with application reference number

#### Story 1.2: Approve SSN Application
**As an** SSA administrator  
**I want to** review and approve pending applications  
**So that** eligible applicants receive their SSN

**Acceptance Criteria:**
- System displays list of pending applications
- Admin can view full application details
- Admin can approve or reject application with reason
- Upon approval, system generates unique 9-digit SSN
- SSN follows format: XXX-XX-XXXX (area-group-serial)
- Generated SSN is never reused
- Application status changes to "Approved"
- Approval date is recorded

#### Story 1.3: View Issued SSN
**As an** approved applicant  
**I want to** view my issued Social Security Number  
**So that** I can use it for employment and government services

**Acceptance Criteria:**
- User can search by application reference number
- System displays SSN in XXX-XX-XXXX format
- System displays issuance date
- System displays cardholder information

### Epic 2: SSN Lookup & Verification

#### Story 2.1: Search for SSN Record
**As an** authorized user  
**I want to** search for SSN records  
**So that** I can verify SSN information

**Acceptance Criteria:**
- User can search by: SSN, Full Name, Date of Birth
- System returns matching records
- System displays: SSN (masked or full based on permission), Name, DOB, Issuance Date
- System handles no results gracefully
- System prevents unauthorized access

#### Story 2.2: Verify SSN Validity
**As an** employer or government agency  
**I want to** verify if an SSN is valid and matches a person  
**So that** I can confirm identity for employment or services

**Acceptance Criteria:**
- User enters SSN and person details
- System validates SSN format (9 digits, proper structure)
- System checks if SSN exists in database
- System verifies name and DOB match (if provided)
- System returns: Valid/Invalid status with reason
- System logs verification attempts for security

### Epic 3: SSN Management

#### Story 3.1: Update Personal Information
**As an** SSN holder  
**I want to** update my personal information  
**So that** my records remain accurate

**Acceptance Criteria:**
- User can update: Name (due to marriage, etc.), Address
- SSN itself cannot be changed
- System validates new information
- System maintains audit trail of changes
- System requires verification before changes

#### Story 3.2: Report Lost/Stolen SSN Card
**As an** SSN holder  
**I want to** report my SSN card as lost or stolen  
**So that** I can request a replacement and flag potential fraud

**Acceptance Criteria:**
- User can report card as lost/stolen
- System flags SSN for fraud monitoring
- System allows replacement card request
- System maintains history of card issuances

#### Story 3.3: View SSN Statistics
**As an** SSA administrator  
**I want to** view statistics about SSN issuance  
**So that** I can monitor system usage

**Acceptance Criteria:**
- System displays: Total SSNs issued, Applications pending/approved/rejected
- System shows issuance trends over time
- System displays geographic distribution of issuances

### Epic 4: Security & Compliance

#### Story 4.1: Audit Trail
**As an** SSA administrator  
**I want to** view audit logs of all SSN operations  
**So that** I can ensure compliance and investigate issues

**Acceptance Criteria:**
- System logs: All SSN lookups, Application submissions/approvals, Data modifications
- Logs include: Timestamp, User/Admin ID, Action type, Details
- Logs are immutable
- Logs can be filtered and searched

#### Story 4.2: Data Privacy Controls
**As a** system administrator  
**I want to** control access to SSN data  
**So that** sensitive information is protected

**Acceptance Criteria:**
- Different user roles: Public Applicant, Verifier, Administrator
- SSN masking for non-admin users (XXX-XX-1234)
- Failed login attempts are logged and limited
- Session timeout after inactivity

## Non-Functional Requirements

### Performance
- SSN generation: < 100ms
- Search queries: < 500ms
- Support 10,000+ SSN records

### Usability
- Modern, clean interface
- Intuitive navigation
- Clear error messages
- Responsive design

### Data Integrity
- No duplicate SSNs
- Referential integrity maintained
- Proper validation on all inputs
- Database transactions for consistency

### Maintainability
- Clear module boundaries (black box design)
- Each module replaceable independently
- Comprehensive error handling
- Well-documented APIs

## System Architecture (Black Box Design)

### Module 1: Domain Model
**Responsibility:** Define core primitives (SSN, Person, Application)  
**Interface:** POJOs with validation logic  
**Replaceable:** Yes - could swap for different validation rules

### Module 2: Repository Layer
**Responsibility:** Data persistence and retrieval  
**Interface:** CRUD operations on entities  
**Replaceable:** Yes - could swap H2 for PostgreSQL, MySQL, or MongoDB

### Module 3: Service Layer
**Responsibility:** Business logic (SSN generation, validation, approval)  
**Interface:** Service methods for each operation  
**Replaceable:** Yes - could change business rules without affecting other layers

### Module 4: UI Layer
**Responsibility:** User interaction and presentation  
**Interface:** Event handlers and view updates  
**Replaceable:** Yes - could swap JavaFX for web UI, mobile app, or CLI

### Module 5: Configuration
**Responsibility:** Application setup and initialization  
**Interface:** Configuration loading and dependency injection  
**Replaceable:** Yes - could swap configuration source or DI framework

## Technology Stack

- **Language:** Java 17+
- **UI:** JavaFX (modern, sleek, cross-platform)
- **Database:** H2 (embedded, no installation required)
- **Build:** Maven
- **Architecture:** Layered (Repository → Service → UI)

## Design Principles Applied

### KISS (Keep It Simple, Stupid)
- Straightforward SSN generation algorithm
- Simple database schema
- Intuitive UI workflows

### DRY (Don't Repeat Yourself)
- Shared validation utilities
- Common repository base class
- Reusable UI components

### SOLID
- **Single Responsibility:** Each class has one job
- **Open/Closed:** Extend behavior through interfaces
- **Liskov Substitution:** Interface-based design
- **Interface Segregation:** Focused interfaces
- **Dependency Inversion:** Depend on abstractions

### Black Box Design
- Hide implementation details
- Clean APIs between modules
- Each module can be replaced independently
- No cross-module implementation dependencies
