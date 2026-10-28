package br.com.SoulUp.entities.nichoRecompensas;

import br.com.SoulUp.dao.UsuarioDao;
import br.com.SoulUp.entities.usuario.Usuarios;

import javax.swing.*;
import java.sql.SQLException;

public class Recompensas {

    static int inteiro(String i) {
        return Integer.parseInt(JOptionPane.showInputDialog(i));
    }

    static double real(String r) {
        return Double.parseDouble(JOptionPane.showInputDialog(r));
    }

    static void mensagem(String parametro, String mensagem, String titulo, int informacao) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, informacao);
    }

    Usuarios usuarios;

    private int recompensa1 = 0;
    private int recompensa2 = 0;
    private int recompensa3 = 0;
    private int pontosRestantes; // Declarado sem valor fixo no topo

    // Construtor corrigido que recebe o Usuario e puxa os pontos dele com segurança
    public Recompensas(Usuarios usuarios) {
        this.usuarios = usuarios;
        this.pontosRestantes = usuarios.getPontos();
    }

    public int getPontosRestantes() {
        return pontosRestantes;
    }

    public void setPontosRestantes(int pontosRestantes) {
        this.pontosRestantes = pontosRestantes;
    }

    public Recompensas() {
    }

    public int getRecompensa1() {
        return recompensa1;
    }

    public void setRecompensa1(int recompensa1) {
        this.recompensa1 = recompensa1;
    }

    public int getRecompensa2() {
        return recompensa2;
    }

    public void setRecompensa2(int recompensa2) {
        this.recompensa2 = recompensa2;
    }

    public int getRecompensa3() {
        return recompensa3;
    }

    public void setRecompensa3(int recompensa3) {
        this.recompensa3 = recompensa3;
    }

    public void recompensas() throws SQLException, ClassNotFoundException, SQLException {
        int sair = 0;
        while(sair != 1 || pontosRestantes == 0) {

            mensagem(null,
                    "Bem vindo ao Sistema de Recompensas!\n\nSelecione A seguir Qual Recompensa deseja Resgatar",
                    "Recompensas", JOptionPane.INFORMATION_MESSAGE);

            int escolha = inteiro("Recompensas:\n\n" +
                    "1-Selo de |Membro Ativo|:\nUm icone especial que aparecerá ao lado de seu Usuario!(2 Pontos)\n\n" +
                    "2-Destaque de Postagem: \n Suas Postagens Terao um impulso de visualizações durante 24 horas(3 Pontos)\n\n" +
                    "3-Título Customizado: \n Crie uma tag de texto (ex: \"Aluno Exemplar\") " +
                    "que ficará fixa abaixo do seu nome nos comentários(2 Pontos).\n\n0-Voltar ao Menu");

            UsuarioDao dao = new UsuarioDao();

            switch (escolha){

                case(1) ->{
                    if (recompensa1 > 0) {
                        mensagem(null, "Você já resgatou esta recompensa!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else if (pontosRestantes < 2) {
                        mensagem(null, "Pontos insuficientes! Você precisa de 2 pontos para esta recompensa.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else {
                        mensagem(null,
                                "Você Resgatou o selo de Membro Ativo!,  Parábens! ",
                                "Resgatado", JOptionPane.INFORMATION_MESSAGE);

                        pontosRestantes -= 2;
                        usuarios.setPontos(pontosRestantes);
                        recompensa1 = 1;

                        dao.atualizar(usuarios);
                    }
                }

                case (2) -> {
                    if (recompensa2 > 0) {
                        mensagem(null, "Você já resgatou esta recompensa!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else if (pontosRestantes < 3) {
                        mensagem(null, "Pontos insuficientes! Você precisa de 3 pontos para esta recompensa.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else {
                        mensagem(null, "Você resgatou o Destaque de Postagem! Suas postagens terão um impulso.", "Resgatado", JOptionPane.INFORMATION_MESSAGE);
                        pontosRestantes -= 3;
                        recompensa2 = 1;
                        usuarios.setPontos(pontosRestantes);

                        dao.atualizar(usuarios);
                    }
                }

                case (3) -> {
                    if (recompensa3 > 0) {
                        mensagem(null, "Você já resgatou esta recompensa!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else if (pontosRestantes < 2) {
                        mensagem(null, "Pontos insuficientes! Você precisa de 2 pontos para esta recompensa.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String tag = JOptionPane.showInputDialog("Digite a tag de texto que deseja usar (ex: Aluno Exemplar):");

                        if (tag != null && !tag.trim().isEmpty()) {
                            mensagem(null, "Título '[" + tag + "]' aplicado com sucesso!", "Resgatado", JOptionPane.INFORMATION_MESSAGE);
                            pontosRestantes -= 2;
                            recompensa3 = 1;
                            usuarios.setPontos(pontosRestantes);

                            dao.atualizar(usuarios);
                        } else {
                            mensagem(null, "Resgate cancelado. Tag inválida ou em branco.", "Aviso", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }

                case (0) -> {
                    sair = 1;
                    mensagem(null, "Voltando ao Menu Principal...", "SoulUP", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                default -> {
                    mensagem(null, "Opção inválida! Escolha 1, 2, 3 ou 0.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}