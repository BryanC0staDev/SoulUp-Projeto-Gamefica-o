package br.com.SoulUp.dao;

import br.com.SoulUp.conexoes.ConexaoFactory;
import br.com.SoulUp.entities.usuario.Posts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostsDao {

    public void inserir(Posts post) throws SQLException {
        String sql = "INSERT INTO TB_POSTS_JAVA (conteudo, cpf_usuario) VALUES (?, ?)";

        Connection conn = new ConexaoFactory().cn();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, post.getConteudo());
        stmt.setString(2, post.getCpfUsuario());

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }


    public ArrayList<Posts> buscarTodosPosts() throws SQLException {
        String sql = "SELECT p.id_post, p.conteudo, p.cpf_usuario, u.nome " +
                "FROM TB_POSTS_JAVA p " +
                "INNER JOIN TB_USUARIOS_JAVA u ON p.cpf_usuario = u.cpf";

        ArrayList<Posts> listaPosts = new ArrayList<>();

        Connection conn = new ConexaoFactory().cn();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Posts post = new Posts();
            post.setIdPost(rs.getInt("id_post"));
            post.setConteudo(rs.getString("conteudo"));
            post.setCpfUsuario(rs.getString("cpf_usuario"));

            post.setNomeAutor(rs.getString("nome"));

            listaPosts.add(post);
        }

        rs.close();
        stmt.close();
        conn.close();

        return listaPosts;
    }
}