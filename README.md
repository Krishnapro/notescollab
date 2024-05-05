## notescollab
This project is a backed application to create and share notes with friends. It used Java and SpringBoot for the backend and PostgreSql for the database.

## Prerequisites to Setups project locally:
1. Java 17 or above
2. PostgreSQL
3. Maven
4. SpringBooot version - 3.2.1

### Steps to Setups project:
1. Install the Java 17
2. Install the Maven
3. Now fork and clone the project
4. Go to the project directory and run the below commands
5. ```code
   mvn clean install
   ```
6. ```code
   mvn spring-boot:run
   ```
<!-- 6. change in the `application.properties` and update DB info 

```
application.properties

 spring.datasource.url=jdbc:postgresql://localhost:5432/database_name
 spring.datasource.username=//username
 spring.datasource.password=//password
 spring.datasource.driver-class-name=org.postgresql.Driver

 # This will update table automatically in your database
 spring.jpa.hibernate.ddl-auto=update
```
-->


 ## The contributors 🎉

<a href="https://github.com/Krishnapro/notescollab/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=Krishnapro/notescollab" />
</a>
