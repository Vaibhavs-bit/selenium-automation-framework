# Test Execution Commands

## Basic Execution

### Run all tests
```bash
mvn clean test
```

### Run with specific browser
```bash
# Chrome (default)
mvn clean test -Dbrowser=chrome

# Firefox
mvn clean test -Dbrowser=firefox

# Edge
mvn clean test -Dbrowser=edge
```

## Test Filtering

### Run specific test class
```bash
mvn clean test -Dtest=LoginTest
```

### Run specific test method
```bash
mvn clean test -Dtest=LoginTest#testValidLogin
```

### Run multiple test classes
```bash
mvn clean test -Dtest=LoginTest,HomeTest
```

### Run by test name pattern
```bash
mvn clean test -Dtest=*LoginTest
```

## TestNG Configuration

### Run with TestNG XML
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng.xml
```

### Run parallel tests (multiple browsers)
```bash
mvn clean test -DsuiteXmlFile=src/test/resources/testng-parallel.xml
```

## Advanced Execution

### Skip tests
```bash
mvn clean package -DskipTests
```

### Run specific test group
```bash
mvn clean test -Dgroups=smoke
```

### Enable detailed logging
```bash
mvn clean test -X
```

### Run with specific Java version
```bash
export JAVA_HOME=/path/to/java17
mvn clean test
```

## Maven Profiles

### Run with Chrome profile
```bash
mvn clean test -Pchrome
```

### Run with Firefox profile
```bash
mvn clean test -Pfirefox
```

### Run with Edge profile
```bash
mvn clean test -Pedge
```

## Report Generation

### Generate reports
```bash
mvn clean test
# Reports generated in: test-results/reports/
```

### View test reports
1. Navigate to `test-results/reports/`
2. Open `ExtentReport_*.html` in web browser

## Continuous Integration

### Jenkins Pipeline
```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}
```

## Common Issues & Solutions

### Issue: Maven not found
```bash
# Solution: Install Maven or add to PATH
export PATH=$PATH:/path/to/maven/bin
```

### Issue: Java version mismatch
```bash
# Solution: Check Java version
java -version

# Update JAVA_HOME
export JAVA_HOME=/usr/libexec/java_home -v 17
```

### Issue: Test not running
```bash
# Solution: Clean and rebuild
mvn clean install -DskipTests
mvn test
```

## Performance Tips

- Use parallel execution for faster test runs
- Disable unnecessary logging in production
- Use proper waits instead of sleeps
- Clean up resources properly in teardown

---

For more information, see README.md
