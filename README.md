# Basic Banking Model Application

This basic spring boot application provides a relational data model backed by spring data jpa for Customer, Account and Transaction entities.
Along with that it exposes basic rest services for :

* View Account list for a given customer:

Example Request:  `http://localhost:8080/accounts/10001`


* View Transactions for a given account:

Example Request: `http://localhost:8080/transactions/20006`

## Running the Application

Use default spring boot command i.e. `mvn spring-boot:run` to run the app.

To test the application, you can use the test data present here `src/main/resources/data.sql`
This data is auto populated to the in-memory h2-database `http://localhost:8080/h2-console/` once the application is started.
 
