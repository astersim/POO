package org.example;

import java.sql.*;

public class itemPedidoDAO {
    private static final String URL = "jdbc:sqlite:produtos.db";

    public itemPedidoDAO() {
        try(Connection connection = DriverManager.getConnection(URL)) {
            String sqlCreateTable = "CREATE TABLE IF NOT EXIST itemPedido("
                    + "id INTERGER PRIMARY KEY AUTOINCREMENT,"
                    + "idPedido INTERGER NOT NULL,"
                    + "idUsuario INTERGET NOT NULL,"
                    + "descricao TEXT NOT NULL,"
                    + "quantidade INTERGET NOT NULL,"
                    + "precoUnitario REAL NOT NULL,"
                    + "FOREIFN KEY(idPedido) REFERENCES pedido(id),"
                    + "FOREIGN KEY(idUsuario) REFERENCES usuario(id)";
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreateTable);
        }  catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }
}
