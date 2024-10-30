package org.example;

public class itemPedido {
    private int id;
    private int idPedido;
    private int idProduto;
    private String descricao;
    private int quantidade;
    private double precoUnitario;

    public itemPedido(int id, int idPedido, int idProduto, String descricao, int quantidade, double precoUnitario) {
        this.id = id;
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public itemPedido(int idPedido, int idProduto, String descricao, int quantidade, double precoUnitario) {
        this.idPedido = idPedido;
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    @Override
    public String toString() {
        return "itemPedido{" +
                "id=" + id +
                ", idPedido=" + idPedido +
                ", idProduto=" + idProduto +
                ", descricao='" + descricao + '\'' +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                '}';
    }
}
