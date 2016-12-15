package com.example.denesleal.precificacao;

/**
 * Created by User on 14/12/2016.
 */

public class Orcamento implements java.io.Serializable {

    private int id;
    private String nome;
    private String senttoemail;
    private int total;

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

    public String getSenttoemail() {
        return senttoemail;
    }

    public void setSenttoemail(String senttoemail) {
        this.senttoemail = senttoemail;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
