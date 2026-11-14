## api-automation-foundation

### Overview:
* This project serves as a foundation for API automation, validating HTTP requests and responses with executable Cucumber scenarios;
* The example scenarios target the public ReqRes demo API at `https://reqres.in`, using its no-key `/api/users` endpoints;
* ReqRes demo writes are echoed in the response and are not persisted.

### Requirements
* Java (17 or above);
* Maven (3.9.5 or above).

### Guidelines:
* Test Run:
  * Run `mvn test` to execute all test scenarios.
* Formatting:
  * Run `mvn spotless:apply` to format Java sources;
  * Run `mvn spotless:check` to check formatting without changing files.

### Scenario Coverage
Each feature is tagged with its HTTP method (`@GET`, `@POST`, `@PUT`, `@PATCH`, or `@DELETE`) and includes positive and negative scenarios:
* **GET**: list users, retrieve an existing user, and request a missing user;
* **POST**: create a user and reject malformed JSON;
* **PUT**: replace a user and reject malformed JSON;
* **PATCH**: partially update a user and reject malformed JSON;
* **DELETE**: delete an existing user and delete a missing user. ReqRes returns 204 for both cases.

### Key Components
* Cucumber - A testing tool that facilitates collaboration between technical and non-technical team members, enabling the creation of executable specifications for API tests;
* Rest Assured - Used for validation of HTTP responses, making it easier to write powerful and concise tests for REST APIs;
* JUnit - A java testing framework that provides annotations and assertions for writing and running tests;
* Maven - Simplifies the build process,dependency management, test execution and report generation;
* Jackson - A high-performance and versatile JSON processing library for Java;
* Logback - A reliable, generic, fast and flexible logging framework for Java-based applications;
* Lombok - A java library tool that is used to minimize/remove the boilerplate code;
* Spotless - A general-purpose formatting plugin which is used for Java.

### Dependencies
* io.cucumber
  * cucumber-java
  * cucumber-junit-platform-engine
* org.junit.platform
  * junit-platform-suite
* org.junit.jupiter
  * junit-jupiter-api
* io.rest-assured
  * rest-assured
* com.fasterxml.jackson
  * jackson-databind
  * jackson-datatype-jdk8
* ch.qos.logback
  * logback-classic
* org.projectlombok
  * lombok
* commons-io
* commons-codec

### Documentation
* Cucumber - https://cucumber.io/docs/cucumber/
* REST Assured - https://rest-assured.io/
* JUnit - https://junit.org/junit5/docs/current/user-guide/
* Maven - https://maven.apache.org/guides/index.html
* Jackson - https://github.com/FasterXML/jackson-docs
* Logback - https://logback.qos.ch/documentation.html
* Lombok - https://projectlombok.org/features/
* Spotless Maven Plugin - https://github.com/diffplug/spotless/tree/main/plugin-maven
* ReqRes - https://reqres.in/docs

### FAQ / Troubleshooting
* How do I select the target environment?
  * Set `environment` in `src/test/resources/config.properties` to `dev`, `preprod`, or `prod`;
  * `dev` is the default and points to `https://reqres.in`;
  * `base.url.preprod` and `base.url.prod` are placeholders. Replace them with the appropriate base URLs before selecting those environments.
