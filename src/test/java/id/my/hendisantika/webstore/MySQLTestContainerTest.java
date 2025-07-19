package id.my.hendisantika.webstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Testcontainers
public class MySQLTestContainerTest {

    // Define the MySQL container with the same version as in compose.yaml
    @Container
    private static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:9.3.0")
            .withDatabaseName("test_shop_db")
            .withUsername("test_user")
            .withPassword("test_password")
            .waitingFor(Wait.forLogMessage(".*ready for connections.*", 2))
            .withCommand("--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci");

    // Dynamically set the datasource properties for Spring Boot
    @DynamicPropertySource
    static void databaseProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", mySQLContainer::getDriverClassName);
    }

    @Test
    void containerIsRunning() {
        // Verify that the container is running
        assertTrue(mySQLContainer.isRunning(), "MySQL container should be running");
        System.out.println("[DEBUG_LOG] MySQL container is running on port: " + mySQLContainer.getMappedPort(3306));
        System.out.println("[DEBUG_LOG] JDBC URL: " + mySQLContainer.getJdbcUrl());
    }

    @Test
    void containerIsHealthy() throws SQLException {
        // Verify that the container is healthy by executing a simple query
        try (
                Connection connection = DriverManager.getConnection(
                        mySQLContainer.getJdbcUrl(),
                        mySQLContainer.getUsername(),
                        mySQLContainer.getPassword()
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT 1")
        ) {
            assertTrue(resultSet.next(), "Query should return a result");
            assertEquals(1, resultSet.getInt(1), "Query result should be 1");
            System.out.println("[DEBUG_LOG] Successfully connected to MySQL and executed query");
            System.out.println("[DEBUG_LOG] MySQL container is healthy");
        }
    }
}