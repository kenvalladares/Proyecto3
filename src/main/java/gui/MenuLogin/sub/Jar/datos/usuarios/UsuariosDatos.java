package gui.MenuLogin.sub.Jar.datos.usuarios;
import gui.MenuLogin.sub.Jar.datos.conexion.Conexion;
import recursos.clases.Usuarios;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuariosDatos extends Conexion {
    public boolean registrar (Usuarios usr){
        PreparedStatement ps = null;
        Connection cn = obetenerConexion();
        String sql = "INSERT INTO usuarios (usuario, password, nombre, correo) VALUES (?,?,?,?)";
        try{
         cn.prepareStatement("SET IDENTITY_INSERT usuarios ON");
        ps = cn.prepareStatement(sql);
        ps.setString(1,usr.getUsuario());
        ps.setString(2,usr.getPassword());
        ps.setString(3,usr.getNombre());
        ps.setString(4,usr.getCorreo());
        ps.execute();
        return true;
    } catch (SQLException ex){
            Logger.getLogger(UsuariosDatos.class.getName()).log(Level.SEVERE,null,ex);
        }
        return false;
    }
    public boolean Login (Usuarios usr){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = obetenerConexion();
        String sql = "SELECT id, usuario,password, nombre, id FROM usuarios WHERE usuario= ?";
        try{

            ps = cn.prepareStatement(sql);
            ps.setString(1,usr.getUsuario());
            rs = ps.executeQuery();
            if(rs.next()){
                if(usr.getPassword().equals(rs.getString(3))){
                    usr.setId(rs.getInt(1));
                    usr.setNombre(rs.getString(4));
                    return true;
                }else{
                    return false;
                }
            }
            return false;
        } catch (SQLException ex){
            Logger.getLogger(UsuariosDatos.class.getName()).log(Level.SEVERE,null,ex);
        }
        return false;
    }
    public int ExisteUsuario (String usuario){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection cn = obetenerConexion();
        String sql = "SELECT count(id) FROM usuarios WHERE usuario= ?";
        try{

            ps = cn.prepareStatement(sql);
            ps.setString(1,usuario);
            rs = ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
            return 1;
        } catch (SQLException ex){
            Logger.getLogger(UsuariosDatos.class.getName()).log(Level.SEVERE,null,ex);
        }
        return 1;
    }
    public boolean Email(String correo){
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(correo);
        return matcher.find();
    }
}

