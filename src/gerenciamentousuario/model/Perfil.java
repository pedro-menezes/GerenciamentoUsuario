/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario.model;

/**
 * Classe referentes aos objetos do ripo Perfil.
 * @author pedro-menezes
 */
public class Perfil{
    private String nome;

    /**
     * Construtor simples.
     */
    public Perfil() {
    }
    
    /**
     * Construtor que recebe o nome do perfil.
     * @param nome - String - nome que será vinculado ao perfil.
     */
    public Perfil(String nome) {
        this.nome = nome;
    }
    
    /**
     * Método que retorna nome do perfil.
     * @return String - nome do perfil.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que recebe um nome e vincula ao perfil.
     * @param nome - String - nome do perfil.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
