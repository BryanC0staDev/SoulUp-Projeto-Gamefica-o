package br.com.SoulUp.entities.nichoRecompensas;

import br.com.SoulUp.dao.UsuarioDao;
import br.com.SoulUp.entities.usuario.Usuarios;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class Missoes {

    Usuarios usuarios;

    static int inteiro(String i) {
        return Integer.parseInt(JOptionPane.showInputDialog(i));
    }

    static double real(String r) {
        return Double.parseDouble(JOptionPane.showInputDialog(r));
    }

    static void mensagem(String parametro, String mensagem, String titulo, int informacao) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, informacao);
    }

    private String descricao;
    private int missaoConcluida1 = 0;
    private int missaoConcluida2 = 0;
    private int missaoConcluida3 = 0;

    // Construtor principal que recebe o usuário logado
    public Missoes(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Usuarios getUsuario() {
        return usuarios;
    }

    public void setUsuario(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public int getMissaoConcluida1() {
        return missaoConcluida1;
    }

    public void setMissaoConcluida1(int missao1) {
        this.missaoConcluida1 = missao1;
    }

    public int getMissaoConcluida2() {
        return missaoConcluida2;
    }

    public void setMissaoConcluida2(int missao2) {
        this.missaoConcluida2 = missao2;
    }

    public int getMissaoConcluida3() {
        return missaoConcluida3;
    }

    public void setMissaoConcluida3(int missao3) {
        this.missaoConcluida3 = missao3;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Missoes() {
    }

    public Missoes(String descricao, Usuarios usuarios) {
        this.descricao = descricao;
        this.usuarios = usuarios;
    }

    public void listaMissoes() throws SQLException, ClassNotFoundException {
        ArrayList<Missoes> listaMissoes = new ArrayList<>();

        Missoes missao1 = new Missoes("Missão 1: Faça um Post no forum do App! (+2 Pontos!)", this.usuarios);
        Missoes missao2 = new Missoes("Missão 2: Siga 4+ usuarios no nosso App! (+4 Pontos!)", this.usuarios);
        Missoes missoes3 = new Missoes("Missao 3: Curtir 2 Posts na nossa rede! (+1 Ponto!)", this.usuarios);

        listaMissoes.add(missao1);
        listaMissoes.add(missao2);
        listaMissoes.add(missoes3);

        String controlador = "";
        UsuarioDao dao = new UsuarioDao();

        while (!controlador.equals("x")) {
            JOptionPane.showMessageDialog(
                    null,
                    "\nBem vindo às Mission-Ups\n" +
                            "Estas são nossas missões para você acumular pontos e poder resgatar RECOMPENSAS!\n" +
                            "Escolha uma das missões a seguir, e assim que concluí-las resgate seus pontos!\n\n" +
                            "Ou digite 0 para sair!",
                    "MISSÕES:", INFORMATION_MESSAGE
            );

            for (int i = 0; i < listaMissoes.size(); i++) {
                JOptionPane.showMessageDialog(null, listaMissoes.get(i).getDescricao(), "MISSÃO " + (i + 1), INFORMATION_MESSAGE);
            }

            int escolha = inteiro("Qual Missão deseja resgatar os pontos? (Digite 0 para sair)");

            if (escolha == 0) {
                controlador = "x";
                break;
            }

            switch (escolha) {
                case (1) -> {
                    if (missaoConcluida1 == 0) {
                        if (usuarios.getQntPosts() < 1) {
                            mensagem(null,
                                    "Você ainda não pode resgatar esta Missão. Faça pelo menos 1 post!",
                                    "AVISO", WARNING_MESSAGE);
                        } else {
                            mensagem(null,
                                    "Parabéns, você concluiu a missão e resgatou seus pontos! +2 Pontos",
                                    "Missão Concluída!", INFORMATION_MESSAGE);

                            usuarios.setPontos(usuarios.getPontos() + 2);
                            missaoConcluida1++;

                            dao.atualizar(usuarios);
                        }
                    } else {
                        mensagem(null, "Você já concluiu esta missão", "Aviso", WARNING_MESSAGE);
                    }
                }
                case (2) -> {
                    if (missaoConcluida2 == 0) {
                        if (usuarios.getSeguindo() < 4) {
                            mensagem(null,
                                    "Você precisa seguir ao menos 4 usuários!",
                                    "Aviso", WARNING_MESSAGE);
                        } else {
                            mensagem(null,
                                    "Parabéns, você concluiu a missão e resgatou seus pontos! +4 Pontos",
                                    "Missão Concluída!", INFORMATION_MESSAGE);

                            usuarios.setPontos(usuarios.getPontos() + 4);
                            missaoConcluida2++;

                            dao.atualizar(usuarios);
                        }
                    } else {
                        mensagem(null, "Você já concluiu esta missão", "Aviso", WARNING_MESSAGE);
                    }
                }
                case (3) -> {
                    if (missaoConcluida3 == 0) {
                        if (usuarios.getCurtidas() < 2) {
                            mensagem(null,
                                    "Você precisa curtir ao menos 2 posts!",
                                    "Aviso", WARNING_MESSAGE);
                        } else {
                            mensagem(null,
                                    "Parabéns, você concluiu a missão e resgatou seus pontos! +1 Ponto",
                                    "Missão Concluída!", INFORMATION_MESSAGE);

                            usuarios.setPontos(usuarios.getPontos() + 1);
                            missaoConcluida3++;

                            dao.atualizar(usuarios);
                        }
                    } else {
                        mensagem(null, "Você já concluiu esta missão", "Aviso", WARNING_MESSAGE);
                    }
                }
                default -> mensagem(null, "Opção Inválida, Tente novamente!", "Erro", WARNING_MESSAGE);
            }
            mensagem(null, "VOLTANDO AO MENU DE MISSÕES!", "SoulUP", INFORMATION_MESSAGE);
        }
    }
}