/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Usuario;
import connection.UsuarioDAO;
import java.sql.SQLException;

/**
 *
 * @author anado
 */
public class Teste {
    
    public static void main(String[] args) throws SQLException{
        
        int id;
        int token;
        String nome;
        String senha;
        String filme;
        String diretor;
        
        UsuarioDAO bd = new UsuarioDAO();
        
        nome = "test";
        senha = "teste1";
        token = 56;
        filme = "dune";
        diretor = "villenueve";
        
        Usuario user = new Usuario(nome, senha);
        
        if(bd.selectUser(user)){
            System.out.println("Votado");
        }else{
            System.out.println("Erro ao votar");
        }
        
        
        
        //bd.insert(user);
        //bd.insertToken(user, token);
        //Usuario usuario = bd.select(user); --> usuario.getSenha();
        //bd.votar(token, filme, diretor);
        //bd.selectUser(user) --> verifica se o usuário existe
    }
    
}
