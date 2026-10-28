package br.com.SoulUp.api;

import br.com.SoulUp.services.ViaCepAPI;

import javax.swing.*;

public class Endereco {
    private String cep;
    private String logradouro;
    private String numero;
    private String bairro;
    private String localidade;
    private String estado;

    ViaCepAPI viaCepAPI = new ViaCepAPI();

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Endereco() {
    }

    public Endereco(String cep, String logradouro, String numero, String bairro, String localidade, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.localidade = localidade;
        this.estado = estado;
    }

    public void cadastroEndereco() {
        String cep = JOptionPane.showInputDialog("Digite seu CEP:");
        this.cep = cep;
        this.logradouro = viaCepAPI.getEndereco(cep).logradouro;
        this.bairro = viaCepAPI.getEndereco(cep).bairro;
        this.estado = viaCepAPI.getEndereco(cep).estado;
        this.localidade = viaCepAPI.getEndereco(cep).getLocalidade();
        this.numero = JOptionPane.showInputDialog("Digite o número:");

    }


    @Override
    public String toString() {

        return  "\n\nEndereco: " +
                "\nCEP: " + cep +
                "\nLogradouro: " + logradouro+
                "\nNúmero: " + numero +
                "\nBairro: " + bairro +
                "\nCidade: " + localidade +
                "\nEstado: " + estado;
    }



}
