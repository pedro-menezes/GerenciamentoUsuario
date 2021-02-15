/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario.model;

import java.util.ArrayList;
import java.sql.Date;
/**
 * Classe que respresenta o tipo usuário que também é do tipo pessoa.
 * @author pedro-menezes
 */
public class Usuario extends Pessoa{
    private Cargo cargo;
    private ArrayList<Perfil> perfis;
    private Date dataCadastro;
    
    /**
     * Construtor simples.
     */
    public Usuario() {}

    /**
     * Contrutor que recebe parâmetros para construtor de pessoa e paramentreo 
     * data de cadastro que é exclusivo de usuário.
     * @param cpf - String - CPF do usuário.
     * @param nome - String - nome do usuário.
     * @param dataNascimento - Date - data de nascimento do usuário.
     * @param sexo - char - sexo do usuário 'M' ou 'F'.
     * @param dataCadastro  - Date - data que o usuário foi cadastrado no sistema.
     */
    public Usuario(String cpf, String nome, Date dataNascimento, char sexo, Date dataCadastro) {
        super(cpf, nome, dataNascimento, sexo);
        this.dataCadastro = dataCadastro;
    }
    
    /**
     * Método que retorna o cargo do usuário.
     * @return Cargo - cargo vinculado ao usuário.
     */
    public Cargo getCargo() {
        return cargo;
    }

    /**
     * Método que recebe novo cargo do usuário.
     * @param cargo - cargo que será vinculado ao usuário.
     */
    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    /**
     * Método que retorna a lista de perfis do usuário.
     * @return ArrayList - lista com perfis do usuário.
     */
    public ArrayList<Perfil> getPerfis() {
        return perfis;
    }
    
    /**
     * Método que adiciona uma lista de perfis ao usuário.
     * @param perfis - lista de peris que será adicionada ao usuário.
     */
    public void setPerfis(ArrayList<Perfil> perfis) {
        this.perfis = perfis;
    }
    
    /**
     * Método que recebe a data de cadastro no usuário no sistema.
     * @param dataCadastro - Date - data de cadastro.
     */
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    /**
     * Método que retorna a data de cadastro no sistema.
     * @return Date - data de cadastro do usuário no sistema.
     */
    public Date getDataCadastro() {
        return dataCadastro;
    }
    
    /**
     * Método que retorna todos os perfis do usuário em uma linha.
     * @return String - linha com perfis do usuário.
     */
    public String retornaTextoPerfis(){
        String linhaPerfis = "";
        for (Perfil perfil: getPerfis()) {
            linhaPerfis += perfil.getNome()+"; ";
        }
        if (linhaPerfis.equals("")) {
            return "Sem perfis cadastrados";
        }
        return linhaPerfis;
    }
    
    /**
     * Verifica se perfil pertence ao usuário.
     * @param perfil - Perfil - perfil que será verificado.
     * @return boolean - 'true' se pertence e 'false' se não pertence.
     */
    public boolean verificaPerfil(Perfil perfil){
        for (Perfil perfilAux : getPerfis()) {
            if (perfil.getNome().equals(perfilAux.getNome())) {
                return true;
            }
        }
        return false;
    }
}
