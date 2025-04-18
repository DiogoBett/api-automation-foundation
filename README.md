## api-automation-foundation

### Overview:
* This project serves as a foundation for API automation, designed to validate the integrity and functionality of microservices through automated testing of requests and responses for GET, PATCH, and POST methods;
* Currently, it uses the free Postman 101 Training APIs as an example for demonstration and development of test scenarios.

### Requirements
* Java (17 or above);
* Maven (3.9.5 or above).

### Guidelines:
* How to run:
    1. Setup 'config.properties' (Troubleshooting);
    2. Open a terminal inside the project and type:
       * `mvn test` (Run all tests);
       * `mvn test -Dcucumber.filter.tags=@Tag` (Run specific tests).

* Tags
  ```Gherkin
  @Feature1
    # GET Description of Feature 1
  @Feature2
    # POST Description of Feature 1
  @Feature3
    # PATCH Description of Feature 1
  ```

### Key Components
* Cucumber - A testing tool that facilitates collaboration between technical and non-technical team members, enabling the creation of executable specifications for API tests;
* Rest Assured - Used for validation of HTTP responses, making it easier to write powerful and concise tests for REST APIs;
* JUnit - A java testing framework that provides annotations and assertions for writing and running tests;
* Maven - Simplifies the build process,dependency management, test execution and report generation;
* Jackson - A high-performance and versatile JSON processing library for Java;
* Logback - A reliable, generic, fast and flexible logging framework for Java-based applications;
* Lombok - A java library tool that is used to minimize/remove the boilerplate code.

### Dependencies
* io.cucumber
  * cucumber-java
  * cucumber-core
  * cucumber-junit
  * gherkin
* io.rest-assured
  * rest-assured
  * json-schema-validator
* com.fasterxml.jackson
  * core
    * jackson-databind
  * datatype
    * jackson-datatype-jsr310
* org.json
  * json
* ch.cos.logback
  * logback-classic
* org.projectlombok
  * lombok

### Documentation
* Cucumber - https://cucumber.io/docs/cucumber/
* Rest Assured - https://github.com/rest-assured/rest-assured/wiki/Usage
* JUnit - https://junit.org/junit4/javadoc/latest/
* Maven - https://maven.apache.org/guides/index.html
* Jackson - https://github.com/FasterXML/jackson-docs
* Logback - https://logback.qos.ch/documentation.html
* Lombok - https://projectlombok.org/features/

### FAQ / Troubleshooting
* Currently Developing - Free API for Automation Example:
  * https://glitch.com/edit/#!/postman-library-api
  * https://www.postman.com/postman/postman-classroom-program/collection/7ifvzlh/library-api-reference?action=share&source=copy-link&creator=30363938
* Additional Documentation Here (TODO):
  * A
  * B