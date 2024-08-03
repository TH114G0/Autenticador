package br.com.org.autenticador.query;

public class UsersQuery {
    public static final String CREATE_USERS = "INSERT INTO users (username, password) VALUES (?, ?)";
    public static final String USER_EXISTS = "SELECT * FROM users WHERE username = ?";
    public static final String AUTHENTICATOR_USERS = "SELECT * FROM users WHERE username = ?";
    public static final String UPDATE = "UPDATE users SET username = ?, password = ? WHERE id = ?";
    public static final String DELETE = "DELETE FROM users WHERE id = ?";
    public static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";
}
