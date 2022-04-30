/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Usuario;
import connection.UsuarioDAO;
import java.sql.SQLException;
import java.util.Random;
import org.json.simple.JSONObject;

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
        
        
        Random r = new Random();
        int token4 = r.nextInt(100)+1;
        JSONObject obj = new JSONObject();
        obj.put("token", token4);
        int token2 = (int) obj.get("token");
        System.out.println(obj);
        
        
        //bd.insert(user);
        //bd.insertToken(user, token);
        //Usuario usuario = bd.select(user); --> usuario.getSenha();
        //bd.votar(token, filme, diretor);
        //bd.selectUser(user) --> verifica se o usuário existe
    }
    
}
