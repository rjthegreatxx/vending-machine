# vending-machine

by: RJ

redmond007@gmail.com

## Description ##

See attached pdfs.

## Install and Run ##

### vending-machine-spring-boot ###

Spring boot backend

**Prerequisites**

<ol>
  <li>Java 17</li>
  <li>Spring Boot 3.0.6</li>
  <li>Maven 3.6.1</li>
  <li>PostgreSQL 15</li>
</ol>

**Install and Run**

    
    /* run unit tests to set up test data:
    should_delete_all_sodas()
    create_test_data() */

    maven clean install

    java -jar target/vending-machine-spring-boot-0.0.1-SNAPSHOT.jar

Recommended: alternately you can run in IntelliJ using Maven tools.  See unit tests and Swagger for integration testing 
http://localhost:8080/swagger-ui/

**vending-machine-angular**

Angular front end

**Prerequisites**

<ol>
  <li>Angular 14</li>
  <li>Node.js 18.14.2</li>
  <li>Bootstrap 5.2.3</li>
</ol>

**Install and Run**

    ng serve --port 8081

## TODO ##

<ol>
  <li>More unit tests</li>
  <li>Mocking for jpa in unit tests</li>
  <li>Form validation</li>
  <li>Better dom manipulation and angular form behavior</li>
</ol>