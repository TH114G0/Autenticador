package br.com.org.autenticador.query;

/**
 * Classe {@code UsersQuery} contém os comandos SQL utilizados para operar na tabela users no banco de dados.
 * <p>
 *     Esta classe fornece os comandos necessários para criar, verificar existência, autenticar, atualizar, excluir e encontrar usuários na tabela users.
 * </p>
 *
 * @author Thiago
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
     *     Seleciona todos os campos username na tabela users onde o ID e o username são iguais aos valores passados.
     * </p>
     */
    public static final String USER_EXISTS_BY_ID_AND_USERNAME = "SELECT 1 FROM users WHERE id = ? AND username = ?";

    /**
     * Comando SQL para autenticar um usuário.
     * <p>
     *     Seleciona todos os campos do usuário na tabela users onde o username é igual ao valor passado.
     *     Este comando é utilizado para verificar se as credenciais fornecidas são válidas.
     * </p>
     */
    public static final String AUTHENTICATOR_USERS = "SELECT * FROM users WHERE username = ?";

    /**
     * Comando SQL para atualizar a senha de um usuário.
     * <p>
     *     Atualiza a senha do usuário na tabela users com base no ID fornecido.
     * </p>
     */
    public static final String UPDATE = "UPDATE users SET password = ? WHERE id = ?";
}
