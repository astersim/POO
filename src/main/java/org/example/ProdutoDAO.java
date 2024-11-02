package org.example;
import java.sql.*;

public class ProdutoDAO {
    private static final String URL = "jdbc:sqlite:produtos.db";

    public ProdutoDAO() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS produto ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT NOT NULL,"
                    + "preco REAL NOT NULL)";
            Statement stmt = conn.createStatement();
            stmt.execute(sqlCreateTable);

            String sqlAddColumn = "ALTER TABLE produto ADD COLUMN status INTERGER DEFAULT 1";
            try {
                stmt.execute(sqlAddColumn);
            } catch (SQLException e) {
                if (!e.getMessage().contains("duplicate column name: status")) {
                    throw e;
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public void cadastrarProduto(Produto produto) {
        String sqlInsert = "INSERT INTO produto(nome, preco) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setDouble(2, produto.getPreco());
            pstmt.executeUpdate();
            System.out.println("Produto cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    public Produto consultarProduto(int id) {
        String sqlSelect = "SELECT * FROM produto WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int status = rs.getInt("status");

                if(status == 0) {
                    System.out.println("Produto Inativo");
                    return null;
                }

                return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), status);
            } else {
                System.out.println("Produto nao encontrado");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar produto: " + e.getMessage());
        }
        return null;
    }

    public void desativarProduto(int id) {
        String sqlUpdate = "UPDATE produto SET status = 0 WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produto desativado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao desativar produto: " + e.getMessage());
        }
    }
}