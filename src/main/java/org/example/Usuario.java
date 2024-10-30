package org.example;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String endereco;
    private String telefone;
    private String profissao;

    public Usuario(int id, String nome, String email, String endereco, String telefone, String profissao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.profissao = profissao;
    }

    public Usuario(String nome, String email, String endereco, String telefone, String profissao) {
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.profissao = profissao;
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
