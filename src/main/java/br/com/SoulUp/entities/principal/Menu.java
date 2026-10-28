package br.com.SoulUp.entities.principal;

import br.com.SoulUp.dao.UsuarioDao;
import br.com.SoulUp.entities.nichoRecompensas.Missoes;
import br.com.SoulUp.entities.nichoRecompensas.Recompensas;
import br.com.SoulUp.entities.usuario.Usuarios;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Menu {

    static String texto(String t){
        return JOptionPane.showInputDialog(t);
    }

    static int inteiro(String i) {
        return Integer.parseInt(JOptionPane.showInputDialog(i));
    }

    static double real(String r){
        return Double.parseDouble(JOptionPane.showInputDialog(r));
    }

    private Usuarios usuarios;
    Tabela tabela = new Tabela();
    Missoes missoes = new Missoes();



    public Menu(Usuarios usuariosLogado) throws SQLException, ClassNotFoundException {
        this.usuarios = usuariosLogado;
    }



    public void menu(Usuarios usuariosLogado) throws SQLException, ClassNotFoundException {
        boolean controlador = true;
        AcoesApp acoesApp = new AcoesApp();
        this.missoes = new Missoes(usuariosLogado);




        while (controlador) {
            String nomeExibicao = (usuarios.getApelido() != null && !usuarios.getApelido().isEmpty())
                    ? usuarios.getApelido()
                    : usuarios.getNome();

            int escolha = inteiro (
                    "Ola " + nomeExibicao.toUpperCase() + " Seja muito Bem vindo ao nosso app!" +
                            "\nAbaixo você terá acesso à suas possiveis escolhas dentro do nosso app!" +
                            "\n1-Navegar Pelo App"+
                            "\n2-Ver Tabela de Pontos"+
                            "\n3-Alterar/Ver Dados"+
                            "\n4-Acessar Missões"+
                            "\n5-Resgatar Recompensas"+
                            "\n6-Excluir Conta"+
                            "\n7-Fazer Log-out"
            );

            switch(escolha){
                case (1) -> {

                    acoesApp.navegaçao(usuarios, acoesApp);
                }
                case (2) -> {
                    ArrayList<Usuarios> ranking = new UsuarioDao().buscarRankingUsuarios();
                    tabela.exibirRanking(ranking);
                }
                case (3) -> {
                    usuarios.alterarCadastro(usuarios);
                }
                case (4) -> {
                    missoes.listaMissoes();
                }
                case (5) -> {
                    Recompensas recompensas = new Recompensas(usuariosLogado);
                    recompensas.recompensas();
                }
                case (6) -> {
                    int confirmarExclusao = JOptionPane.showConfirmDialog(

                            null,
                            "Tem certeza de que deseja excluir sua conta permanentemente? Esta ação não pode ser desfeita.",
                            "Confirmar Exclusão",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

                    if (confirmarExclusao == 0) {
                        UsuarioDao dao = new UsuarioDao();
                        dao.excluir(usuarios.getCpf());

                        JOptionPane.showMessageDialog(
                                null,
                                "Conta excluída com sucesso. Até logo!",
                                "Conta Excluída",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        controlador = false;

                    }
                }
                case (7) -> {
                    JOptionPane.showMessageDialog(null,"Encerrando Programa! Ate Breve.", "", JOptionPane.INFORMATION_MESSAGE);
                    controlador = false;

                }
            }
        }
    }
}