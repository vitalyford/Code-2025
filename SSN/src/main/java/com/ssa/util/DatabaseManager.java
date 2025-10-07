package com.ssa.util;

import com.ssa.exception.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database connection manager for H2 embedded database.
 * 
 * Black Box Implementation Detail: Manages database lifecycle.
 * Other modules only see Connection objects, not how they're created.
 */
public class DatabaseManager {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseManager.class);
    
    private static final String DB_URL = "jdbc:h2:./data/ssn_db";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";
    
    private static DatabaseManager instance;
    private Connection connection;

    private DatabaseManager() {
        // Private constructor for singleton
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
            instance.initialize();
        }
        return instance;
    }

    private void initialize() {
        try {
            // Load H2 driver
            Class.forName("org.h2.Driver");
            // Create connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            logger.info("Database connection established");
            // Initialize schema
            initializeSchema();
        } catch (ClassNotFoundException | SQLException e) {
            logger.error("Failed to initialize database", e);
            throw new RepositoryException("Database initialization failed", e);
        }
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            logger.error("Failed to get database connection", e);
            throw new RepositoryException("Failed to get database connection", e);
        }
    }

    private void initializeSchema() {
        logger.info("Initializing database schema");
        
        try (Statement stmt = connection.createStatement()) {
            // Create persons table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS persons (
                    person_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    first_name VARCHAR(100) NOT NULL,
                    middle_name VARCHAR(100),
                    last_name VARCHAR(100) NOT NULL,
                    date_of_birth DATE NOT NULL,
                    place_of_birth VARCHAR(200) NOT NULL,
                    mothers_maiden_name VARCHAR(100) NOT NULL,
                    fathers_name VARCHAR(100) NOT NULL,
                    citizenship_status VARCHAR(50) NOT NULL,
                    ssn VARCHAR(11),
                    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            // Create SSNs table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS ssns (
                    ssn VARCHAR(11) PRIMARY KEY,
                    person_id BIGINT NOT NULL,
                    issued_date TIMESTAMP NOT NULL,
                    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
                    FOREIGN KEY (person_id) REFERENCES persons(person_id)
                )
            """);

            // Create applications table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS applications (
                    application_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    reference_number VARCHAR(50) UNIQUE NOT NULL,
                    person_id BIGINT NOT NULL,
                    application_date TIMESTAMP NOT NULL,
                    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
                    review_date TIMESTAMP,
                    review_notes TEXT,
                    reviewed_by VARCHAR(100),
                    assigned_ssn VARCHAR(11),
                    FOREIGN KEY (person_id) REFERENCES persons(person_id)
                )
            """);

            // Create audit_logs table
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS audit_logs (
                    log_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    timestamp TIMESTAMP NOT NULL,
                    user_name VARCHAR(100) NOT NULL,
                    action VARCHAR(100) NOT NULL,
                    details TEXT,
                    ip_address VARCHAR(50)
                )
            """);

            // Create indexes
            stmt.execute("CREATE INDEX IF NOT EXISTS idx_person_name ON persons(last_name, first_name)");
            stmt.execute("CREATE INDEX IF NOT EXISTS idx_person_dob ON persons(date_of_birth)");
            stmt.execute("CREATE INDEX IF NOT EXISTS idx_person_ssn ON persons(ssn)");
            stmt.execute("CREATE INDEX IF NOT EXISTS idx_ssn_person ON ssns(person_id)");
            stmt.execute("CREATE INDEX IF NOT EXISTS idx_app_status ON applications(status)");
            stmt.execute("CREATE INDEX IF NOT EXISTS idx_app_refnum ON applications(reference_number)");
            stmt.execute("CREATE INDEX IF NOT EXISTS idx_audit_date ON audit_logs(timestamp)");

            logger.info("Database schema initialized successfully");
        } catch (SQLException e) {
            logger.error("Failed to initialize schema", e);
            throw new RepositoryException("Schema initialization failed", e);
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("Database connection closed");
            }
        } catch (SQLException e) {
            logger.error("Error closing database connection", e);
        }
    }
}
