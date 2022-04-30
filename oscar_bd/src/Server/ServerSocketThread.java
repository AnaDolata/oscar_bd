/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import Model.Usuario;
import connection.UsuarioDAO;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import org.json.simple.JSONObject;

/**
 *
 * @author anado
 */
public class ServerSocketThread extends Thread{
    private final Socket socket;
    UsuarioDAO bd = new UsuarioDAO();
    
    public ServerSocketThread(Socket socket) {
        this.socket = socket;
    }
    
    
    public void login(){
        super.run();
        try{
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            String nome = in.readUTF().toString();
            String senha = in.readUTF().toString();
            
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            
            if(bd.selectUser(usuario)){ //se o user existe
                Usuario user = bd.select(usuario);
                String password = usuario.getSenha(); //pega a senha dele
                if(senha.equals(senha)){
                    Random r = new Random();
                    int token = r.nextInt(100)+1;
                    if(bd.insertToken(usuario, token)){ //usuario entrou com a senha certa, gera o token e add no banco
                        JSONObject obj = new JSONObject();
                        obj.put("token", token);
                        out.writeBytes(obj.toJSONString());
                        out.flush();
                        out.close();
                        in.close();
                        socket.close();
                    }
                }
            }else{
                JSONObject obj = new JSONObject();
                String result = "Erro ao logar, tente novamente";
                obj.put("result", result);
                out.writeBytes(obj.toJSONString());
                out.flush();
                out.close();
                in.close();
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void vote(){
        super.run();
        try{
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            
            String diretor = in.readUTF().toString();
            String filme = in.readUTF().toString();
            int token = in.readInt();
            
            if(bd.votar(token, filme, diretor)){
                JSONObject obj = new JSONObject();
                String result = "Voto computado";
                obj.put("result", result);
                out.writeBytes(obj.toJSONString());
                out.flush();
                out.close();
                in.close();
                socket.close();
            }else{
                JSONObject obj = new JSONObject();
                String result = "Token Inválido";
                obj.put("result", result);
                out.writeBytes(obj.toJSONString());
                out.flush();
                out.close();
                in.close();
                socket.close();
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
}
