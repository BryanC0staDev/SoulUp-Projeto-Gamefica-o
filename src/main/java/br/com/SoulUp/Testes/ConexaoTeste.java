package br.com.SoulUp.Testes;

import br.com.SoulUp.conexoes.ConexaoFactory;

import java.sql.Connection;

public class ConexaoTeste {

    public static void main(String[] args) {
        try{
        Connection cn  = new ConexaoFactory().cn();
            if ( cn != null ) {
                System.out.println("Conectado com sucesso");

            }else {
                System.out.println("Falha a se conctar");
            }

            cn.close();

        }catch (Exception e){
            e.printStackTrace();

        }

    }

}
