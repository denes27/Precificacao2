package com.example.denesleal.precificacao;

/**
 * Created by User on 14/12/2016.
 */

public class Produtos implements java.io.Serializable {
    private int id;
    private String nome;
    private int preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}
