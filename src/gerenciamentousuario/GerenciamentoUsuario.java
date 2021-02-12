/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerenciamentousuario;

import gerenciamentousuario.controller.UsuarioController;
import gerenciamentousuario.model.Usuario;
import java.sql.Date;

/**
 *
 * @author pedro-menezes
 */
public class GerenciamentoUsuario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Usuario usu = new Usuario("111111111", "teste", new Date(2021, 10, 11), 'S');
        new UsuarioController().adiciona(usu);
    }    
}
