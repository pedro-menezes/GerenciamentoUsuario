/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario.model;

import java.util.ArrayList;
import java.sql.Date;
/**
 *
 * @author pedro-menezes
 */
public class Usuario extends Pessoa{
    private Cargo cargo;
    private ArrayList<Perfil> perfis;
    
    public Usuario() {}

    public Usuario(String cpf, String nome, Date dataNascimento, char sexo) {
        super(cpf, nome, dataNascimento, sexo);
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public ArrayList<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(ArrayList<Perfil> perfis) {
        this.perfis = perfis;
    }
    
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
    
    public boolean verificaPerfil(Perfil perfil){
        for (Perfil perfilAux : getPerfis()) {
            if (perfil.getNome().equals(perfilAux.getNome())) {
                return true;
            }
        }
        return false;
    }
}
