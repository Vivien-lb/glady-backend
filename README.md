# Glady Backend challenge

## Getting started

### Prerequisites

To work on the application, everything you need is a JDK. You may use version 8
or above.

### Building the project

This Spring Boot application is built using Maven.
To launch the application, you need a JAVA_HOME environment set.

Use the following command :

On Linux or macOS:

```shell
./mvnw spring-boot:run
```

On Windows:

```shell
mvnw spring-boot:run
```


### DATABASE : http://localhost:8080/h2
This Spring Boot Application contains an embedded in memory H2 Database. 
The data.sql script create a few data in the database at each start, with the following script :

```shell
INSERT INTO GLADY_USER (id, name) VALUES (1, 'Vivien');
INSERT INTO GLADY_USER (id, name) VALUES (2, 'John');   

INSERT INTO COMPANY (id, name, balance) VALUES (1, 'Tesla', 300);
INSERT INTO COMPANY (id, name, balance) VALUES (2, 'Google', 550);
```


Users ID and companies ID are 1 and 2.
You can connect to see the database and see what's in it at the url : localhost:8080/h2

Connection informations :

* Saved Settings: Generic H2 (Embedded)
* Setting Name : Generic H2 (Embedded)
* Driver Class : org.h2.Driver
* JDBC URL: jdbc:h2:mem:gladydb
* User Name: sa




### API : http://localhost:8080/swagger-ui/index.html

This application exposes three endpoints.

* `GET /user/userBalance/{id}`: return the calculated user's balance.
* `POST /deposit/gift/{depositDto}`: allows a company to distribute a gift deposit
* `POST /deposit/meal/{depositDto}`: allows a company to distribute a meal deposit

To help you use this api and play with the application, you can use the swagger url : http://localhost:8080/swagger-ui/index.html
On the api, click on "Try it out", enter the needed informations then click on "Execute".
Users ID and companies ID are 1 and 2.
