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
 * Classe responsável por realizar operações sobre a tabela usuario_perfil
 * entre os sistemas: banco de dados, view e model.
 * @author pedro-menezes
 */
public class UsuarioPerfilController {
    private Connection con;

    /**
     * Construtor que recebe valores da conexão.
     */
    public UsuarioPerfilController() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    /**
     * Método que recebe um perfil e um usuário e faz a ligação entre eles no 
     * banco de dados através da tabela usuario_perfil.
     * @param perfil - Perfil - perfil que será adicionado na tabela usuario_perfil.
     * @param usuario - Usuario - usuário que será adicionado na tabela usuario_perfil.
     */
    public void adiciona(Perfil perfil, Usuario usuario) {
        String sql = "insert into usuario_perfil (usp_usuCpf,usp_perNome) values (?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, perfil.getNome());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que recebe o nome de um perfil e o CPF de um Usuário e faz a exclusão
     * da ligação na tabela usuario_perfil.
     * @param usuCpf - String - CPF do usuário que terá um perfil removido da conta.
     * @param perNome - String - nome do perfil que será removido do usuário.
     */
    public void deleta(String usuCpf, String perNome) {
        String sql = "delete from usuario_perfil where (usp_usuCpf = ?) and (usp_perNome = ?);";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, usuCpf);
            stmt.setString(2, perNome);
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que busca todos os perfis que estão adicionados em um determinado
     * usuário.
     * @param usuario - Usuario - objeto do usuario que deseja buscar os perfis.
     */
    public void buscaPerfis(Usuario usuario) {
        String sql = "select usp_perNome from usuario_perfil where usp_usuCpf = ?";
        ArrayList<Perfil> perfis = new ArrayList();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, usuario.getCpf());

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Perfil perfil = new Perfil();
                perfil.setNome(rs.getString(1));
                perfis.add(perfil);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        usuario.setPerfis(perfis);
    }

    /**
     * Método para verificar se existe uma ligação entre um determinado usuário
     * com um determinado perfil.
     * @param usuCpf - String - CPF do usuário que se deseja buscar a ligação.
     * @param perNome - String - nome do perfil que será verificado a existência de uma ligação.
     * @return boolean - Método que retorna todos os cargos e e seus atributos do banco de dados.
     */
    public boolean existe(String usuCpf, String perNome) {
        String sql =  "select (select count(*) from usuario_perfil where usp_perNome = ? and usp_usuCpf = ?) > 0";
        boolean existe = false;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, perNome);
            stmt.setString(2, usuCpf);

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                existe = rs.getBoolean(1);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return existe;
    }
    
    /**
     * Metódo que verifica se existe algum usuário com determinado perfil.
     * @param perNome - String - nome do perfil que será verificado.
     * @return boolean - Método que retorna todos os cargos e e seus atributos do banco de dados.
     */
    public boolean existe(String perNome) {
        String sql =  "select (select count(*) from usuario_perfil where usp_perNome = ?) > 0";
        boolean existe = false;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, perNome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                existe = rs.getBoolean(1);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return existe;
    }
}
