package br.com.org.autenticador.DAO;

import br.com.org.autenticador.model.Users;
import br.com.org.autenticador.query.UsersQuery;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe responsável por realizar operações de CRUD e autenticação
 * relacionadas aos usuários no banco de dados.
 *
 * Esta classe utiliza a biblioteca BCrypt para criptografia de senhas,
 * garantindo que as senhas sejam armazenadas de forma segura.
 *
 * @author Thiago
 * @version 1.1
 */
public class UsersDAO {

    private final Connection connection;

    /**
     * Construtor que inicializa a classe com uma conexão ao banco de dados.
     *
     * @param connection Conexão com o banco de dados.
     */
    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Verifica se um usuário existe no banco de dados com base no ID e nome de usuário fornecidos.
     *
     * @param users Objeto Users contendo o ID e o nome de usuário a serem verificados.
     * @return true se o usuário existir; false caso contrário.
     */
    public boolean userExists(Users users) {
        try (PreparedStatement pstm = connection.prepareStatement(UsersQuery.USER_EXISTS_BY_ID_AND_USERNAME)) {
            pstm.setInt(1, users.getId());
            pstm.setString(2, users.getUsername());
            try (ResultSet rst = pstm.executeQuery()) {
                return rst.next();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao verificar a existência do usuário.", ex);
        }
    }

    /**
     * Cria um novo usuário no banco de dados, criptografando a senha antes de armazená-la.
     *
     * @param users Objeto Users contendo as informações do novo usuário.
     * @return true se o usuário for criado com sucesso; false caso contrário.
     */
    public boolean createUser(Users users) {
        String hashedPassword = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());
        try (PreparedStatement pstm = connection.prepareStatement(UsersQuery.CREATE_USERS)) {
            pstm.setString(1, users.getUsername());
            pstm.setString(2, hashedPassword);
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao criar o usuário.", ex);
        }
    }

    /**
     * Autentica um usuário comparando o nome de usuário e a senha fornecidos
     * com os armazenados no banco de dados.
     *
     * @param users Objeto Users contendo o nome de usuário e a senha.
     * @return true se as credenciais forem válidas; false caso contrário.
     */
    public boolean authenticateUser(Users users) {
        try (PreparedStatement pstm = connection.prepareStatement(UsersQuery.AUTHENTICATOR_USERS)) {
            pstm.setString(1, users.getUsername());
            try (ResultSet rst = pstm.executeQuery()) {
                if (rst.next()) {
                    String storedHash = rst.getString("password");
                    return BCrypt.checkpw(users.getPassword(), storedHash);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao autenticar o usuário.", ex);
        }
        return false;
    }

    /**
     * Atualiza a senha de um usuário no banco de dados, criptografando a nova senha antes de armazená-la.
     *
     * @param users Objeto Users contendo o ID do usuário e a nova senha.
     * @return true se a senha for atualizada com sucesso; false caso contrário.
     */
    public boolean UpdatePassword(Users users) {
        String hashedPassword = BCrypt.hashpw(users.getPassword(), BCrypt.gensalt());
        try (PreparedStatement pstm = connection.prepareStatement(UsersQuery.UPDATE)) {
            pstm.setString(1, hashedPassword);
            pstm.setInt(2, users.getId());
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar a senha do usuário.", ex);
        }
    }
}
