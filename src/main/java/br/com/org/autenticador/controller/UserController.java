package br.com.org.autenticador.controller;

import br.com.org.autenticador.DAO.UsersDAO;
import br.com.org.autenticador.database.DatabaseManager;
import br.com.org.autenticador.model.Users;

import java.sql.Connection;

/**
 * Classe que controla as operações relacionadas aos usuários, como autenticação, criação de novos usuários
 * e atualização de senhas. Esta classe atua como uma camada intermediária entre a interface do usuário e o
 * acesso ao banco de dados.
 *
 * @author Thiago
 * @version 1.1
 */
public class UserController {
    private UsersDAO usersDAO;

    /**
     * Construtor padrão que inicializa a conexão com o banco de dados e o objeto UsersDAO.
     */
    public UserController() {
        Connection connection = new DatabaseManager().getConnection();
        this.usersDAO = new UsersDAO(connection);
    }

    /**
     * Autentica um usuário com base nas credenciais fornecidas.
     *
     * @param users Objeto Users contendo o nome de usuário e a senha.
     * @return true se as credenciais forem válidas; false caso contrário.
     */
    public boolean authenticate(Users users) {
        return usersDAO.authenticateUser(users);
    }

    /**
     * Cria um novo usuário no sistema.
     *
     * @param users Objeto Users contendo as informações do novo usuário.
     * @return true se o usuário for criado com sucesso; false caso contrário.
     */
    public boolean createUser(Users users) {
        return usersDAO.createUser(users);
    }

    /**
     * Verifica se um usuário já existe no sistema com base no nome de usuário fornecido.
     *
     * @param users Objeto Users contendo o nome de usuário a ser verificado.
     * @return true se o usuário já existir; false caso contrário.
     */
    public boolean userExist(Users users) {
        return usersDAO.userExists(users);
    }

    /**
     * Atualiza a senha de um usuário no sistema.
     *
     * @param users Objeto Users contendo o ID do usuário e a nova senha.
     * @return true se a senha for atualizada com sucesso; false caso contrário.
     */
    public boolean UpdatePassword(Users users) {
        return usersDAO.UpdatePassword(users);
    }
}
