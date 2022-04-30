/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author anado
 */
public class Main {
    private static final int porta = 12345;
    
    public static void main(String args[]) throws IOException{
        ServerSocket serverSocket = new ServerSocket(porta);
        System.out.println("Server socket criado");
        while(true){
            System.out.println("Aguardando conexões...");
            Socket socket = serverSocket.accept();
            System.out.println("Conectou...");
            new ServerSocketThread(socket).start();
        }
    }
}
