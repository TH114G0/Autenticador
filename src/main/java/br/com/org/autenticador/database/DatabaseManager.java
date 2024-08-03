package br.com.org.autenticador.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/autenticador";
    private static final String ROOT = "root";
    private static final String PASSWORD = "Thiago2826.";
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,ROOT,PASSWORD);
    }
}
