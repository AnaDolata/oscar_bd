/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author anado
 */
public class UsuarioDAO {
    
    public Usuario select(Usuario user){
        ConnectionFactory connector = new ConnectionFactory();
        Connection conn = connector.getConnection();
        int id = 0;
        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("SELECT senha FROM pessoa WHERE nome=?");
            ps.setString(1, user.getNome());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setSenha(rs.getString("senha"));
                return usuario;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connector.close(conn);
        }
        return null;
    }
    
    public boolean insertToken(Usuario user, int token){
        ConnectionFactory connector = new ConnectionFactory();
        Connection conn = connector.getConnection();
        int id = 0;
        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("UPDATE pessoa SET tokenid=? WHERE nome=?;");
            ps.setInt(1, token);
            ps.setString(2, user.getNome());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connector.close(conn);
        }
        return false;
    }
    
    public boolean votar(int token, String filme, String diretor){
        ConnectionFactory connector = new ConnectionFactory();
        Connection conn = connector.getConnection();
        int id = 0;
        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("UPDATE pessoa SET filme=?, diretor=? WHERE tokenid=?;");
            ps.setString(1, filme);
            ps.setString(2, diretor);
            ps.setInt(3, token);
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connector.close(conn);
        }
        return false;
    }
    
        
    public boolean insert(Usuario user){
        ConnectionFactory connector = new ConnectionFactory();
        Connection conn = connector.getConnection();
        int id = 0;
        try {
            Statement stmt = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO pessoa (nome, senha) VALUES (?, ?)");
            ps.setString(1, user.getNome());
            ps.setString(2, user.getSenha());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            connector.close(conn);
        }
        return false;
    }
    
}
