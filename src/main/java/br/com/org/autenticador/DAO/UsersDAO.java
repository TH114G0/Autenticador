package br.com.org.autenticador.DAO;

import br.com.org.autenticador.database.DatabaseManager;
import br.com.org.autenticador.model.Users;
import br.com.org.autenticador.query.UsersQuery;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {
    public static boolean userExists(Users users) throws SQLException {
        try(Connection connection = new DatabaseManager().getConnection();
            PreparedStatement pstm = connection.prepareStatement(UsersQuery.USER_EXISTS)) {
            pstm.setString(1, users.getUsername());
            try(ResultSet rst = pstm.executeQuery()) {
               return rst.next();
            }
        }
    }

    public static void createUser(Users users) throws SQLException{
        String hashedPassword = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());
        try(Connection connection = new DatabaseManager().getConnection();
            PreparedStatement pstm = connection.prepareStatement(UsersQuery.CREATE_USERS)) {
            pstm.setString(1, users.getUsername());
            pstm.setString(2, hashedPassword);
            pstm.executeUpdate();
        }
    }

    public static boolean authenticateUser(Users users) throws SQLException{
        try(Connection connection = new DatabaseManager().getConnection();
            PreparedStatement pstm = connection.prepareStatement(UsersQuery.AUTHENTICATOR_USERS)) {
            pstm.setString(1, users.getUsername());
            try(ResultSet rst = pstm.executeQuery()) {
                if (rst.next()) {
                    String storedHash = rst.getString("password");
                    return BCrypt.checkpw(users.getPassword(), storedHash);
                }
            }
        }
        return false;
    }
}
