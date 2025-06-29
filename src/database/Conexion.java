package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Miguel
 */
public class Conexion {
    private final String DRIVER="com.mysql.cj.jdbc.Driver";
    private final String URL="jdbc:mysql://localhost:3306/";
    private final String DB="dbsistema";
    private final String USER="root";
    private final String PASSWORD="";
    
    public Connection cadena;
    public static Conexion instancia;
    
    private Conexion(){
        this.cadena = null;
    }
    
    public Connection conectar(){
        try {
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection( URL + DB, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return this.cadena;
    }
    
    public void desconectar(){
        try {
            this.cadena.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public synchronized static Conexion getInstancia(){
        if(instancia==null){
            instancia = new Conexion();
        }
        return instancia;
    }
}
