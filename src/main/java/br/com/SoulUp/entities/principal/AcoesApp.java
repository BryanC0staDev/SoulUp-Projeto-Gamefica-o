package br.com.SoulUp.entities.principal;

import br.com.SoulUp.dao.PostsDao;
import br.com.SoulUp.dao.UsuarioDao;
import br.com.SoulUp.entities.usuario.Posts;
import br.com.SoulUp.entities.usuario.Usuarios;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class AcoesApp {

    private int curtido = 0;
    private ArrayList<String> listaPostsUsuarios = new ArrayList<>();
    int posts = 0;

    Usuarios usuarios = new Usuarios();

    public int getCurtido() {
        return curtido;
    }

    public void setCurtido(int curtido) {
        this.curtido = curtido;
    }

    public ArrayList<String> getListaPostsUsuarios() {
        return listaPostsUsuarios;
    }

    public void setListaPostsUsuarios(ArrayList<String> listaPostsUsuarios) {
        this.listaPostsUsuarios = listaPostsUsuarios;
    }

    public AcoesApp() {
    }

    static String texto(String t) {
        return JOptionPane.showInputDialog(t);
    }

    static int inteiro(String i) {
        return Integer.parseInt(JOptionPane.showInputDialog(i));
    }

    static double real(String r) {
        return Double.parseDouble(JOptionPane.showInputDialog(r));
    }

    static void mensagem(String parametro, String mensagem, String titulo, int informacao) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, informacao);
    }

    // Função para buscar os posts do Banco
    public ArrayList<Posts> posts() throws SQLException, ClassNotFoundException {
        PostsDao postsDao = new PostsDao();
        return postsDao.buscarTodosPosts();
    }

    // Recuperando nomes do Banco
    public ArrayList<String> usuarios() throws SQLException, ClassNotFoundException {
        UsuarioDao dao = new UsuarioDao();
        return dao.buscarTodosNomes();
    }

    // Função Principal
    public void navegaçao(Usuarios usuarios, AcoesApp acoesApp) throws SQLException, ClassNotFoundException {

        mensagem(
                null,
                "Entrando na rede...",
                "", JOptionPane.INFORMATION_MESSAGE);

        int controlador = 1;

        while (controlador != 0) {

            int escolha = inteiro("O que deseja Fazer?" +
                    "\n1-Ver Posts" +
                    "\n2-Ver Usuarios" +
                    "\n3-Fazer um Post!" +
                    "\n4-Entrar no seu Perfil" +
                    "\n0-Voltar ao Menu");

            switch (escolha) {
                case (1) -> {
                    ArrayList<Posts> listaPosts = acoesApp.posts();

                    if (listaPosts.isEmpty()) {
                        mensagem(null, "Nenhum post encontrado no momento!", "", JOptionPane.WARNING_MESSAGE);
                        break;
                    }

                    for (int i = 0; i < listaPosts.size(); i++) {
                        JOptionPane.showMessageDialog(null, "Navegando Por Posts...");
                        mensagem(null, listaPosts.get(i).getConteudo() + "  - Autor: " + listaPosts.get(i).getNomeAutor(), "", JOptionPane.INFORMATION_MESSAGE);
                        int curtir = JOptionPane.showConfirmDialog(null,
                                "Deseja Curtir esse Post?", "", JOptionPane.YES_NO_OPTION);

                        if (curtir == 0) {
                            mensagem(null, "Post Curtido com Sucesso!❤", "", JOptionPane.INFORMATION_MESSAGE);
                            usuarios.setCurtidas(usuarios.getCurtidas() + 1);


                            UsuarioDao dao = new UsuarioDao();
                            dao.incrementarCurtidas(usuarios.getCpf());

                        }
                    }
                }

                case (2) -> {
                    mensagem(null, "Buscando Perfis...", "", JOptionPane.INFORMATION_MESSAGE);

                    ArrayList<String> listaNomesUsuarios = acoesApp.usuarios();

                    if (listaNomesUsuarios.isEmpty()) {
                        mensagem(null, "Nenhum usuário cadastrado no banco!", "", JOptionPane.WARNING_MESSAGE);
                        break;
                    }

                    StringBuilder menuPerfis = new StringBuilder("Qual Perfil deseja entrar?\n");
                    for (int i = 0; i < listaNomesUsuarios.size(); i++) {
                        menuPerfis.append((i + 1)).append("-").append(listaNomesUsuarios.get(i)).append("\n");
                    }

                    int escolhaPerfil = inteiro(menuPerfis.toString());

                    if (escolhaPerfil < 1 || escolhaPerfil > listaNomesUsuarios.size()) {
                        mensagem(null, "Esse Perfil nao existe, Tente Novamente!", "", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        int perfilIndex = escolhaPerfil - 1;
                        String nomeEscolhido = listaNomesUsuarios.get(perfilIndex);

                        int confirmaPerfil = JOptionPane.showConfirmDialog(null,
                                "Deseja seguir ou interagir com o perfil de: " + nomeEscolhido, "", JOptionPane.YES_NO_OPTION);

                        if (confirmaPerfil == 0) {
                            mensagem(null, "Você agora está seguindo " + nomeEscolhido, "", JOptionPane.INFORMATION_MESSAGE);

                            UsuarioDao dao = new UsuarioDao();

                            usuarios.setSeguindo(usuarios.getSeguindo() + 1);
                            //aumenta seguindo
                            dao.incrementarSeguindo(usuarios.getCpf());

                            //aumenta seguidores
                            dao.incrementarSeguidor(nomeEscolhido);
                        }
                    }
                }


                case (3) -> {
                    String conteudoPost = JOptionPane.showInputDialog("Digite o conteúdo do seu Post:");
                    if (conteudoPost != null && !conteudoPost.trim().isEmpty()) {
                        Posts novoPost = new Posts(conteudoPost.trim(), usuarios.getCpf());


                        PostsDao postDao = new PostsDao();
                        postDao.inserir(novoPost);

                        usuarios.setQntPosts(usuarios.getQntPosts() + 1);
                        UsuarioDao dao = new UsuarioDao();
                        dao.incrementarPosts(usuarios.getCpf());

                        mensagem(null, "Post publicado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                case (4) -> {
                    mensagem(null, "Seu Perfil:\n\n" + usuarios, "Perfil", JOptionPane.INFORMATION_MESSAGE);
                }

                case (0) -> {
                    controlador = 0;
                    mensagem(null, "Voltando ao Menu Principal...", "", JOptionPane.INFORMATION_MESSAGE);
                }

                default -> mensagem(null, "Opção inválida!", "Erro", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}