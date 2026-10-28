package br.com.SoulUp.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String CONEXAO = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USUARIO = "RM569439";
    private static final String SENHA = "100408";

    public Connection cn() {

        try {
            Class.forName(DRIVER);

            return DriverManager.getConnection(CONEXAO, USUARIO, SENHA);



        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao encontrar o driver do banco de dados!");


        } catch (SQLException e) {
            System.out.println("Erro ao acessar o Banco de Dados. Verifique URL, usuário e senha.");

        }catch (Exception e){
            System.out.println("Erro Desconhecido");
            e.printStackTrace();
        }

        return null;
    }
}