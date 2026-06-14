package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    public static Connection getConnection()
            throws SQLException {

        String host = System.getenv("POSTGRES_HOST");

        String port = System.getenv("POSTGRES_PORT");

        String database = System.getenv("POSTGRES_DB");

        String user = System.getenv("POSTGRES_USER");

        String password = System.getenv("POSTGRES_PASSWORD");

        String url = String.format(
                        "jdbc:postgresql://%s:%s/%s",
                        host,
                        port,
                        database);

        return DriverManager.getConnection(
                url,
                user,
                password);
    }
}
