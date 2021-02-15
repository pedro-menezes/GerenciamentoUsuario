/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario.controller;

import gerenciamentousuario.connection.ConnectionFactory;
import gerenciamentousuario.model.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsável por realizar operações sobre o tipo e tabela Cargo
 * entre os sistemas: banco de dados, view e model.
 * @author pedro-menezes
 */
public class CargoController {
    private Connection con;

    /**
     * Construtor que recebe valores da conexão.
     */
    public CargoController() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    /**
     * Método que recebe um objeto do tipo cargo e adiciona a tabela cargo no 
     * banco de dados.
     * @param cargo - Cargo - objeto com valores que serão adicionados no banco de dados.
     */
    public void adiciona(Cargo cargo) {
        String sql = "insert into cargo (carNome) values (?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, cargo.getNome());
            
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que recebe o nome de um cargo e faz a exclusão do mesmo no banco de dados.
     * @param nome - String - nome do cargo que será deletado do banco de dados.
     */
    public void deleta(String nome) {
        String sql = "delete from cargo where carNome = ?;";

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
     * Método que retorna todos os cargos e e seus atributos do banco de dados.
     * @return lista - ArrayList - lista de cargos buscados no banco.
     */
    public ArrayList<Cargo> lista() {
        String sql = "select * from cargo;";
        ArrayList<Cargo> cargos = new ArrayList();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Cargo cargo = new Cargo(rs.getString(1));
                cargos.add(cargo);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cargos;
    }
    
    /**
     * Método que atualiza informações de um determinado cargo no BD.
     * @param cargo - Cargo - objeto com informações para serem atualizadas no banco de dados.
     * @param nomeAntigo - String - nome do cargo antes de ser atualizado.
     */
    public void editar(Cargo cargo, String nomeAntigo) {
        String sql = "update cargo set carNome = ? where (carNome = ?);";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, cargo.getNome());
            stmt.setString(2, nomeAntigo);

            stmt.executeUpdate();

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
