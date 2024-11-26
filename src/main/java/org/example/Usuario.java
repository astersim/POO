package org.example;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private String profissao;
    private int status;

    public Usuario(int id, String nome, String email, String endereco, String telefone, String profissao, int status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.profissao = profissao;
        this.status = status;
    }

    public Usuario(String nome, String email, String endereco, String telefone, String profissao, int status) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.profissao = profissao;
        this.status = 1;
    }



    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getProfissao() {
        return profissao;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                ", profissao='" + profissao + '\'' +
                '}';
    }

    
}
