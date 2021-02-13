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
 *
 * @author pedro-menezes
 */
public class ConnectionFactory {

    public Connection getConnection() {
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             return DriverManager.getConnection("jdbc:mysql://localhost:3306/gerenciamentoUsuario", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        } 
   }
}