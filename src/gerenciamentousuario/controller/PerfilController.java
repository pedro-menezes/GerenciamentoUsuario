/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario.controller;

import gerenciamentousuario.connection.ConnectionFactory;
import gerenciamentousuario.model.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsável por realizar operações sobre o tipo e tabela Perfil entre
 * os sistemas: banco de dados, view e model.
 * @author pedro-menezes
 */
public class PerfilController {

    private Connection con;

    /**
     * Construtor que recebe valores da conexão.
     */
    public PerfilController() {
        this.con = new ConnectionFactory().getConnection();
    }

    /**
     * Método que recebe um objeto do tipo perfil e adiciona a tabela perfil no
     * banco de dados.
     * @param perfil - Perfil - objeto com valores que serão adicionados no
     * banco de dados.
     */
    public void adiciona(Perfil perfil) {
        String sql = "insert into perfil (perNome) values (?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, perfil.getNome());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que recebe o nome de um perfil e faz a exclusão do mesmo no banco
     * de dados.
     * @param nome - String - nome do perfil que será deletado do banco de dados.
     */
    public void deleta(String nome) {
        String sql = "delete from perfil where perNome = ?;";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que retorna todos os perfis e e seus atributos do banco de dados.
     * @return lista - ArrayList - lista de perfis buscados no banco.
     */
    public ArrayList<Perfil> lista() {
        String sql = "select * from perfil;";
        ArrayList<Perfil> perfis = new ArrayList();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

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
        return perfis;
    }
    
    /**
     * Método que busca um perfil pelo nome no banco de dados.
     * @param nome - String - nome do perfil que será buscado.
     * @return Perfil - perfil buscado.
     */
    public Perfil busca(String nome) {
        ArrayList<Perfil> perfis = lista();

        for (Perfil perfil : perfis) {
            if (perfil.getNome().equals(nome)) {
                return perfil;
            }
        }

        return null;
    }
    
    /**
     * Método que atualiza informações de um determinado cargo no BD.
     * @param perfil - Perfil - objeto com informações para serem atualizadas no banco de dados.
     * @param nomeAntigo - String - nome do perfil antes de ser atualizado.
     */
    public void editar(Perfil perfil, String nomeAntigo) {
        String sql = "update perfil set perNome = ? where (perNome = ?);";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, perfil.getNome());
            stmt.setString(2, nomeAntigo);

            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
