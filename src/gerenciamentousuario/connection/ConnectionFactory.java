/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável pelo conexão do sistema com o banco de dados através da 
 * biblioteca mysql-connector-java-8.0.22.jar.
 * @author pedro-menezes
 */
public class ConnectionFactory {
    /**
     * Método que retorna informações sobre a conexão: nome do banco, usuário e senha.
     * @return Connection - informações necessárias pra conexão.
     */
    public Connection getConnection() {
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             return DriverManager.getConnection("jdbc:mysql://localhost:3306/gerenciamentoUsuario", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } 
   }
}