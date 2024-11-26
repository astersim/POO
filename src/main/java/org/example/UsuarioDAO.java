package org.example;

import java.sql.*;

public class UsuarioDAO {
    private static final String URL = "jdbc:sqlite:produtos.db";

    public UsuarioDAO() {
        try(Connection connection = DriverManager.getConnection(URL)) {
            String sqlCreateTable = "CREATE TABLE IF NOT EXIST usuario("
                    + "id INTERGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT NOT NULL,"
                    + "email TEXT NOT NULL,"
                    + "endereco TEXT NOT NULL,"
                    + "telefone VARCHAR(11) NOT NULL,"
                    + "profissao TEXT NOT NULL";
            Statement stmt = connection.createStatement();
            stmt.execute(sqlCreateTable);

            String sqlAddColumn = "ALTER TABLE usuario ADD COLUMN status INTERGER DEFAULT 1";
            try {
                stmt.execute(sqlAddColumn);
            } catch (SQLException e) {
                if (!e.getMessage().contains("duplicate column name: status")) {
                    throw e;
                }
            }
        }  catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public void cadastrarUsuario(String nome, String email, String endereco, String telefone, String profissao) {
        String sqlInsert = "INSERT INTO usuario(nome, email, endereco, telefone, profissao) VALUES(?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setString(3, endereco);
            pstmt.setString(4, telefone);
            pstmt.setString(5, profissao);
            pstmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public void alterarUsuario(int id, String email, String endereco, String telefone) {
        String sqlUpdate = "UPDATE usuario SET email = ?, endereco = ?, telefone = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            pstmt.setString(1, email);
            pstmt.setString(2, endereco);
            pstmt.setString(3, telefone);
            pstmt.setInt(4, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário alterado com sucesso!");
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao alterar usuário: " + e.getMessage());
        }
    }

    public Usuario consultarUsuario(int id) {
        String sqlSelect = "SELECT * FROM usuario WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int status = rs.getInt("status");
                if (status == 0) {
                    System.out.println("Usuário inativo");
                    return null;
                }
                return new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("endereco"), rs.getString("telefone"), rs.getString("profissao"), status);
            } else {
                System.out.println("Usuário não encontrado");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar usuário: " + e.getMessage());
        }
        return null;
    }

    public void inativarUsuario(int id) {
        String sqlUpdate = "UPDATE usuario SET status = 0 WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário inativado com sucesso!");
            } else {
                System.out.println("Usuário não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inativar usuário: " + e.getMessage());
        }
    }
}



