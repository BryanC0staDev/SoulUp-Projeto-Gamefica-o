package br.com.SoulUp.bo;

import br.com.SoulUp.dao.PostsDao;
import br.com.SoulUp.entities.usuario.Posts;

import java.sql.SQLException;
import java.util.ArrayList;

public class PostsBo {

    private static final int LIMITE_CARACTERES = 280;

    // Inserir
    public void inserirBo(Posts post) throws SQLException {
        PostsDao postsDao = new PostsDao();


        if (post == null) {
            throw new IllegalArgumentException("Post não pode ser nulo.");
        }
        if (post.getConteudo() == null || post.getConteudo().trim().isEmpty()) {
            throw new IllegalArgumentException("O post não pode ser vazio.");
        }
        if (post.getConteudo().length() > LIMITE_CARACTERES) {
            throw new IllegalArgumentException("O post pode ter no máximo " + LIMITE_CARACTERES + " caracteres.");
        }

        postsDao.inserir(post);
    }

    // Buscar todos os posts
    public ArrayList<Posts> buscarTodosPostsBo() throws SQLException {
        PostsDao postsDao = new PostsDao();

        return postsDao.buscarTodosPosts();
    }
}