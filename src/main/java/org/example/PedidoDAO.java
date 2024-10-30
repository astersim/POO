package org.example;

import java.sql.*;

public class PedidoDAO {
    private static final String URL = "jdbc:sqlite:produtos.db";

    public PedidoDAO() {
        try(Connection connection = DriverManager.getConnection(URL)) {
            String sqlCreateTable = "CREATE TABLE IF NOT EXIST pedido("
                    + "id INTERGER PRIMARY KEY AUTOINCREMENT,"
                    + "numero INTERGER NOT NULL,"
                    + "idUsuario INTERGET NOT NULL,"
                    + "total REAL NOT NULL,"
                    + "status TEXT NOT NULL,"
                    + "FOREIGN KEY(idUsuario) REFERENCES usuario(id)";
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreateTable);
        }  catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }
}
