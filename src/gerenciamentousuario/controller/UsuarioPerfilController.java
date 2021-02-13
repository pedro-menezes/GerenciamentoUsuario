/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario.controller;

import gerenciamentousuario.connection.ConnectionFactory;
import gerenciamentousuario.model.Perfil;
import gerenciamentousuario.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pedro-menezes
 */
public class UsuarioPerfilController {
    private Connection con;

    public UsuarioPerfilController() {
        //inicializa a conexão com o BD
        this.con = new ConnectionFactory().getConnection();
    }

    public void adiciona(Perfil perfil, Usuario usuario) {
        String sql = "insert into usuario_perfil (usp_usuCpf,usp_perNome) values (?,?)";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);

            // seta os valores
            System.out.println("FOI");
            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, perfil.getNome());
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleta(String nome) {
        String sql = "delete from perfil where carNome = ?;";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);

            // seta os valores
            stmt.setString(1, nome);

            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Perfil> lista() {
        String sql = "select * from perfil;";
        ArrayList<Perfil> perfis = new ArrayList();
        try {
            // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);

            // executa
            ResultSet rs = stmt.executeQuery();
            //joga resultado da consulta no ArrayList
            while (rs.next()) {
                Perfil perfil = new Perfil();
                perfil.setNome(rs.getString(1));
                perfis.add(perfil);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return perfis;
    }
}
