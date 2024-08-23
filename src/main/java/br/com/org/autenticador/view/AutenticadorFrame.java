package br.com.org.autenticador.view;

import br.com.org.autenticador.controller.UserController;
import br.com.org.autenticador.model.Users;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutenticadorFrame extends JFrame {

    private JLabel labelLogin, labelSenha;
    private JTextField textoLogin;
    private JPasswordField textoSenha;
    private JButton botaoLogin, botaoEsqueciSenha, botaoCadastro;
    Users users;
    UserController usersController;

    public AutenticadorFrame() {
        users = new Users();
        usersController = new UserController();
        setTitle("Tela de Autenticação");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicialização dos componentes
        labelLogin = new JLabel("Login:");
        labelSenha = new JLabel("Senha:");

        textoLogin = new JTextField(20);
        textoSenha = new JPasswordField(20);

        botaoLogin = new JButton("Login");
        botaoEsqueciSenha = new JButton("Esqueci Senha");
        botaoCadastro = new JButton("Realizar Cadastro");


        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(labelLogin, gbc);

        gbc.gridx = 1;
        add(textoLogin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(labelSenha, gbc);

        gbc.gridx = 1;
        add(textoSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(botaoLogin, gbc);

        gbc.gridy = 3;
        add(botaoEsqueciSenha, gbc);

        gbc.gridy = 4;
        add(botaoCadastro, gbc);

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = textoLogin.getText();
                String senha = new String(textoSenha.getPassword());
                if (!login.isEmpty() && !senha.isEmpty()) {
                    users.setUsername(login);
                    users.setPassword(senha);
                    if (usersController.authenticate(users)) {
                        JOptionPane.showMessageDialog(null, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "TODOS os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                textoLogin.setText("");
                textoSenha.setText("");
            }
        });

        botaoEsqueciSenha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria a tela para identificação do usuário
                JFrame userIdentificationFrame = new JFrame("Identificar Usuário");
                userIdentificationFrame.setSize(400, 300);
                userIdentificationFrame.setLocationRelativeTo(null);  // Centraliza a janela na tela
                userIdentificationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Fecha apenas a janela de identificação do usuário
                userIdentificationFrame.setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre componentes
                gbc.anchor = GridBagConstraints.WEST;  // Alinha os componentes à esquerda

                // Inicializa os componentes
                JLabel idLabel = new JLabel("ID do Usuário:");
                JTextField idField = new JTextField(20);
                JLabel nameLabel = new JLabel("Nome:");
                JTextField nameField = new JTextField(20);
                JButton confirmButton = new JButton("Confirmar");

                idLabel.setPreferredSize(new Dimension(120, 30));  // Ajuste o tamanho das labels
                nameLabel.setPreferredSize(new Dimension(120, 30));  // Ajuste o tamanho das labels
                confirmButton.setPreferredSize(new Dimension(100, 40));  // Ajuste o tamanho do botão

                // Adiciona os componentes à tela
                gbc.gridx = 0;
                gbc.gridy = 0;
                userIdentificationFrame.add(idLabel, gbc);

                gbc.gridx = 1;
                userIdentificationFrame.add(idField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                userIdentificationFrame.add(nameLabel, gbc);

                gbc.gridx = 1;
                userIdentificationFrame.add(nameField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;  // Ocupa duas colunas
                gbc.anchor = GridBagConstraints.CENTER;  // Centraliza o botão
                userIdentificationFrame.add(confirmButton, gbc);

                // Adiciona ação ao botão de confirmar
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String id = idField.getText().trim();
                        String name = nameField.getText().trim();


                        if (!id.isEmpty() && !name.isEmpty()) {
                            users.setId(Integer.parseInt(id));
                            users.setUsername(name);
                            if (usersController.userExist(users)) {
                                // Fecha a tela de identificação do usuário
                                userIdentificationFrame.dispose();

                                // Cria a nova tela para redefinição de senha
                                JFrame passwordResetFrame = new JFrame("Redefinir Senha");
                                passwordResetFrame.setSize(400, 300);
                                passwordResetFrame.setLocationRelativeTo(null);  // Centraliza a janela na tela
                                passwordResetFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Fecha apenas a janela de redefinição de senha
                                passwordResetFrame.setLayout(new GridBagLayout());

                                GridBagConstraints gbc = new GridBagConstraints();
                                gbc.insets = new Insets(10, 10, 10, 10);
                                gbc.anchor = GridBagConstraints.WEST;


                                JLabel newPasswordLabel = new JLabel("Nova Senha:");
                                JPasswordField newPasswordField = new JPasswordField(20);
                                JLabel confirmPasswordLabel = new JLabel("Confirme a Senha:");
                                JPasswordField confirmPasswordField = new JPasswordField(20);
                                JButton saveButton = new JButton("Salvar");

                                newPasswordLabel.setPreferredSize(new Dimension(120, 30));
                                confirmPasswordLabel.setPreferredSize(new Dimension(120, 30));
                                saveButton.setPreferredSize(new Dimension(100, 40));


                                gbc.gridx = 0;
                                gbc.gridy = 0;
                                passwordResetFrame.add(newPasswordLabel, gbc);

                                gbc.gridx = 1;
                                passwordResetFrame.add(newPasswordField, gbc);

                                gbc.gridx = 0;
                                gbc.gridy = 1;
                                passwordResetFrame.add(confirmPasswordLabel, gbc);

                                gbc.gridx = 1;
                                passwordResetFrame.add(confirmPasswordField, gbc);

                                gbc.gridx = 0;
                                gbc.gridy = 2;
                                gbc.gridwidth = 2;
                                gbc.anchor = GridBagConstraints.CENTER;
                                passwordResetFrame.add(saveButton, gbc);
                                saveButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        String newPassword = new String(newPasswordField.getPassword());
                                        String confirmPassword = new String(confirmPasswordField.getPassword());

                                        if (newPassword.equals(confirmPassword)) {

                                            JOptionPane.showMessageDialog(passwordResetFrame, "Senha alterada com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                                            passwordResetFrame.dispose();
                                        } else {
                                            JOptionPane.showMessageDialog(passwordResetFrame, "As senhas não coincidem.", "Erro", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                });
                                passwordResetFrame.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(userIdentificationFrame, "ID ou nome inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "TODOS os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                            textoLogin.setText("");
                            textoSenha.setText("");
                        }
                    }
                });

                // Torna a tela visível
                userIdentificationFrame.setVisible(true);
            }
        });


        botaoCadastro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria a nova tela para cadastro
                JFrame cadastroFrame = new JFrame("Cadastro");
                cadastroFrame.setSize(400, 300);
                cadastroFrame.setLocationRelativeTo(null);  // Centraliza a janela na tela
                cadastroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Fecha apenas a janela de cadastro
                cadastroFrame.setLayout(new GridBagLayout());

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);  // Espaçamento entre componentes
                gbc.anchor = GridBagConstraints.WEST;  // Alinha os componentes à esquerda

                // Inicializa os componentes
                JLabel nomeLabel = new JLabel("Nome:");
                JTextField nomeField = new JTextField(20);
                JLabel senhaLabel = new JLabel("Senha:");
                JPasswordField senhaField = new JPasswordField(20);
                JButton salvarButton = new JButton("Salvar");

                nomeLabel.setPreferredSize(new Dimension(80, 30));  // Ajuste o tamanho das labels
                senhaLabel.setPreferredSize(new Dimension(80, 30));  // Ajuste o tamanho das labels
                salvarButton.setPreferredSize(new Dimension(100, 40));  // Ajuste o tamanho do botão

                // Adiciona os componentes à tela
                gbc.gridx = 0;
                gbc.gridy = 0;
                cadastroFrame.add(nomeLabel, gbc);

                gbc.gridx = 1;
                cadastroFrame.add(nomeField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 1;
                cadastroFrame.add(senhaLabel, gbc);

                gbc.gridx = 1;
                cadastroFrame.add(senhaField, gbc);

                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;  // Ocupa duas colunas
                gbc.anchor = GridBagConstraints.CENTER;  // Centraliza o botão
                cadastroFrame.add(salvarButton, gbc);
                salvarButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nome = nomeField.getText();
                        String senha = new String(senhaField.getPassword());

                        if (!nome.isEmpty() && !senha.isEmpty()) {
                            users.setUsername(nome);
                            users.setPassword(senha);
                            if (usersController.createUser(users)) {
                                JOptionPane.showMessageDialog(null, "Usuário criado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Ops, houve algum erro ao criar seu usuário", "Erro", JOptionPane.ERROR_MESSAGE);
                                textoLogin.setText("");
                                textoSenha.setText("");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "TODOS os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                            textoLogin.setText("");
                            textoSenha.setText("");
                        }
                        // Fecha a tela de cadastro após salvar
                        cadastroFrame.dispose();
                    }
                });

                // Exibe a tela de cadastro
                cadastroFrame.setVisible(true);
            }
        });
    }
}
