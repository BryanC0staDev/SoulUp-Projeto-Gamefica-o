package br.com.SoulUp.controller;

import br.com.SoulUp.bo.UsuarioBo;
import br.com.SoulUp.entities.usuario.Usuarios;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    UsuarioBo usuarioBo = new UsuarioBo();

    // Inserir
    @PostMapping
    public ResponseEntity<Void> inserirRs(@RequestBody Usuarios usuarios) throws SQLException {
        usuarioBo.inserirBo(usuarios);

        URI location = URI.create("/usuarios/" + usuarios.getCpf());
        return ResponseEntity.created(location).build();
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<Usuarios> autenticarRs(@RequestBody Map<String, String> credenciais) throws SQLException {
        Usuarios usuario = usuarioBo.autenticarBo(credenciais.get("email"), credenciais.get("senha"));

        if (usuario == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(usuario);
    }

    // Atualizar
    @PutMapping
    public ResponseEntity<Void> atualizarRs(@RequestBody Usuarios usuarios) throws SQLException, ClassNotFoundException {
        usuarioBo.atualizarBo(usuarios);
        return ResponseEntity.ok().build();
    }

    // Buscar todos os nomes
    @GetMapping("/nomes")
    public ArrayList<String> buscarTodosNomesRs() throws SQLException, ClassNotFoundException {
        return usuarioBo.buscarTodosNomesBo();
    }

    // Ranking
    @GetMapping("/ranking")
    public ArrayList<Usuarios> buscarRankingRs() throws SQLException, ClassNotFoundException {

        return usuarioBo.buscarRankingUsuariosBo();
    }

    // Excluir
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> excluirRs(@PathVariable String cpf) throws SQLException, ClassNotFoundException {
        usuarioBo.excluirBo(cpf);
        return ResponseEntity.ok().build();
    }
}