package org.example;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int status;

    public Produto(int id, String nome, double preco, int status) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.status = status;
    }

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
        this.status = 1;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getStatus() {return status;}

    @Override
    public String toString() {
        return "Produto [ID=" + id + ", Nome=" + nome + ", Preço=" + preco + "]";
    }
}
