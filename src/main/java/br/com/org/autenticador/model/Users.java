package br.com.org.autenticador.model;

/**
 Classe {@code Users} representa um usuário do sistema.
 * Esta classe contém o nome de usuário e a senha, com os métodos
 * para acessar e modificar esses atributos.
 *
 * @author Thiago
 */
public class Users {
    private int id;
    /**
     * Nome do usuário
     */
    private String username;
    /**
     * Senha do usuário
     */
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do usuário
     * @return O nome do usuário
     */
    public String getUsername() {
        return username;
    }

    /**
     * Modifica o nome do usuário.
     * @param username O novo nome de usuário.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtém a senha do usuário.
     * @return A senha do usuário.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modifica a senha do usuário.
     * @param password A nova senha do usuário.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
