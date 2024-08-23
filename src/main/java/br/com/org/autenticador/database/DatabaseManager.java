package br.com.org.autenticador.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Gerenciador de conexões com o banco de dados utilizando um pool de conexões.
 *
 * A classe utiliza o ComboPooledDataSource para gerenciar conexões de forma eficiente.
 *
 * @author Thiago
 * @version 1.1
 */
public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/autenticador";
    private static final String USER = "root";
    private static final String PASSWORD = "Thiago2826.";
    private final DataSource dataSource;

    /**
     * Construtor que configura o pool de conexões com os parâmetros especificados.
     */
    public DatabaseManager() {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(URL);
        comboPooledDataSource.setUser(USER);
        comboPooledDataSource.setPassword(PASSWORD);

        // Configurações adicionais do pool (opcionais)
        comboPooledDataSource.setMinPoolSize(5);
        comboPooledDataSource.setMaxPoolSize(20);
        comboPooledDataSource.setAcquireIncrement(5);

        this.dataSource = comboPooledDataSource;
    }

    /**
     * Obtém uma conexão com o banco de dados a partir do pool de conexões.
     *
     * @return Connection uma conexão com o banco de dados.
     * @throws RuntimeException se a conexão falhar.
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao obter a conexão com o banco de dados!", ex);
        }
    }
}
