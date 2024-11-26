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
                    + "FOREIGN KEY(idPedido) REFERENCES pedido(id),"
                    + "FOREIGN KEY(idUsuario) REFERENCES usuario(id)";
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreateTable);
        }  catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public static void cadastrarItemPedido(itemPedido itemPedido) {
        String sqlInsert = "INSERT INTO itemPedido(idPedido, idUsuario, descricao, quantidade, precoUnitario) VALUES(? ? ? ? ?)";
        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
            preparedStatement.setInt(1, itemPedido.getIdPedido());
            preparedStatement.setInt(2, itemPedido.getIdUsuario());
            preparedStatement.setString(3, itemPedido.getDescricao());
            preparedStatement.setInt(4, itemPedido.getQuantidade());
            preparedStatement.setDouble(5, itemPedido.getPrecoUnitario());
            System.out.println("ItemPedido cadastrado");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar itemPedido" + e.getMessage());
        }
    }

    public void alterarQuantidadeItemPedido(int id, int quantidade) {
        String sqlUpdate = "UPDATE itemPedido SET quantidade = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlUpdate)) {
            preparedStatement.setInt(1, quantidade);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Quantidade alterada");
        } catch (SQLException e) {
            System.out.println("Erro ao alterar quantidade" + e.getMessage());
        }
    }

    public void deletarItemPedido(int id) {
        String sqlDelete = "DELETE FROM itemPedido WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlDelete)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("ItemPedido deletado com sucesso!");
            } else {
                System.out.println("ItemPedido não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar itemPedido: " + e.getMessage());
        }
    }

    public itemPedido consultarItemPedido(int id) {
        String sqlSelect = "SELECT * FROM itemPedido WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new itemPedido(
                        resultSet.getInt("id"),
                        resultSet.getInt("idPedido"),
                        resultSet.getInt("idUsuario"),
                        resultSet.getString("descricao"),
                        resultSet.getInt("quantidade"),
                        resultSet.getDouble("precoUnitario")
                );
            } else {
                System.out.println("ItemPedido não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar itemPedido: " + e.getMessage());
        }
        return null;
    }
}
