package br.com.SoulUp.Testes;

import br.com.SoulUp.api.Endereco;

public class testesMain {
    public static void main(String[] args) {
        Endereco endereco = new Endereco();

        endereco.cadastroEndereco();
        System.out.println(endereco);

    }


}
