package gui.MenuLogin.sub.Jar.datos.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
    private static final String USUARIO = "sa";
    private static final String CLAVE="madrid12";
    public static Connection obetenerConexion(){
        try {
            String URL = "jdbc:sqlserver://192.168.0.6:1433;databaseName=Restaurante;";
            Connection cn = DriverManager.getConnection(URL,USUARIO,CLAVE);
            return cn;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
