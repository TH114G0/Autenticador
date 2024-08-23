package br.com.org.autenticador.query;

/**
 * Classe {@code UsersQuery} contém os comandos SQL utilizados para operar na tabela users no banco de dados.
 *  <p>
 *      Essa classe fornece os comandos necessários para criar, verificar existência, autenticar, atualizar, excluir e encontrar usuários na tabela users.
 *  </p>
 *  @autor Thiago
 */
public class UsersQuery {
    /**
     * Comando SQL para criar um novo usuário.
     * <p>
     *     Insere um novo registro na tabela de users com username e password.
     * </p>
     */
    public static final String CREATE_USERS = "INSERT INTO users (username, password) VALUES (?, ?)";
    /**
     * Comando SQL para verificar se um usuário já existe.
     * <p>
     *     Seleciona todos os campos username na tabela users onde é igual ao username passado.
     * </p>
     */
    public static final String USER_EXISTS_BY_ID_AND_USERNAME = "SELECT 1 FROM users WHERE id = ? AND username = ?";

    /**
     * Comando SQL para
     */
    public static final String AUTHENTICATOR_USERS = "SELECT * FROM users WHERE username = ?";
    public static final String UPDATE = "UPDATE users SET password = ? WHERE id = ?";
}
