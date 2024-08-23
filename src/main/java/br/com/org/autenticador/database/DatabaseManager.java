package br.com.org.autenticador.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/autenticador";
    private static final String ROOT = "root";
    private static final String PASSWORD = "Thiago2826.";
    private DataSource source;

    public DatabaseManager() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(URL);
        comboPooledDataSource.setUser(ROOT);
        comboPooledDataSource.setPassword(PASSWORD);

        this.source = comboPooledDataSource;
    }
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL,ROOT,PASSWORD);
        }catch (SQLException ex) {
            throw new RuntimeException("Conexão com o banco de dados falhou!", ex);
        }
    }
}
