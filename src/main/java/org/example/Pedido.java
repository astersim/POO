package org.example;

public class Pedido {
    private int id;
    private int numero;
    private int idUsuario;
    private double total;
    private String status;

    public Pedido(int id, int numero, int idUsuario, double total, String status) {
        this.id = id;
        this.numero = numero;
        this.idUsuario = idUsuario;
        this.total = total;
        this.status = status;
    }

    public Pedido(int numero, int idUsuario, double total, String status) {
        this.numero = numero;
        this.idUsuario = idUsuario;
        this.total = total;
        this.status = status;
    }

    public int getNumero() {
        return numero;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", numero=" + numero +
                ", idUsuario=" + idUsuario +
                ", total=" + total +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() { return id;
    }
}
