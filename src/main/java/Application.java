import javax.swing.JFrame;

import br.com.org.autenticador.view.AutenticadorFrame;

/**
 * Classe de teste para inicializar a interface gráfica de gerenciamento do autenticador.
 * <p>
 * Esta classe contém o método principal que cria e exibe a tela principal para gerenciamento
 * de autenticação.
 * </p>
 */
public class Application {

    /**
     * Método principal para iniciar a aplicação.
     * <p>
     * Cria uma instância da tela de gerenciamento de autenticador e define a operação
     * de fechamento da janela para encerrar o aplicativo quando a janela é fechada.
     * </p>
     *
     * @param args Argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        // Cria a instância da tela de gerenciamento de autenticação
        AutenticadorFrame autenticadorFrame = new AutenticadorFrame();

        // Define a operação de fechamento da janela para encerrar a aplicação
        autenticadorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Exibe a janela
        autenticadorFrame.setVisible(true);
    }
}
