## notescollab

## Prerequisites to Setups project locally:
1. Java 17 or above
2. PostgreSQL
3. SpringBooot version - 3.2.1

### Steps to Setups project:
1. Install the Java 17
2. Install the PostgreSQL
3. Now fork and clone the project
4. change in the `application.properties` and update DB info

```
application.properties

 spring.datasource.url=jdbc:postgresql://localhost:5432/database_name
 spring.datasource.username=//username
 spring.datasource.password=//password
 spring.datasource.driver-class-name=org.postgresql.Driver

 # This will update table automatically in your database
 spring.jpa.hibernate.ddl-auto=update
```
