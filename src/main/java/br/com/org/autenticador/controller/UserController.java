package br.com.org.autenticador.controller;

import br.com.org.autenticador.DAO.UsersDAO;
import br.com.org.autenticador.database.DatabaseManager;
import br.com.org.autenticador.model.Users;

import java.sql.Connection;

public class UserController {
    UsersDAO usersDAO;

    public UserController() {
        Connection connection = new DatabaseManager().getConnection();
        this.usersDAO = new UsersDAO(connection);
    }

    public boolean authenticate(Users users) {
        return usersDAO.authenticateUser(users);
    }
    public boolean createUser(Users users) {
        return usersDAO.createUser(users);
    }

    public boolean userExist(Users users) {
        return usersDAO.userExists(users);
    }
    public boolean UpdatePassword(Users users) {
        return usersDAO.UpdatePassword(users);
    }
}
