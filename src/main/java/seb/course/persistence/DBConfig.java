package seb.course.persistence;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Startup
@Singleton
@DataSourceDefinition(
        name = "java:app/jdbc/PostgresDS",  // The JNDI name we will reference later
        className = "org.postgresql.ds.PGSimpleDataSource", // The Postgres Driver Class
        user = "user",
        password = "password",
        databaseName = "db",
        serverName = "localhost",
        portNumber = 5432,
        minPoolSize = 2,
        maxPoolSize = 10)
public class DBConfig {
}
