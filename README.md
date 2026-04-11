# 🚀 Phoenix API Automation Framework

[![Build Status](https://img.shields.io/badge/Build-Passing-brightgreen)](https://github.com)
[![Java 16+](https://img.shields.io/badge/Java-16%2B-orange)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.8.0%2B-red)](https://maven.apache.org/)
[![TestNG](https://img.shields.io/badge/TestNG-7.11.0-blue)](https://testng.org/)
[![License](https://img.shields.io/badge/License-Proprietary-lightgrey)](LICENSE)
[![Version](https://img.shields.io/badge/Version-0.0.1--SNAPSHOT-yellowgreen)](https://github.com)

> A robust, enterprise-grade REST API automation testing framework built with **Java**, **Rest Assured**, and **TestNG**. This framework is designed for comprehensive API testing with data-driven capabilities, advanced reporting, and database validation.

---

## 📋 Table of Contents

- [Quick Start](#-quick-start)
- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Configuration](#configuration)
- [Running Tests](#running-tests)
- [Test Data Management](#test-data-management)
- [Reporting](#reporting)
- [Utilities & Helper Classes](#utilities--helper-classes)
- [Best Practices](#best-practices)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)

---

## ⚡ Quick Start

Get up and running in 5 minutes:

```bash
# 1. Clone the repository
git clone <repository-url>
cd PhoenixApiAutomationFramework

# 2. Install dependencies
mvn clean install

# 3. Configure your environment
# Edit src/test/resources/config/config.properties with your API endpoint

# 4. Run tests
mvn clean test

# 5. View Allure report
mvn allure:report allure:serve
```

> **First time setup?** See [Installation & Setup](#installation--setup) for detailed instructions.

---

## 📖 Overview

The **Phoenix API Automation Framework** is a comprehensive testing solution for validating REST APIs with a focus on:

- **API Testing**: Complete REST endpoint validation using Rest Assured
- **Test Automation**: TestNG-based test orchestration and execution
- **Data-Driven Testing**: Support for multiple data sources (Excel, CSV, Database)
- **Advanced Reporting**: Allure reporting for detailed test execution insights
- **Database Operations**: Direct database validation using MySQL connections with connection pooling
- **Test Data Generation**: Automated fake data generation using JavaFaker
- **Response Validation**: JSON schema validation and assertion framework
- **Reusability**: Service-based architecture for maintainable and scalable tests

---

## ✨ Features

### 🎯 Core Testing Capabilities

- ✅ REST API endpoint automation for GET, POST, PUT, DELETE, PATCH operations
- ✅ Request/Response validation with JSON schema support
- ✅ Data-driven testing with Excel, CSV, and database sources
- ✅ Dynamic request body generation with faker data
- ✅ Authentication token management and reuse
- ✅ Test retry mechanism for flaky tests
- ✅ Comprehensive test listeners for logging and reporting

### 🏗️ Framework Features

- 🔐 Secure configuration management with property files
- 🗄️ Database connectivity with HikariCP connection pooling
- 📊 Multi-format data input support (Excel, CSV, JSON, Database)
- 🔄 Reusable API service classes for common operations
- 🏷️ Filter-based request/response manipulation
- 📝 Detailed logging with Log4j2
- 🎯 Test categorization and tagging capabilities
- 🔙 Retry analyzer for handling intermittent failures

---

## 🛠️ Technology Stack

| Technology | Version | Purpose |
|-----------|---------|---------|
| **Java** | 16 | Core programming language |
| **Rest Assured** | 5.5.6 | REST API testing library |
| **TestNG** | 7.11.0 | Test framework and orchestration |
| **Jackson** | 2.20.1 | JSON data binding and processing |
| **Maven** | Latest | Build and dependency management |
| **Allure** | 2.12.0 | Advanced test reporting |
| **MySQL** | Connector 9.5.0 | Database connectivity |
| **HikariCP** | 7.0.2 | Connection pooling |
| **Apache POI** | 5.4.1 | Excel file handling |
| **OpenCSV** | 5.12.0 | CSV file handling |
| **JavaFaker** | 1.0.2 | Test data generation |
| **Lombok** | 1.18.42 | Code generation and reduction |
| **Log4j2** | Latest | Logging framework |

---

## 📁 Project Structure

```
PhoenixApiAutomationFramework/
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   ├── ApiTests/                 # Test classes for API endpoints
│   │   │   ├── apiservices/              # Service classes (AuthService, etc.)
│   │   │   ├── ApiUtils/                 # Utility classes
│   │   │   │   ├── ConfigManager.java
│   │   │   │   ├── SpecUtils.java
│   │   │   │   ├── JsonReaderUtil.java
│   │   │   │   ├── ExcelReaderUtil.java
│   │   │   │   ├── CSVReaderUtils.java
│   │   │   │   ├── AuthTokenProvider.java
│   │   │   │   ├── FakerDataGenerator.java
│   │   │   │   ├── DateTimeUtil.java
│   │   │   │   ├── EnvUtil.java
│   │   │   │   └── VaultDBConfig.java
│   │   │   ├── requestmodel/             # Request POJO classes
│   │   │   ├── reponsemodel/             # Response POJO classes
│   │   │   ├── DataProvidersApiBeans/    # Data provider POJO classes
│   │   │   ├── dataproviders/            # Data provider classes for tests
│   │   │   ├── database/                 # Database connectivity and queries
│   │   │   │   └── DatabaseManager.java
│   │   │   ├── databasemodel/            # Database model classes
│   │   │   ├── apifilters/               # Request/Response filters
│   │   │   ├── listeners/                # Test listeners
│   │   │   ├── allure_reporting/         # Allure report attachments
│   │   │   ├── retry/                    # Retry analyzer
│   │   │   └── Constants/                # Constants and enum classes
│   │   └── resources/
│   │       ├── config/
│   │       │   └── config.properties     # Configuration properties
│   │       ├── test-data/                # Test data files (Excel, CSV, JSON)
│   │       ├── response-schema/          # JSON schema files for validation
│   │       └── log4j2.xml                # Logging configuration
│   └── main/
│       └── java/                         # Main source (if needed)
├── target/                               # Build artifacts
│   ├── allure-results/                   # Allure report data
│   ├── classes/                          # Compiled classes
│   └── test-classes/                     # Compiled test classes
├── test-output/                          # TestNG report output
├── logs/                                 # Application logs
├── pom.xml                               # Maven build configuration
├── testng.xml                            # TestNG suite configuration
├── testng-datadriven.xml                 # Data-driven test suite
└── README.md                             # This file
```

---

## 📋 Prerequisites

Before setting up the framework, ensure you have the following installed:

| Requirement | Minimum Version | Installation |
|-------------|-----------------|--------------|
| **Java Development Kit (JDK)** | 16+ | [Download](https://www.oracle.com/java/technologies/downloads/) |
| **Maven** | 3.8.0+ | [Download](https://maven.apache.org/) |
| **MySQL Database** | 5.7+ | [Download](https://www.mysql.com/) |
| **Git** | 2.30+ | [Download](https://git-scm.com/) |
| **IDE (Optional)** | Latest | [IntelliJ IDEA](https://www.jetbrains.com/idea/) • [VS Code](https://code.visualstudio.com/) |

### ✅ Verify Installation

```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Check MySQL version
mysql --version

# Check Git version
git --version
```

Expected output example:
```
java version "16.0.1" or higher
Apache Maven 3.8.0 or higher
mysql Ver 8.0.x
git version 2.30.x
```

---

## 🚀 Installation & Setup

### Step 1: Clone the Repository

```bash
# Using HTTPS
git clone https://github.com/your-org/PhoenixApiAutomationFramework.git

# Using SSH
git clone git@github.com:your-org/PhoenixApiAutomationFramework.git

# Navigate to project directory
cd PhoenixApiAutomationFramework
```

### Step 2: Configure Maven

Ensure Java compiler source and target are set correctly in `pom.xml`:

```xml
<properties>
    <maven.compiler.source>16</maven.compiler.source>
    <maven.compiler.target>16</maven.compiler.target>
</properties>
```

### Step 3: Install Dependencies

```bash
mvn clean install
```

This command will:
- ✅ Clean previous builds
- ✅ Download all required dependencies
- ✅ Compile source and test code
- ✅ Verify setup

### Step 4: Import Project in IDE

**IntelliJ IDEA:**
1. Open IntelliJ IDEA
2. File → Open → Select the project directory
3. Configure JDK: File → Project Structure → Project → Set JDK to Java 16
4. Wait for Maven to sync dependencies

**VS Code:**
1. Install Extension Pack for Java
2. Open the project folder
3. Wait for dependency resolution
4. Install Lombok processor annotation support

**Eclipse:**
1. File → Import → Maven → Existing Maven Projects
2. Select the project directory
3. Finish and wait for build

### Step 5: Verify Setup

```bash
# Test a single test class to verify setup
mvn clean test -Dtest=LoginApiTest

# If successful, you should see BUILD SUCCESS message
```

---

## ⚙️ Configuration

### Configuration File Location

```
src/test/resources/config/config.properties
```

### Configuration Properties

Edit `config.properties` to configure your environment:

```properties
# API Configuration
BASE_URI=http://64.227.160.186:9000/v1
API_KEY=your_api_key_here
API_SECRET=your_api_secret_here

# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/phoenix_db
DB_USERNAME=root
DB_PASSWORD=password
DB_DRIVER=com.mysql.cj.jdbc.Driver
DB_POOL_SIZE=5
DB_CONNECTION_TIMEOUT=10000

# Timeouts (in milliseconds)
IMPLICIT_WAIT=10000
EXPLICIT_WAIT=15000
CONNECTION_TIMEOUT=5000

# Allure Report Configuration
ALLURE_RESULTS_DIR=target/allure-results
ATTACH_SCREENSHOTS=true
LOG_LEVEL=INFO

# Environment Configuration
ENVIRONMENT=dev
RETRY_COUNT=2
PARALLEL_THREADS=4
```

### Using ConfigManager

```java
import ApiUtils.ConfigManager;

public class TestExample {
    private static ConfigManager config = new ConfigManager();
    
    @Before
    public void setup() {
        String baseUri = config.getProperty("BASE_URI");
        int timeout = config.getIntProperty("IMPLICIT_WAIT");
        String dbUrl = config.getProperty("DB_URL");
    }
}
```

### Log4j2 Configuration

Configure logging levels and output in `src/test/resources/log4j2.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration packages="org.apache.logging.log4j.core">
    <Appenders>
        <!-- Console appender for console output -->
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n" />
        </Console>
        
        <!-- File appender for file output -->
        <File name="File" fileName="logs/application.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} [%t] %-5level %logger{36} - %msg%n" />
        </File>
    </Appenders>
    
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console" />
            <AppenderRef ref="File" />
        </Root>
    </Loggers>
</Configuration>
```

### Environment-Specific Configuration

Create separate property files for different environments:

```
src/test/resources/config/
├── config.properties          # Default
├── config.dev.properties      # Development
├── config.staging.properties  # Staging
└── config.prod.properties     # Production
```

Load environment-specific config:
```bash
# Run with specific environment
mvn clean test -Denv=staging
```

### Database Configuration Example

```properties
# MySQL Configuration
DB_URL=jdbc:mysql://localhost:3306/phoenix_db?useSSL=false&serverTimezone=UTC
DB_USERNAME=admin
DB_PASSWORD=secure_password
DB_DRIVER=com.mysql.cj.jdbc.Driver

# Connection Pool Settings
DB_POOL_SIZE=10
DB_MINIMUM_IDLE=5
DB_CONNECTION_TIMEOUT=30000
DB_IDLE_TIMEOUT=600000
DB_MAX_LIFETIME=1800000
```

---

## 🧪 Running Tests

### Basic Test Execution

```bash
# Run all tests
mvn clean test

# Run with verbose output
mvn clean test -X
```

### Test Suite Execution

```bash
# Run specific TestNG suite
mvn clean test -DsuiteXmlFile=testng.xml

# Run data-driven test suite
mvn clean test -DsuiteXmlFile=testng-datadriven.xml
```

### Targeted Test Execution

```bash
# Run specific test class
mvn clean test -Dtest=LoginApiTest

# Run specific test method
mvn clean test -Dtest=LoginApiTest#testValidCredentials

# Run multiple test classes
mvn clean test -Dtest=LoginApiTest,UserDetailsApiTest

# Run tests matching a pattern
mvn clean test -Dtest=*ApiTest
```

### Parallel Test Execution

```bash
# Run tests in parallel (methods level)
mvn clean test -Dtest.parallel=methods -Dtest.threadCount=4

# Run tests in parallel (classes level)
mvn clean test -Dtest.parallel=classes -Dtest.threadCount=4
```

### Advanced Execution Options

```bash
# Skip tests during build
mvn clean install -DskipTests

# Run only failing tests from last run
mvn clean test -Dtest=@target/failsafe-reports/junitreport.xml

# Run with custom log level
mvn clean test -Dlog.level=DEBUG

# Run with JVM options
mvn clean test -Xmx1024m -Xms512m
```

### Generate Reports

```bash
# Run tests and generate Allure report
mvn clean test allure:report

# Serve Allure report (opens in browser)
mvn allure:serve

# Generate TestNG reports only
mvn clean test surefire-report:report
```

### Example: Complete Test Execution Workflow

```bash
# Clean build, run all tests, generate reports
mvn clean install -DskipTests && \
mvn test -DsuiteXmlFile=testng.xml && \
mvn allure:report allure:serve
```

---

## 📊 Test Data Management

### Excel Data Input

```java
ExcelReaderUtil excelReader = new ExcelReaderUtil("path/to/testdata.xlsx");
List<Map<String, String>> testData = excelReader.readSheet("Sheet1");
```

### CSV Data Input

```java
CSVReaderUtils csvReader = new CSVReaderUtils("path/to/testdata.csv");
List<Map<String, String>> testData = csvReader.readAllData();
```

### Generate Fake Data

```java
FakerDataGenerator faker = new FakerDataGenerator();
String firstName = faker.generateFirstName();
String email = faker.generateEmail();
String phoneNumber = faker.generatePhoneNumber();
```

### Database Query Execution

```java
DatabaseManager dbManager = new DatabaseManager();
List<Map<String, Object>> results = dbManager.executeQuery("SELECT * FROM users WHERE id = ?", userId);
dbManager.closeConnection();
```

---

## 📈 Reporting

### Allure Report Generation

The framework integrates with **Allure** for comprehensive, visual test reporting with trends, history, and detailed analytics.

#### Generate & View Allure Report

```bash
# Run tests (Allure results are generated automatically)
mvn clean test

# Generate HTML report from results
mvn allure:report

# Serve report on local server (opens http://localhost:4040)
mvn allure:serve

# Or use Allure CLI
allure serve target/allure-results
```

#### Allure Report Features
- 📊 Visual test statistics and trends
- 🎯 Test categorization by severity
- 📝 Detailed step-by-step execution logs
- 📎 Screenshots and file attachments
- ⏱️ Execution timeline
- 🔗 Test-to-requirement traceability

#### Add Allure Annotations to Tests

```java
import io.qameta.allure.*;

@Test
@DisplayName("User Login with Valid Credentials")
@Description("Verify that user can login with valid email and password")
@Severity(SeverityLevel.CRITICAL)
@Feature("Authentication")
@Story("User Login")
@Tag("regression")
public void testUserLoginSuccess() {
    loginStep();
    verifyLoginSuccess();
}

@Step("Perform login with credentials")
public void loginStep() {
    // Step implementation
}

@Attachment(value = "Response", type = "application/json")
private String attachResponsePayload(String response) {
    return response;
}

@Attachment(value = "Screenshot", type = "image/png")
public byte[] captureScreenshot() {
    // Capture screenshot logic
    return screenshotBytes;
}
```

#### Allure Report Structure

```
target/allure-report/
├── index.html              # Main report page
├── data/
│   ├── test-cases/        # Detailed test case reports
│   └── test-runs/         # Test execution history
└── plugins/               # Allure plugins
```

### TestNG Report

TestNG generates HTML reports in the `test-output/` directory:

```
test-output/
├── index.html             # Main report
├── testng-results.xml     # XML results (for CI/CD)
├── emailable-report.html  # Email-friendly report
└── Default suite/         # Test results by suite
```

### View Reports

```bash
# Open TestNG report in browser
open test-output/index.html

# Open Allure report in browser
open target/allure-report/index.html
```

### CI/CD Integration

#### GitHub Actions Example

Create `.github/workflows/test.yml`:

```yaml
name: API Automation Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    strategy:
      matrix:
        java-version: ['16']
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK
      uses: actions/setup-java@v3
      with:
        java-version: '16'
    
    - name: Run tests
      run: mvn clean test -DsuiteXmlFile=testng.xml
    
    - name: Generate Allure Report
      if: always()
      run: mvn allure:report
    
    - name: Upload Allure Results
      if: always()
      uses: actions/upload-artifact@v3
      with:
        name: allure-results
        path: target/allure-results
```

#### Jenkins Pipeline Example

Create `Jenkinsfile`:

```groovy
pipeline {
    agent any
    
    environment {
        MAVEN_HOME = '/usr/share/maven'
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/your-org/PhoenixApiAutomationFramework.git'
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn clean test -DsuiteXmlFile=testng.xml'
            }
        }
        
        stage('Report') {
            steps {
                sh 'mvn allure:report'
            }
        }
    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
            publishHTML([
                reportDir: 'target/allure-report',
                reportFiles: 'index.html',
                reportName: 'Allure Report'
            ])
        }
    }
}
```

---

## 🛠️ Utilities & Helper Classes

### SpecUtils
Provides specification setup for API requests:
```java
SpecUtils.setupSpec()  // Returns RequestSpecification
```

### JsonReaderUtil
Read and parse JSON files:
```java
JsonReaderUtil.readJson("path/to/file.json")
```

### AuthTokenProvider
Manage authentication tokens:
```java
String token = AuthTokenProvider.getToken();
```

### DateTimeUtil
Handle date and time operations:
```java
String formattedDate = DateTimeUtil.getCurrentDate("yyyy-MM-dd");
```

### ConfigManager
Centralized configuration management:
```java
String value = ConfigManager.getProperty("KEY");
```

### CreateJobBeanMapper
Map objects to JSON/POJOs:
```java
JobRequest request = CreateJobBeanMapper.mapToJobRequest(data);
```

---

## 🏛️ Architecture & Design Patterns

### Framework Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                      Test Layer (ApiTests/)                 │
│                                                              │
│  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐         │
│  │ LoginApiTest│  │ UserDetailsI│  │ SearchJobI  │         │
│  └──────┬──────┘  └──────┬──────┘  └──────┬──────┘         │
└─────────┼─────────────────┼─────────────────┼────────────────┘
          │                 │                 │
┌─────────▼─────────────────▼─────────────────▼────────────────┐
│              Service Layer (apiservices/)                    │
│                                                              │
│  ┌──────────────────┐      ┌──────────────────┐             │
│  │  AuthService     │      │  JobService      │             │
│  ├──────────────────┤      ├──────────────────┤             │
│  │ + login()        │      │ + searchJobs()   │             │
│  │ + getToken()     │      │ + createJob()    │             │
│  └──────────────────┘      └──────────────────┘             │
└─────────┬──────────────────────┬───────────────────────────┬─┘
          │                      │                           │
┌─────────▼──────────────────────▼───────────────────────────▼─┐
│              Utilities Layer (ApiUtils/)                    │
│                                                              │
│  ┌────────────────┐  ┌─────────────────┐  ┌────────────┐   │
│  │ SpecUtils      │  │ AuthTokenPrvdr  │  │ConfigMgr   │   │
│  │ JsonReaderUtil │  │ FakerDataGen    │  │DateTimeUtil│   │
│  └────────────────┘  └─────────────────┘  └────────────┘   │
└─────────┬──────────────────────┬───────────────────────────┬─┘
          │                      │                           │
┌─────────▼──────────────────────▼───────────────────────────▼─┐
│          Data & Infrastructure Layer                        │
│                                                              │
│  ┌───────────────┐  ┌──────────────┐  ┌──────────────────┐ │
│  │ Database      │  │ Data Models  │  │ Configuration    │ │
│  │ Manager       │  │ (POJO/Beans) │  │ Files & Properties
│  └───────────────┘  └──────────────┘  └──────────────────┘ │
└──────────────────────────────────────────────────────────────┘
```

### Design Patterns Used

| Pattern | Location | Purpose |
|---------|----------|---------|
| **Service Layer** | `apiservices/` | Encapsulate API business logic |
| **Data Provider** | `dataproviders/` | Parameterize tests with multiple datasets |
| **Singleton** | `ConfigManager` | Single configuration instance |
| **Builder** | `RequestSpecification` | Fluent API request building |
| **Factory** | `DatabaseManager` | Create database connections |
| **Listener** | `listeners/` | Hook into test lifecycle |
| **Retry** | `retry/` | Retry failed tests automatically |

### Key Components

```
┌─────────────────────────────────────────┐
│     Request/Response Models             │
│  (requestmodel/ & reponsemodel/)       │
│  - POJO/Bean classes                    │
│  - Jackson @JsonProperty annotations    │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│      API Service Classes                │
│     (apiservices/)                      │
│  - Reusable API operations              │
│  - Request/Response handling            │
│  - Error management                     │
└──────────────┬──────────────────────────┘
               │
               ▼
┌─────────────────────────────────────────┐
│      Test Classes                       │
│     (ApiTests/)                         │
│  - Test cases using services            │
│  - TestNG annotations                   │
│  - Assertions & validations             │
└─────────────────────────────────────────┘
```

---

## 📝 Best Practices

### 1. Service-Based Architecture ✅

Create dedicated service classes for each API endpoint:

```java
// ✅ GOOD: Service encapsulates all login operations
public class AuthService {
    
    public Response login(String email, String password) {
        return given()
            .spec(RequestSpecUtils.getDefaultSpec())
            .body(new LoginRequest(email, password))
            .when()
            .post("/auth/login")
            .then()
            .statusCode(200)
            .extract()
            .response();
    }
    
    public String getAuthToken(String email, String password) {
        Response response = login(email, password);
        return response.jsonPath().getString("data.token");
    }
}

// ❌ AVOID: Mixing API logic with test logic
@Test
public void testLogin() {
    // Don't put API calls directly in test
}
```

### 2. Data-Driven Testing 📊

Separate test data from test logic:

```java
// ✅ GOOD: Use data providers
@DataProvider(name = "loginCredentials")
public Object[][] getLoginData() {
    return new Object[][] {
        { "valid@email.com", "password123" },
        { "another@email.com", "SecurePass99" }
    };
}

@Test(dataProvider = "loginCredentials")
public void testLoginWithMultipleCredentials(String email, String password) {
    // Test implementation
}

// ✅ GOOD: Load from Excel/CSV
@Test
public void testUserCreationFromExcel() {
    ExcelReaderUtil excel = new ExcelReaderUtil("testdata.xlsx");
    List<Map<String, String>> data = excel.readSheet("Users");
    
    for (Map<String, String> userData : data) {
        String name = userData.get("Name");
        String email = userData.get("Email");
        // Test logic
    }
}
```

### 3. Assertion Strategies 🎯

Use appropriate assertion methods:

```java
// ✅ GOOD: Clear and specific assertions
@Test
public void testLoginResponse() {
    Response response = authService.login("user@email.com", "password");
    
    // Assert status code
    assertThat(response.statusCode()).isEqualTo(200);
    
    // Assert specific fields
    String token = response.jsonPath().getString("data.token");
    assertThat(token).isNotEmpty();
    
    // JSON schema validation
    response.then()
        .assertThat()
        .body(RestAssured.matchesJsonSchemaInClasspath("login-response-schema.json"));
}

// ❌ AVOID: Generic or weak assertions
response.then().statusCode(200); // No assertion object
```

### 4. Logging & Debugging 📝

Log meaningful information at appropriate levels:

```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthService {
    private static final Logger logger = LogManager.getLogger(AuthService.class);
    
    public Response login(String email, String password) {
        logger.info("Attempting login with email: {}", email);
        
        try {
            Response response = given()
                .spec(RequestSpecUtils.getDefaultSpec())
                .body(new LoginRequest(email, password))
                .when()
                .post("/auth/login");
                
            logger.debug("Response Status: {}", response.statusCode());
            logger.trace("Response Body: {}", response.getBody().asString());
            
            return response;
        } catch (Exception e) {
            logger.error("Login failed for email: {}", email, e);
            throw new RuntimeException("Login operation failed", e);
        }
    }
}
```

### 5. Code Organization 📂

Maintain clear separation of concerns:

```
✅ GOOD: Clear organization
ApiTests/
├── AuthTests.java
├── UserTests.java
└── JobTests.java

apiservices/
├── AuthService.java
├── UserService.java
└── JobService.java

requestmodel/
├── LoginRequest.java
├── UserCreateRequest.java
└── JobSearchRequest.java

❌ AVOID: Mixed concerns
Tests.java  // All tests in one file
Utils.java  // All utilities in one file
```

### 6. Error Handling 🛡️

Implement proper exception handling:

```java
// ✅ GOOD: Specific exception handling
public Response loginWithErrorHandling(String email, String password) {
    try {
        return authService.login(email, password);
    } catch (IllegalArgumentException e) {
        logger.error("Invalid input: {}", e.getMessage());
        throw new TestException("Invalid login credentials", e);
    } catch (ConnectionException e) {
        logger.error("Connection failed: {}", e.getMessage());
        throw new TestException("API server not responding", e);
    } catch (Exception e) {
        logger.error("Unexpected error: {}", e.getMessage());
        throw new TestException("Unexpected error during login", e);
    }
}

// Use retry analyzer for flaky tests
@Test(retryAnalyzer = RetryAnalyzer.class)
public void testUnstableApi() {
    // This test will retry up to 3 times if it fails
}
```

### 7. Configuration Management 🔧

Avoid hardcoded values:

```java
// ✅ GOOD: Use configuration
private static final String BASE_URI = ConfigManager.getProperty("BASE_URI");
private static final int TIMEOUT = ConfigManager.getIntProperty("IMPLICIT_WAIT");

@Before
public void setup() {
    given()
        .baseUri(BASE_URI)
        .timeout(Duration.ofMillis(TIMEOUT))
        .when();
}

// ❌ AVOID: Hardcoded values
given()
    .baseUri("http://localhost:8080")
    .timeout(Duration.ofSeconds(10))
    .when();
```

### 8. Test Independence 🔄

Ensure tests are independent and idempotent:

```java
// ✅ GOOD: Test is independent
@Test
public void testUserCreation() {
    // Generate unique data for each run
    String uniqueEmail = FakerDataGenerator.generateUniqueEmail();
    
    Response response = userService.createUser(uniqueEmail);
    assertThat(response.statusCode()).isEqualTo(201);
    
    // Cleanup: Delete created user
    long userId = response.jsonPath().getLong("data.id");
    userService.deleteUser(userId);
}

// ❌ AVOID: Tests depending on execution order
@Test(dependsOnMethods = "testUserCreation")  // Brittle dependency
public void testUserUpdate() {
    // This test only works if testUserCreation runs first
}
```

---

## 🔍 Troubleshooting

### Common Issues & Solutions

#### Issue: Maven Build Fails
**Solution:**
```bash
# Clear Maven cache and rebuild
mvn clean install -U

# Check Java version compatibility
java -version  # Should be 16 or higher
```

#### Issue: Tests Timeout
**Solution:**
- Increase timeout values in `config.properties`
- Check API server availability
- Verify network connectivity

#### Issue: Database Connection Error
**Solution:**
```properties
# Verify database configuration
DB_URL=jdbc:mysql://hostname:3306/database_name
DB_USERNAME=root
DB_PASSWORD=correct_password
```

#### Issue: JSON Schema Validation Fails
**Solution:**
- Verify schema file location in `response-schema/` directory
- Ensure schema matches actual API response structure
- Check schema file syntax

#### Issue: Allure Report Not Generating
**Solution:**
```bash
# Ensure Allure is installed
allure --version

# Run Maven Allure commands
mvn clean test allure:report allure:serve
```

### 🆘 Still Need Help?

- 📖 Check the [Project Wiki](https://github.com/your-org/PhoenixApiAutomationFramework/wiki)
- 💬 Ask in [GitHub Discussions](https://github.com/your-org/PhoenixApiAutomationFramework/discussions)
- 🐛 [Report a Bug](https://github.com/your-org/PhoenixApiAutomationFramework/issues/new?template=bug_report.md)
- 💡 [Request a Feature](https://github.com/your-org/PhoenixApiAutomationFramework/issues/new?template=feature_request.md)

---

## 🤝 Contributing

We'd love your contributions! Here's how you can help:

### Getting Started

1. **Fork the repository**
2. **Create a feature branch** (`git checkout -b feature/amazing-feature`)
3. **Make your changes**
4. **Commit with clear messages** (`git commit -m 'Add amazing feature'`)
5. **Push to your fork** (`git push origin feature/amazing-feature`)
6. **Open a Pull Request**

### Guidelines

#### 1. **Code Quality** 
   - Follow Java naming conventions (PascalCase for classes, camelCase for methods)
   - Write clean, readable code following SOLID principles
   - Maintain DRY principle (Don't Repeat Yourself)
   - Maximum line length: 120 characters

#### 2. **Testing**
   - Write meaningful test cases with descriptive names
   - Include positive and negative test scenarios
   - Ensure tests are independent and idempotent
   - Aim for >80% code coverage

#### 3. **Documentation**
   - Update README for new features
   - Add Javadoc comments to public classes and methods
   - Include usage examples for new utilities
   - Keep CHANGELOG.md updated

#### 4. **Version Control**
   - Use descriptive commit messages (Reference issue numbers when applicable)
   - Create feature branches for new functionality
   - Keep commits atomic and logically separated
   - Submit pull requests with clear descriptions

### Pull Request Process

1. Update the README.md with details of changes if applicable
2. Update version numbers following [Semantic Versioning](https://semver.org/)
3. Link related issues in your PR description
4. Ensure all tests pass: `mvn clean test`
5. Request review from maintainers

### Code Review Checklist

- [ ] Code follows project conventions
- [ ] All tests pass locally
- [ ] New tests added for new features
- [ ] Documentation updated
- [ ] No hardcoded values or secrets
- [ ] Backward compatibility maintained

---

## 📞 Support & Contact

<div align="center">

| Resource | Link |
|----------|------|
| 📧 **Email** | [contact@example.com](mailto:contact@example.com) |
| 🐛 **Report Issues** | [GitHub Issues](https://github.com/your-org/PhoenixApiAutomationFramework/issues) |
| 💬 **Discussions** | [GitHub Discussions](https://github.com/your-org/PhoenixApiAutomationFramework/discussions) |
| 📚 **Wiki** | [Project Wiki](https://github.com/your-org/PhoenixApiAutomationFramework/wiki) |

</div>

---

## 📄 License

This project is proprietary and confidential. All rights reserved.

---

## 🔄 Version History

| Version | Date | Changes |
|---------|------|---------|
| 0.0.1 | 2026-04-11 | Initial framework setup and documentation |

---

## 📚 Additional Resources

- **Rest Assured Documentation**: [rest-assured.io](https://rest-assured.io/)
- **TestNG Documentation**: [testng.org](https://testng.org/)
- **Allure Documentation**: [docs.qameta.io/allure](https://docs.qameta.io/allure/)
- **Maven Documentation**: [maven.apache.org](https://maven.apache.org/)
- **Java Documentation**: [docs.oracle.com/javase](https://docs.oracle.com/javase/)

---

<div align="center">

### Made with ❤️ by Phoenix Team

[![GitHub Stars](https://img.shields.io/github/stars/your-org/PhoenixApiAutomationFramework?style=social)](https://github.com/your-org/PhoenixApiAutomationFramework)
[![GitHub Forks](https://img.shields.io/github/forks/your-org/PhoenixApiAutomationFramework?style=social)](https://github.com/your-org/PhoenixApiAutomationFramework)
[![GitHub Issues](https://img.shields.io/github/issues/your-org/PhoenixApiAutomationFramework?style=social)](https://github.com/your-org/PhoenixApiAutomationFramework/issues)

**Last Updated:** April 11, 2026  
**Framework Version:** 0.0.1-SNAPSHOT

</div>
