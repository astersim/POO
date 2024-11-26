package org.example;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

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

    public void cadastrarPedido(Pedido pedido) {
        String sqlInsert = "INSERT INTO pedido(numero, isUsuario, total, status) VALUES(? ? ? ?)";
        try (Connection connection = DriverManager.getConnection(URL)) {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setInt(1, pedido.getNumero());
            preparedStatement.setInt(2, pedido.getIdUsuario());
            preparedStatement.setDouble(3, pedido.getTotal());
            preparedStatement.setString(4, pedido.getStatus());
            preparedStatement.executeUpdate();
            System.out.println("Pedido cadastrado");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pedido" + e.getMessage());
        }
    }

    public void alterarStatusPedido(int id, String status) {
        String sqlUpdate = "UPDATE pedido SET status = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Status alterado");
        } catch (SQLException e) {
            System.out.println("Erro ao alterar status" + e.getMessage());
        }
    }

    public List<Map<String, Object>> consultarPedido(int id) {
        String sqlSelect = "SELECT p.id, pr.nome, ip.quantidade, (ip.quantidade * ip.precoUnitario) AS valorTotal " +
                "FROM pedido p " +
                "JOIN itemPedido ip ON p.id = ip.idPedido " +
                "JOIN produto pr ON ip.idProduto = pr.id " +
                "WHERE p.id = ?";
        List<Map<String, Object>> result = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", resultSet.getInt("id"));
                row.put("nome", resultSet.getString("nome"));
                row.put("quantidade", resultSet.getInt("quantidade"));
                row.put("valorTotal", resultSet.getDouble("valorTotal"));
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar pedido: " + e.getMessage());
        }
        return result;
    }
}
