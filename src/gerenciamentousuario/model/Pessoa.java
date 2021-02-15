/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario.model;

import java.sql.Date;

/**
 * Classe abstrata do tipo pessoa.
 * @author pedro-menezes
 */
public abstract class Pessoa {
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private char sexo;

    /**
     * Construtor simples.
     */
    public Pessoa() {}
    
    /**
     * Construtor que recebe parâmetros do tipo pessoa e vincula ao objeto.
     * @param cpf - String - CPF de uma pessoa.
     * @param nome - String - nome da pessoa.
     * @param dataNascimento - Date - data de nascomento.
     * @param sexo - char - sexo da pessoa 'M' ou 'F'.
     */
    public Pessoa(String cpf, String nome, Date dataNascimento, char sexo) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
    }
    
    /**
     * Método que retorna o CPF da pessoa.
     * @return String - CPF da pessoa.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Método que recebe CPF e vincula a pessoa.
     * @param cpf - String - CPF que será vinculado.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Método que retorna o nome da pessoa.
     * @return String - nome da pessoa.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que recebe nome e vincula a pessoa.
     * @param nome - String - nome que será vinculado.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que retorna a data de nascimento da pessoa.
     * @return Date - data de nascimento da pessoa.
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Método que recebe a data de nascimento e vincula a pessoa.
     * @param dataNascimento - String - data de nascimento que será vinculado.
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Método que retorna o sexo da pessoa.
     * @return String - sexo da pessoa.
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * Método que recebe o sexo e vincula a pessoa.
     * @param sexo - String - sexo que será vinculado.
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
}
