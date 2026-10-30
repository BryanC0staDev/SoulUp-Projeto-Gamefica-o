package br.com.SoulUp.dao;

import br.com.SoulUp.conexoes.ConexaoFactory;
import br.com.SoulUp.api.Endereco;
import br.com.SoulUp.entities.usuario.Usuarios;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDao {
    Connection connection;

    public UsuarioDao()  {
        this.connection = new ConexaoFactory().cn();
    }

    public void inserir(Usuarios usuarios) throws SQLException {
        PreparedStatement stmt =
                connection.prepareStatement(
                        "INSERT INTO TB_USUARIOS_JAVA (cpf, nome, apelido, email, senha, sexo, idade, " +
                                "cep, rua, numero, bairro, cidade, estado) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1, usuarios.getCpf());
        stmt.setString(2, usuarios.getNome());
        stmt.setString(3, usuarios.getApelido());
        stmt.setString(4, usuarios.getEmail());
        stmt.setString(5, usuarios.getSenha());
        stmt.setString(6, usuarios.getSexo());
        stmt.setInt(7, usuarios.getIdade());
        stmt.setString(8, usuarios.getEndereco().getCep());
        stmt.setString(9, usuarios.getEndereco().getLogradouro());
        stmt.setString(10, usuarios.getEndereco().getNumero());
        stmt.setString(11, usuarios.getEndereco().getBairro());
        stmt.setString(12, usuarios.getEndereco().getLocalidade());
        stmt.setString(13, usuarios.getEndereco().getEstado());
        stmt.execute();
    }


    //validação do Login
    public Usuarios autenticar(String email, String senha) throws SQLException{
        String sql = "SELECT * FROM TB_USUARIOS_JAVA WHERE email = ? AND senha = ?";
        Usuarios usuarios = null;

        Connection conn = new ConexaoFactory().cn();
        PreparedStatement stmt = conn.prepareStatement(sql);

        String emailLimpo = (email != null) ? email.trim() : "";
        String senhaLimpa = (senha != null) ? senha.trim() : "";

        stmt.setString(1, emailLimpo);
        stmt.setString(2, senhaLimpa);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            usuarios = new Usuarios();
            usuarios.setNome(rs.getString("nome"));
            usuarios.setApelido(rs.getString("apelido"));
            usuarios.setCpf(rs.getString("cpf"));
            usuarios.setEmail(rs.getString("email"));
            usuarios.setSenha(rs.getString("senha"));
            usuarios.setSexo(rs.getString("sexo"));
            usuarios.setIdade(rs.getInt("idade"));

            usuarios.setPontos(rs.getInt("pontos"));
            usuarios.setCurtidas(rs.getInt("curtidas"));
            usuarios.setQntPosts(rs.getInt("qnt_posts"));
            usuarios.setSeguidores(rs.getInt("seguidores"));
            usuarios.setSeguindo(rs.getInt("seguindo"));

            Endereco endereco = new Endereco();
            endereco.setCep(rs.getString("cep"));
            endereco.setLogradouro(rs.getString("rua"));
            endereco.setNumero(rs.getString("numero"));
            endereco.setBairro(rs.getString("bairro"));
            endereco.setLocalidade(rs.getString("cidade"));
            endereco.setEstado(rs.getString("estado"));

            usuarios.setEndereco(endereco);
        } else {
            JOptionPane.showMessageDialog(null, "Email ou senha incorretos. Tente novamente.", "Acesso Negado", JOptionPane.ERROR_MESSAGE);
        }

        rs.close();
        stmt.close();
        conn.close();

        return usuarios;
    }

    //Update
    public void atualizar(Usuarios usuarios) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE TB_USUARIOS_JAVA SET nome = ?, apelido = ?, email = ?, senha = ?, sexo = ?, idade = ?, " +
                "cep = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ?, " +
                "curtidas = ?, qnt_posts = ?, seguidores = ?, seguindo = ? ,pontos = ? WHERE cpf = ?";

        Connection conn = new ConexaoFactory().cn();
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, usuarios.getNome());
        stmt.setString(2, usuarios.getApelido());
        stmt.setString(3, usuarios.getEmail());
        stmt.setString(4, usuarios.getSenha());
        stmt.setString(5, usuarios.getSexo());
        stmt.setInt(6, usuarios.getIdade());

        stmt.setString(7, usuarios.getEndereco().getCep());
        stmt.setString(8, usuarios.getEndereco().getLogradouro());
        stmt.setString(9, usuarios.getEndereco().getNumero());
        stmt.setString(10, usuarios.getEndereco().getBairro());
        stmt.setString(11, usuarios.getEndereco().getLocalidade());
        stmt.setString(12, usuarios.getEndereco().getEstado());

        //Interações do Usuario

        stmt.setInt(13, usuarios.getCurtidas());
        stmt.setInt(14, usuarios.getQntPosts());
        stmt.setInt(15, usuarios.getSeguidores());
        stmt.setInt(16, usuarios.getSeguindo());
        stmt.setInt(17, usuarios.getPontos());

        // Identificador único
        stmt.setString(18   , usuarios.getCpf());

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    //Pegando Dados para buscar nomes dos Usuarios

    public ArrayList<String> buscarTodosNomes() throws SQLException, ClassNotFoundException {
        String sql = "SELECT nome FROM TB_USUARIOS_JAVA";
        ArrayList<String> listaNomes = new ArrayList<>();

        Connection conn = new ConexaoFactory().cn();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            listaNomes.add(rs.getString("nome"));
        }

        rs.close();
        stmt.close();
        conn.close();

        return listaNomes;
    }


    public void incrementarCurtidas(String cpf) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE TB_USUARIOS_JAVA SET curtidas = curtidas + 1 WHERE cpf = ?";
        Connection conn = new ConexaoFactory().cn();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public void incrementarPosts(String cpf) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE TB_USUARIOS_JAVA SET qnt_posts = qnt_posts + 1 WHERE cpf = ?";
        Connection conn = new ConexaoFactory().cn();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    public void incrementarSeguindo(String cpf) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE TB_USUARIOS_JAVA SET seguindo = seguindo + 1 WHERE cpf = ?";
        Connection conn = new ConexaoFactory().cn();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    public void incrementarSeguidor(String nomeUsuario) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE TB_USUARIOS_JAVA SET seguidores = seguidores + 1 WHERE nome = ?";
        Connection conn = new ConexaoFactory().cn();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nomeUsuario);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    public void excluir(String cpf) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM TB_USUARIOS_JAVA WHERE cpf = ?";
        Connection conn = new ConexaoFactory().cn();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpf);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
    public ArrayList<Usuarios> buscarRankingUsuarios() throws SQLException  {
        String sql = "SELECT * FROM TB_USUARIOS_JAVA ORDER BY pontos DESC";
        ArrayList<Usuarios> ranking = new ArrayList<>();

        Connection conn = new ConexaoFactory().cn();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Usuarios u = new Usuarios();

                u.setNome(rs.getString("nome"));
                u.setApelido(rs.getString("apelido"));
                u.setCpf(rs.getString("cpf"));
                u.setEmail(rs.getString("email"));
                u.setSexo(rs.getString("sexo"));
                u.setIdade(rs.getInt("idade"));

                u.setPontos(rs.getInt("pontos"));
                u.setCurtidas(rs.getInt("curtidas"));
                u.setQntPosts(rs.getInt("qnt_posts"));
                u.setSeguidores(rs.getInt("seguidores"));
                u.setSeguindo(rs.getInt("seguindo"));

                ranking.add(u);
            }


        rs.close();
        stmt.close();
        conn.close();
        return ranking;
    }




}