package br.com.SoulUp.bo;

import br.com.SoulUp.dao.UsuarioDao;
import br.com.SoulUp.entities.usuario.Usuarios;

import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBo {
    //Validações
    private void validarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 números.");
        }
    }

    private void validarUsuario(Usuarios u) {
        if (u == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo.");
        }
        validarCpf(u.getCpf());
        if (u.getNome() == null || u.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (u.getEmail() == null || !u.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email inválido.");
        }
        if (u.getSenha() == null || u.getSenha().length() < 6) {
            throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres.");
        }
        if (u.getIdade() <= 0) {
            throw new IllegalArgumentException("Idade inválida.");
        }
        if (u.getEndereco() == null) {
            throw new IllegalArgumentException("Endereço é obrigatório.");
        }
    }


    //Metodos Bo//




    // Inserir
    public void inserirBo(Usuarios usuarios)  {
        try {
            UsuarioDao usuarioDao = new UsuarioDao();

            validarUsuario(usuarios);
            usuarioDao.inserir(usuarios);
        }catch (SQLException e){
            System.out.println("Error ao inserir o usuario: "+e);
        }
    }

    // Login
    public Usuarios autenticarBo(String email, String senha) throws SQLException {
        UsuarioDao usuarioDao = new UsuarioDao();


        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o email.");
        }
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Informe a senha.");
        }
        return usuarioDao.autenticar(email, senha);
    }

    // Atualizar
    public void atualizarBo(Usuarios usuarios) throws SQLException, ClassNotFoundException {
        UsuarioDao usuarioDao = new UsuarioDao();
        validarUsuario(usuarios);
        usuarioDao.atualizar(usuarios);
    }

    // Buscar todos os nomes
    public ArrayList<String> buscarTodosNomesBo() throws SQLException, ClassNotFoundException {
        UsuarioDao usuarioDao = new UsuarioDao();

        return usuarioDao.buscarTodosNomes();
    }

    // Curtidas
    public void incrementarCurtidasBo(String cpf) throws SQLException, ClassNotFoundException {
        UsuarioDao usuarioDao = new UsuarioDao();

        validarCpf(cpf);
        usuarioDao.incrementarCurtidas(cpf);
    }

    // Posts
    public void incrementarPostsBo(String cpf) throws SQLException, ClassNotFoundException {
        UsuarioDao usuarioDao = new UsuarioDao();

        validarCpf(cpf);
        usuarioDao.incrementarPosts(cpf);
    }

    // Seguindo
    public void incrementarSeguindoBo(String cpf) throws SQLException, ClassNotFoundException {
        UsuarioDao usuarioDao = new UsuarioDao();

        validarCpf(cpf);
        usuarioDao.incrementarSeguindo(cpf);
    }

    // Seguidor (busca pelo nome)
    public void incrementarSeguidorBo(String nomeUsuario) throws SQLException, ClassNotFoundException {
        UsuarioDao usuarioDao = new UsuarioDao();

        if (nomeUsuario == null || nomeUsuario.trim().isEmpty()) {
            throw new IllegalArgumentException("Informe o nome do usuário.");
        }
        usuarioDao.incrementarSeguidor(nomeUsuario);
    }

    // Excluir
    public void excluirBo(String cpf) throws SQLException, ClassNotFoundException {
        UsuarioDao usuarioDao = new UsuarioDao();

        validarCpf(cpf);
        usuarioDao.excluir(cpf);
    }

    // Ranking
    public ArrayList<Usuarios> buscarRankingUsuariosBo() throws SQLException, ClassNotFoundException {
        UsuarioDao usuarioDao = new UsuarioDao();

        return usuarioDao.buscarRankingUsuarios();
    }


}