package ch.bfh.java.experiments.databases;

import java.sql.*;

public class JdbcExample {
    public static void main(String[] args) {
        // Print a meaningful error msg if the driver is not found
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new Error("Cannot find JDBC Driver", e);
        }
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:./src/main/sql/setup/university.db")) {
            System.out.println("Connection established!");
            // Using the connection ...
            dosth(conn);
        } catch (SQLException e) {
            throw new Error("Cannot establish database connection", e);
        }
    }

    static void dosth(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select * from instructor");
            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println(name);
            }
        } catch (SQLException e) {
            throw new Error("Problem executing query", e);
        }
    }
}
