## api-automation-foundation

### Overview:
* This project serves as a foundation for API automation, designed to validate the integrity and functionality of microservices through automated testing of requests and responses for several different methods;
* Currently, it uses the 'postman-library-api' as an example for demonstration and development of test scenarios.

### Requirements
* Java (17 or above);
* Maven (3.9.5 or above).

### Guidelines:
* How to run:
    1. Setup 'config.properties' (Optional)
       * Read the FAQ section for more information.
    2. Open a terminal inside the project and type:
       * `mvn test` (Run all tests);
       * `mvn test -Dcucumber.filter.tags=@Tag` (Run specific tests).

* Tags
  ```Gherkin
  @GET
    # Retrieves a list of all books available in the library;
    # Fetches details of a specific book by its ID.
  @POST
    # Adds a new book to the library with the provided details.
  @PATCH
    # Updates the information of an existing book identified by its ID.
  @DELETE
    # Removes a book from the library using its ID.
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
* The current implementation uses the 'postman-library-api' as an example;
  * https://glitch.com/edit/#!/postman-library-api
* Configuring the .properties file isn't required for demonstration purposes of the 'postman-library-api' due to it not requiring authentication;
  * If your project uses OAuth2 which is the case for most APIs, you will need to configure the 'config.properties' file;
    * automation.api.id = <client_id>;
    * automation.api.secret = <client_secret>;
    * automation.api.environment = <environment>;
    * project.url.X = <base_url> (You can add as many base URLs as you want).
* Utility Class Documentation:
  * AuthenticationUtil - Configure additional urls, scopes, ids and secrets;
  * FileUtil is currently unused, but it's useful for file based flows;
  * HeadersUtil - Configure scope specific and default headers;
  * JSONUtil - Used to convert JSONs to DTOs and vice versa;
  * PropertiesUtil - Used to load and handle properties;
  * TestContext - Setup and save test context data;
  * TestListener - Log and handle test lifecycle events.