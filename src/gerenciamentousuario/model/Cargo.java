/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario.model;

/**
 * Classe referente ao tipo cargo.
 * @author pedro-menezes
 */
public class Cargo {
    private String nome;

    /**
     * Construtor que recebe o nome do cargo.
     * @param nome - Nome - nome que será vinculado.
     */
    public Cargo(String nome) {
        this.nome = nome;
    }

    /**
     * Construtor simples.
     */
    public Cargo() {
    }
    
    /**
     * Método que retorna o nome do cargo.
     * @return String - nome do cargo.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que recebe nome do cargo.
     * @param nome - String - nome que será vinculado ao cargo.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
