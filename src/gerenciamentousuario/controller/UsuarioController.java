/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario.controller;

import gerenciamentousuario.connection.ConnectionFactory;
import gerenciamentousuario.model.Cargo;
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
public class UsuarioController {

    // a conexão com o banco de dados
    private Connection con;

    public UsuarioController() {
        //inicializa a conexão com o BD
        this.con = new ConnectionFactory().getConnection();
    }

    public void adiciona(Usuario contato, Cargo cargo) {
        String sql = "insert into usuario (usuCpf,usuNome,usuSexo,usuDateNasc,usu_carNome) values (?,?,?,?,?)";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);

            // seta os valores
            stmt.setString(1, contato.getCpf());
            stmt.setString(2, contato.getNome());
            stmt.setString(3, String.valueOf(contato.getSexo()));
            stmt.setDate(4, contato.getDataNascimento());
            stmt.setString(5, cargo.getNome());
            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleta(String cpf) {
        String sql = "delete from usuario where usuCpf = ?;";

        try {
            // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);

            // seta os valores
            stmt.setString(1, cpf);

            // executa
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Usuario> lista() {
        String sql = "select * from usuario;";
        ArrayList<Usuario> usuarios = new ArrayList();
        try {
            // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);

            // executa
            ResultSet rs = stmt.executeQuery();
            //joga resultado da consulta no ArrayList
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCpf(rs.getString(1));
                usuario.setNome(rs.getString(2));
                usuario.setSexo(rs.getString(3).charAt(0));
                usuario.setDataNascimento(rs.getDate(4));
                usuario.setCargo(new Cargo(rs.getString(5)));
                usuarios.add(usuario);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    public Usuario busca(String cpf) {
        String sql = "select * from usuario where usuCpf = ?;";
        Usuario usuario = new Usuario();
        try {
            // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);

            // seta os valores
            stmt.setString(1, cpf);

            // executa
            ResultSet rs = stmt.executeQuery();

            //joga resultado da consulta no ArrayList
            while (rs.next()) {
                usuario.setCpf(rs.getString(1));
                usuario.setNome(rs.getString(2));
                usuario.setSexo(rs.getString(3).charAt(0));
                usuario.setDataNascimento(rs.getDate(4));
                usuario.setCargo(new Cargo(rs.getString(5)));
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
    
    public void editar(Usuario usuario) {
        String sql = "update usuario set usuNome = ?, usu_carNome = ?, usuSexo = ? where (usuCpf = ?);";
        try {
            // prepared statement para inserção
            PreparedStatement stmt = con.prepareStatement(sql);

            // seta os valores
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCargo().getNome());
            stmt.setString(3, String.valueOf(usuario.getSexo()));
            stmt.setString(4, usuario.getCpf());

            // executa
            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
