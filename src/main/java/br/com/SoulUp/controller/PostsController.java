package br.com.SoulUp.controller;

import br.com.SoulUp.bo.PostsBo;
import br.com.SoulUp.entities.usuario.Posts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/posts")
public class PostsController {

    PostsBo postsBo = new PostsBo();

    // Buscar todos
    @GetMapping
    public ArrayList<Posts> buscarTodosPostsRs() throws SQLException {
        return postsBo.buscarTodosPostsBo();
    }

    // Inserir
    @PostMapping
    public ResponseEntity<Void> inserirRs(@RequestBody Posts post) throws SQLException {
        postsBo.inserirBo(post);
        return ResponseEntity.status(201).build();
    }
}