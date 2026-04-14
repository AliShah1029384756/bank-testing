# 🏦 Bank Testing – Advanced JUnit 5 Automation Framework

> **CS – Software Testing | Assignment #03 | Spring 2026**  
> **Student:** Syed Muhammad Ali Naqvi (Shah) | **Section:** F | **Instructor:** Aliza Saeed  
> **University:** National University of Computer and Emerging Sciences – Chiniot-Faisalabad Campus

---

## 📁 Project Structure

```
bank-testing/
├── src/
│   ├── main/java/com/bank/
│   │   ├── BankAccount.java          # Deposit, withdraw, balance management
│   │   ├── Customer.java             # Customer data & validation
│   │   ├── Transaction.java          # Financial transaction records
│   │   ├── LoanCalculator.java       # Monthly payment & interest calculation
│   │   ├── AccountValidator.java     # Email, ID, amount, age validation
│   │   └── InterestCalculator.java   # Simple & compound interest
│   └── test/java/com/bank/
│       ├── BankAccountTest.java      # 10 tests
│       ├── CustomerTest.java         # 8 tests
│       ├── TransactionTest.java      # 8 tests
│       ├── LoanCalculatorTest.java   # 8 tests
│       ├── AccountValidatorTest.java # 10 tests
│       ├── InterestCalculatorTest.java # 8 tests
│       ├── DataDrivenTest.java       # 40 parameterized tests (CSV + Excel)
│       ├── ExcelDataGenerator.java   # Generates test_data.xlsx
│       └── BankTestSuite.java        # Complete test suite
├── src/test/resources/
│   ├── test_data.csv                 # 20 CSV test records
│   └── test_data.xlsx                # 20 Excel test records (4 sheets)
├── target/site/jacoco/               # JaCoCo coverage report
└── pom.xml                           # Maven dependencies & plugins
```

---

## 🛠️ Tools & Technologies

| Tool | Version | Purpose |
|------|---------|---------|
| Java (OpenJDK) | 17.0.18 | Primary language |
| Apache Maven | 3.9.14 | Build & dependency management |
| JUnit 5 (Jupiter) | 5.10.0 | Unit testing framework |
| Apache POI | 5.2.3 | Excel file read/write |
| OpenCSV | 5.7.1 | CSV file parsing |
| JaCoCo | 0.8.10 | Code coverage reporting |
| Maven Surefire | 3.1.2 | Parallel test execution |
| IntelliJ IDEA | 2026.1 | IDE |

---

## ✅ Task Completion

| Task | Description | Marks | Status |
|------|-------------|-------|--------|
| Task 1 | Project Setup & Environment | 10 | ✅ Done |
| Task 2 | Test Architecture & Implementation | 30 | ✅ Done |
| Task 3 | Data-Driven Testing (CSV + Excel) | 15 | ✅ Done |
| Task 4 | Parallel Execution & Test Suites | 15 | ✅ Done |
| Task 5 | JaCoCo Code Coverage | +10 | ✅ Done |

---

## 🧪 Task 2 – Test Architecture

### AAA Pattern
All tests follow **Arrange → Act → Assert** pattern.

### Naming Convention
```
should_<expectedBehavior>_when_<condition>
```
Example: `should_ThrowException_when_InsufficientFunds()`

### Test Types Per Class
- ✅ Positive tests
- ✅ Negative tests  
- ✅ Boundary tests
- ✅ Exception handling tests

### Test Summary

| Class | Tests | Tags |
|-------|-------|------|
| BankAccountTest | 10 | @fast, @slow |
| CustomerTest | 8 | @fast, @slow |
| TransactionTest | 8 | @fast, @slow |
| LoanCalculatorTest | 8 | @fast, @slow |
| AccountValidatorTest | 10 | @fast |
| InterestCalculatorTest | 8 | @fast, @slow |
| DataDrivenTest | 40 | @fast, @slow, @integration |
| **TOTAL** | **92** | **0 Failures** |

---

## 📂 Task 3 – Data-Driven Testing

### CSV Testing
- File: `src/test/resources/test_data.csv`
- 20 records covering valid, invalid, boundary, and zero cases
- Library: **OpenCSV**

### Excel Testing
- File: `src/test/resources/test_data.xlsx`
- Library: **Apache POI**
- 4 structured sheets:

| Sheet | Records | Description |
|-------|---------|-------------|
| Valid | 5 | Correct inputs → positive payments |
| Invalid | 5 | Wrong inputs → IllegalArgumentException |
| Edge | 5 | Boundary values |
| Stress | 5 | Large/complex inputs |

---

## ⚡ Task 4 – Parallel Execution & Suites

### Parallel Execution (pom.xml)
```xml
<configurationParameters>
    junit.jupiter.execution.parallel.enabled=true
    junit.jupiter.execution.parallel.mode.default=concurrent
</configurationParameters>
```

### Test Tags
```java
@Tag("fast")        // Quick unit tests
@Tag("slow")        // Complex calculation tests
@Tag("integration") // Data-driven stress tests
```

### Test Suite
```java
@Suite
@SelectClasses({ BankAccountTest.class, CustomerTest.class, ... })
public class BankTestSuite { }
```

### Test Ordering
```java
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(1) // Applied on each test method
```

---

## 📈 Task 5 – JaCoCo Coverage Report

| Package | Line Coverage | Branch Coverage |
|---------|--------------|-----------------|
| com.bank | **96%** | **87%** |
| **Total** | **91%** | **84%** |

> ✅ Target was **70%** — Achieved **91%**

Full report available at: `target/site/jacoco/index.html`

---

## 🚀 How to Run

### Prerequisites
- Java JDK 17+
- Apache Maven 3.9+

### Clone & Run
```bash
git clone https://github.com/AliShah1029384756/bank-testing.git
cd bank-testing
```

### Generate Excel Test Data (first time only)
Run `ExcelDataGenerator.main()` from IntelliJ or:
```bash
mvn compile exec:java -Dexec.mainClass="com.bank.ExcelDataGenerator"
```

### Run All Tests
```bash
mvn clean test
```

### Generate Coverage Report
```bash
mvn jacoco:report
```
Open `target/site/jacoco/index.html` in browser.

---

## 👤 Student Info

| Field | Details |
|-------|---------|
| Name | Syed Muhammad Ali Naqvi (Shah) |
| Section | F |
| Semester | Spring 2026 |
| Instructor | Aliza Saeed |
| University | NUCES – Chiniot-Faisalabad |
