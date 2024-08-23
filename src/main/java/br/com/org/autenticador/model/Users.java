package br.com.org.autenticador.model;

/**
 * Classe {@code Users} representa um usuário do sistema.
 * Esta classe contém o ID, nome de usuário e a senha, com métodos
 * para acessar e modificar esses atributos.
 *
 * <p>Exemplo de uso:</p>
 * <pre>
 *     Users user = new Users();
 *     user.setId(1);
 *     user.setUsername("Thiago");
 *     user.setPassword("password123");
 * </pre>
 *
 * @author Thiago
 */
public class Users {
    /**
     * ID único do usuário.
     */
    private int id;

    /**
     * Nome do usuário.
     */
    private String username;

    /**
     * Senha do usuário.
     */
    private String password;

    /**
     * Obtém o ID do usuário.
     * @return O ID do usuário.
     */
    public int getId() {
        return id;
    }

    /**
     * Modifica o ID do usuário.
     * @param id O novo ID do usuário.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome do usuário.
     * @return O nome do usuário.
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
