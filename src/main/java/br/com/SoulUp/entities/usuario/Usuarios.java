package br.com.SoulUp.entities.usuario;

import br.com.SoulUp.api.Endereco;
import br.com.SoulUp.dao.UsuarioDao;

import javax.swing.*;
import java.sql.SQLException;

public class Usuarios {


    static void mensagem(String parametro, String mensagem, String titulo, int informacao) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, informacao);
    }

    private String senha;
    private String nome;
    private String cpf;
    private String apelido;
    private String email;
    private String sexo;
    private int curtidas;
    private int qntPosts;
    private int seguidores;
    private int seguindo;
    private int idade;
    private  int pontos;

    Endereco endereco;


    public Usuarios(String nome, String cpf, String senha, String apelido,
                    String email, String sexo, int curtidas,
                    int qntPosts, int seguidores, int seguindo,
                    int idade, int pontos) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
        this.apelido = apelido;
        this.email = email;
        this.sexo = sexo;
        this.curtidas = curtidas;
        this.qntPosts = qntPosts;
        this.seguidores = seguidores;
        this.seguindo = seguindo;
        this.idade = idade;
        this.pontos = pontos;

    }

    public Usuarios() {
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {

        return nome;
    }


    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(int curtidas) {
        this.curtidas = curtidas;
    }

    public int getQntPosts() {
        return qntPosts;
    }

    public void setQntPosts(int qntPosts) {
        this.qntPosts = qntPosts;
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    public int getSeguindo() {
        return seguindo;
    }

    public void setSeguindo(int seguindo) {
        this.seguindo = seguindo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }






    public void cadastro(Endereco endereco){

                JOptionPane.showMessageDialog(
                null,
                "Bem Vindo à SoulUP\n\nVamos Começar seu Cadastro!",
                null,
                JOptionPane.INFORMATION_MESSAGE
                );

                this.nome = JOptionPane.showInputDialog("Digite o seu Nome:");
                this.senha = JOptionPane.showInputDialog("Digite sua Senha");
                this.cpf = JOptionPane.showInputDialog("Digite o seu CPF:");
                this.apelido = JOptionPane.showInputDialog("Digite o seu Apelido:");
                this.email = JOptionPane.showInputDialog("Digite o seu email: ");
                this.sexo = JOptionPane.showInputDialog("Digite o seu Sexo:");
                this.idade = Integer.parseInt(JOptionPane.showInputDialog("Digite sua Idade"));

                endereco = new Endereco();
                endereco.cadastroEndereco();

                this.endereco = endereco;





    }

    public void alterarCadastro(Usuarios usuarios) throws SQLException, ClassNotFoundException {

        //criação da variavel Resposta
        String resposta = "";
        UsuarioDao dao = new UsuarioDao();

        //Repetição do Programa!
        while (!resposta.equals("x")) {
            JOptionPane.showMessageDialog(null, "APERTE X PARA SAIR/APERTE X PARA SAIR\n" + usuarios,
                    "Alterar Dados",
                    JOptionPane.INFORMATION_MESSAGE);

            //Captando Resposta do Usuario;
            String input = JOptionPane.showInputDialog("Qual dado vc deseja alterar?");

            if (input == null) {
                return;
            }

            resposta = input.toLowerCase().trim();

            //Tratando alguns possiveis erros;
            if (resposta.isEmpty()) {
                mensagem(null, "Informação nao encontrada, Tente Novamente", "Aviso", JOptionPane.WARNING_MESSAGE);
                continue;
            } else if (resposta.equals("x")) {
                mensagem(null, "Voltando ao Menu!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            //Criando Funcao de alterar Dados;
            switch (resposta) {
                case "nome" -> {
                    String valor = JOptionPane.showInputDialog("Digite o novo NOME:");
                    if (valor != null && !valor.trim().isEmpty()) {
                        usuarios.setNome(valor);
                        dao.atualizar(usuarios);
                        mensagem(null, "Nome alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "cpf" -> {
                    String valor = JOptionPane.showInputDialog("Digite o novo CPF:");
                    if (valor != null && !valor.trim().isEmpty()) {
                        usuarios.setCpf(valor);
                        dao.atualizar(usuarios);
                        mensagem(null, "CPF alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "apelido" -> {
                    String valor = JOptionPane.showInputDialog("Digite o novo APELIDO:");
                    if (valor != null && !valor.trim().isEmpty()) {
                        usuarios.setApelido(valor);
                        dao.atualizar(usuarios);
                        mensagem(null, "Apelido alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "email", "e-mail" -> {
                    String valor = JOptionPane.showInputDialog("Digite o novo EMAIL:");
                    if (valor != null && !valor.trim().isEmpty()) {
                        usuarios.setEmail(valor);
                        dao.atualizar(usuarios);
                        mensagem(null, "E-mail alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "sexo" -> {
                    String valor = JOptionPane.showInputDialog("Digite o novo SEXO:");
                    if (valor != null && !valor.trim().isEmpty()) {
                        usuarios.setSexo(valor);
                        dao.atualizar(usuarios);
                        mensagem(null, "Sexo alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "idade" -> {
                    String valor = JOptionPane.showInputDialog("Digite a nova IDADE (Apenas números):");
                    if (valor != null && !valor.trim().isEmpty()) {
                        try {
                            usuarios.setIdade(Integer.parseInt(valor.trim()));
                            dao.atualizar(usuarios);
                            mensagem(null, "Idade alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } catch (NumberFormatException e) {
                            mensagem(null, "Idade inválida! Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
                //Dados do Endereço
                case "cep" -> {
                    String valor = JOptionPane.showInputDialog("Digite o novo CEP:");
                    if (valor != null && !valor.trim().isEmpty()) {
                        usuarios.getEndereco().setCep(valor);
                        dao.atualizar(usuarios);
                        mensagem(null, "CEP alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "rua", "logradouro" -> {
                    String valor = JOptionPane.showInputDialog("Digite a nova RUA:");
                    if (valor != null && !valor.trim().isEmpty()) {
                        usuarios.getEndereco().setLocalidade(valor);
                        dao.atualizar(usuarios);
                        mensagem(null, "Rua alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "numero", "número" -> {
                    String valor = JOptionPane.showInputDialog("Digite o novo NÚMERO:");
                    if (valor != null && !valor.trim().isEmpty()) {
                        usuarios.getEndereco().setNumero(valor);
                        dao.atualizar(usuarios);
                        mensagem(null, "Número alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "bairro" -> {
                    String valor = JOptionPane.showInputDialog("Digite o novo BAIRRO:");
                    if (valor != null && !valor.trim().isEmpty()) {
                        usuarios.getEndereco().setBairro(valor);
                        dao.atualizar(usuarios);
                        mensagem(null, "Bairro alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "localidade" -> {
                    String valor = JOptionPane.showInputDialog("Digite a nova CIDADE:");
                    if (valor != null && !valor.trim().isEmpty()) {
                        usuarios.getEndereco().setLocalidade(valor);
                        dao.atualizar(usuarios);
                        mensagem(null, "Cidade alterada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "estado", "uf" -> {
                    String valor = JOptionPane.showInputDialog("Digite o novo ESTADO:");
                    if (valor != null && !valor.trim().isEmpty()) {
                        usuarios.getEndereco().setEstado(valor);
                        dao.atualizar(usuarios);
                        mensagem(null, "Estado alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case "x" -> {
                    return;
                }

                // Para Opção Invalida
                default -> mensagem(null, "Campo '" + resposta + "' não encontrado ou inválido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }

    }













    @Override
    public String toString() {
        return "\n\nUsuario" +
                "\nnome = " + nome +
                "\ncpf = " + cpf +
                "\napelido = " + apelido +
                "\nemail = " + email +
                "\nsexo = " + sexo +
                "\nidade = " + idade +
                endereco ;
    }
}


