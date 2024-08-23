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

    private final Connection connection;
    public UsersDAO(Connection connection) {
        this.connection = connection;
    }
    public boolean userExists(Users users){
        try(PreparedStatement pstm = connection.prepareStatement(UsersQuery.USER_EXISTS_BY_ID_AND_USERNAME)) {
            pstm.setInt(1, users.getId());
            pstm.setString(2, users.getUsername());
            try(ResultSet rst = pstm.executeQuery()) {
               return rst.next();
            }
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean createUser(Users users){
        String hashedPassword = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());
        try(PreparedStatement pstm = connection.prepareStatement(UsersQuery.CREATE_USERS)) {
            pstm.setString(1, users.getUsername());
            pstm.setString(2, hashedPassword);
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean authenticateUser(Users users){
        try(PreparedStatement pstm = connection.prepareStatement(UsersQuery.AUTHENTICATOR_USERS)) {
            pstm.setString(1, users.getUsername());
            try(ResultSet rst = pstm.executeQuery()) {
                if (rst.next()) {
                    String storedHash = rst.getString("password");
                    return BCrypt.checkpw(users.getPassword(), storedHash);
                }
            }
        }catch (SQLException ex) {
            throw new RuntimeException("", ex);
        }
        return false;
    }

    public boolean UpdatePassword(Users users) {
        String hashedPassword = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());
        try(PreparedStatement pstm = connection.prepareStatement(UsersQuery.UPDATE)) {
            pstm.setString(1, hashedPassword);
            pstm.setInt(2, users.getId());
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar a senha do usuário", ex);
        }
    }
}
