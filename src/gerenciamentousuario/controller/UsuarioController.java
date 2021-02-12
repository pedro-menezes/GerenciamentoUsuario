/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario.controller;

import gerenciamentousuario.connection.ConnectionFactory;
import gerenciamentousuario.model.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pedro-menezes
 */
public class UsuarioController {
    
    // a conexão com o banco de dados
    private Connection con;

    public UsuarioController() {
        //inicializa a conexão com o BD
        this.con = new ConnectionFactory().getConnection();
    }

    public void adiciona(Usuario contato) {
        String sql = "insert into usuario (usuCpf,usuNome,usuSexo,usuDateNasc,usu_carNome) values (?,?,?,?,?)";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);

            // seta os valores
            stmt.setString(1, contato.getCpf());
            stmt.setString(2, contato.getNome());
            stmt.setString(3, String.valueOf(contato.getSexo()));
            stmt.setDate(4, contato.getDataNascimento());
            stmt.setString(5, "Desenvolvedor");
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleta(int codigo) {
        String sql = "delete from pessoas where pesCodigo = ?;";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);

            // seta os valores
            stmt.setInt(1, codigo);

            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Usuario> getPessoas() {
        String sql = "select * from pessoas;";
        ArrayList<Usuario> a = new ArrayList();
        try {
            // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);

            // executa
            ResultSet rs = stmt.executeQuery();
            //joga resultado da consulta no ArrayList
            while (rs.next()) {
     //           Usuario x = new Usuario();
                // x.setCodigo(rs.getInt(1));
                // x.setNome(rs.getString(2));
                // x.setTelefone(rs.getString(3));
         //       a.add(x);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return a;
    }
}
