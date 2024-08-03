package br.com.org.autenticador;

import br.com.org.autenticador.DAO.UsersDAO;
import br.com.org.autenticador.model.Users;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Você possui login ao sistema ? " +
                "\nSIM - 1" +
                "\nNÃO - 2" +
                "\nSAIR - 0");
        int resposta = scanner.nextInt();
        switch (resposta) {
            case 1:
                Access();
                break;
            case 2:
                create();
                break;
            case 0:
                System.out.println("SAINDO...");
                break;
            default:
                System.out.println("Alternativa incorreta");
                break;
        }
        System.out.println("Programa finalizado");
    }

    private static void Access() {
        Users users = new Users();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu login: ");
        users.setUsername(scanner.next());
        System.out.println("Digite sua senha:");
        users.setPassword(scanner.next());

        try {
            if(UsersDAO.authenticateUser(users)) {
                System.out.println("\nLogin bem-sucedido!");
            }else {
                System.out.println("\nLogin ou senha incorretos.");
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void create() {
        Users users = new Users();
        Scanner scanner = new Scanner(System.in);

        System.out.println("CADASTRO DE USUÁRIO");

        System.out.println("Digite seu login: ");
        users.setUsername(scanner.next());

        System.out.println("Digite sua senha: ");
        users.setPassword(scanner.next());

        try {
            if(UsersDAO.userExists(users)) {
                System.out.println("\nUsuário já existe. Tente outro login.");
            }else {
                UsersDAO.createUser(users);
                System.out.println("Usuário criando com sucesso!");
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}