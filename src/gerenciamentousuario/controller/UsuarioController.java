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
 * Classe responsável por realizar operações sobre o tipo e tabela Usuario
 * entre os sistemas: banco de dados, view e model.
 * @author pedro-menezes
 */
public class UsuarioController {
    private Connection con;

    public UsuarioController() {
        this.con = new ConnectionFactory().getConnection();
    }

    /**
     * Método que recebe um objeto do tipo Usuario e o seu cargo e adiciona a tabela cargo no 
     * banco de dados.
     * @param usuario - Usuario - usuario que será adicionado no banco.
     * @param cargo - Cargo - cargo do usuário.
     */
    public void adiciona(Usuario usuario, Cargo cargo) {
        String sql = "insert into usuario (usuCpf,usuNome,usuSexo,usuDateNasc,usu_carNome,usuDataCadastro) values (?,?,?,?,?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, String.valueOf(usuario.getSexo()));
            stmt.setDate(4, usuario.getDataNascimento());
            stmt.setString(5, cargo.getNome());
            stmt.setDate(6, usuario.getDataCadastro());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * Mátodo que recebe o CPF de um usuário e o deleta do banco de dados.
     * @param cpf - String - CPF do usuário que deve ser deletado.
     */
    public void deleta(String cpf) {
        String sql = "delete from usuario where usuCpf = ?;";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, cpf);

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que retorna uma lista com todos os usuários cadastrados no BD.
     * @return - ArrayList - lista com usuários cadastrados no banco.
     */
    public ArrayList<Usuario> lista() {
        String sql = "select * from usuario;";
        ArrayList<Usuario> usuarios = new ArrayList();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
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

    /**
     * Método que busca um usuário no banco de dados através do CPF.
     * @param cpf - String - CPF do usuário que deseja ser encontrado.
     * @return Usuario = retorna usuário encontrado.
     */
    public Usuario busca(String cpf) {
        String sql = "select * from usuario where usuCpf = ?;";
        Usuario usuario = new Usuario();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                usuario.setCpf(rs.getString(1));
                usuario.setNome(rs.getString(2));
                usuario.setSexo(rs.getString(3).charAt(0));
                usuario.setDataNascimento(rs.getDate(4));
                usuario.setCargo(new Cargo(rs.getString(5)));
                usuario.setDataCadastro(rs.getDate(6));
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
    
    /**
     * Método que edita um usuário no banco de dados.
     * @param usuario - Usuario - usuario que deseja ser alterado.
     */
    public void editar(Usuario usuario) {
        String sql = "update usuario set usuNome = ?, usu_carNome = ?, usuSexo = ? where (usuCpf = ?);";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCargo().getNome());
            stmt.setString(3, String.valueOf(usuario.getSexo()));
            stmt.setString(4, usuario.getCpf());

            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
