package br.com.SoulUp.entities.principal;

import br.com.SoulUp.dao.UsuarioDao;
import br.com.SoulUp.api.Endereco;
import br.com.SoulUp.entities.usuario.Usuarios;

import javax.swing.*;
import java.sql.SQLException;

public class Login {

    public Login() throws SQLException, ClassNotFoundException {
    }

    static int inteiro(String i) {
        String input = JOptionPane.showInputDialog(i);
        if (input == null || input.trim().isEmpty()) {
            return -1;
        }
        return Integer.parseInt(input);
    }

    static String texto(String t) {
        return JOptionPane.showInputDialog(t);
    }

    static void mensagem(String parametro, String mensagem, String titulo, int informacao) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, informacao);
    }

    UsuarioDao usuarioDAO = new UsuarioDao();
    Endereco endereco = new Endereco();

    public void iniciar() throws SQLException, ClassNotFoundException {
        int sair = 0;

        while (sair != 1) {
            int escolha = inteiro("=== BEM-VINDO AO SOULUP ===\n\n" +
                    "Selecione uma opção:\n\n" +
                    "1 - Fazer Login\n" +
                    "2 - Criar nova conta\n" +
                    "0 - Sair");

            switch (escolha) {
                case (1) -> {



                    String email = texto("Digite seu Email:");
                    String senha = texto("Digite sua Senha:");

                    if (email != null && senha != null && !email.trim().isEmpty() && !senha.trim().isEmpty()) {

                        Usuarios usuariosLogado = usuarioDAO.autenticar(email, senha);

                        if (usuariosLogado != null) {
                            mensagem(null, "Login realizado com sucesso!\nBem-vindo(a) " + usuariosLogado.getNome() + "!",
                                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                            Menu menu = new Menu(usuariosLogado);
                            menu.menu(usuariosLogado);

                        }
                    }else{
                        mensagem(null, "Email ou senha incorretos. Tente novamente.", "Acesso Negado", JOptionPane.ERROR_MESSAGE);

                    }
                }

                case (2) -> {
                    Usuarios novoUsuarios = new Usuarios();

                    mensagem(null, "Vamos iniciar o seu cadastro!", "Novo Usuário", JOptionPane.INFORMATION_MESSAGE);

                    novoUsuarios.cadastro(endereco);

                    usuarioDAO.inserir(novoUsuarios);

                    mensagem(null, "Cadastro realizado com sucesso!\nAgora você já pode fazer login.",
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                }

                case (0) -> {
                    sair = 1;
                    mensagem(null, "Encerrando o sistema. Até logo!", "SoulUP", JOptionPane.INFORMATION_MESSAGE);
                }

                case (-1) -> {
                    sair = 1;
                }

                default -> {
                    mensagem(null, "Opção inválida! Escolha 1, 2 ou 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}