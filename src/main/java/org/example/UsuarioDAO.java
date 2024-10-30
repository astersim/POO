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
        }  catch (SQLException e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }
    
    public void cadastrarUsuario(Usuario usuario) {
        String sqlInsert = "INSERT INTO usuario(nome, email, endereco, telefone, profissao) VALUES(? ? ? ? ?)";
        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert)) {
            preparedStatement.setString(1, usuario.getNome());
            preparedStatement.setString(2, usuario.getEmail());
            preparedStatement.setString(3, usuario.getEndereco());
            preparedStatement.setString(4, usuario.getTelefone());
            preparedStatement.setString(5, usuario.getTelefone());
            System.out.println("Usuario cadastrado");
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuario" + e.getMessage());
        }
    }

    public Usuario consultarUsuario(int id) {
        String sqlSelect = "SELECT * FROM usuario WHERE id = ?";
        try(Connection connection = DriverManager.getConnection(URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlSelect)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return new Usuario(resultSet.getInt("id"), resultSet.getString("nome"), resultSet.getString("email"), resultSet.getString("endereco"), resultSet.getString("telefone"), resultSet.getString("profisso"));
            }
        }catch (SQLException e) {
            System.out.println("Erro ao consultar" + e.getMessage());
        }
        return null;
    }

}
