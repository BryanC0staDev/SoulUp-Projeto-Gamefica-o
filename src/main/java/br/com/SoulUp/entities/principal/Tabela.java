package br.com.SoulUp.entities.principal;

import br.com.SoulUp.entities.usuario.Usuarios;
import javax.swing.*;
import java.util.ArrayList;

public class Tabela {

    public Tabela() {
    }

    public void exibirRanking(ArrayList<Usuarios> ranking) {

        if (ranking.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum usuário cadastrado no ranking!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("🏆 RANKING DE USUÁRIOS POR PONTOS 🏆\n\n");
        for (int i = 0; i < ranking.size(); i++) {
            Usuarios u = ranking.get(i);
            String identificacao = (u.getApelido() != null && !u.getApelido().isEmpty()) ? u.getApelido() : u.getNome();

            sb.append((i + 1))
                    .append("º lugar - ")
                    .append(identificacao)
                    .append(" : ")
                    .append(u.getPontos())
                    .append(" pontos\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Tabela de Pontos", JOptionPane.INFORMATION_MESSAGE);
    }
}