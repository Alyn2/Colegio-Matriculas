package persistencia;

import java.sql.*;

public class Conexion {
    private final String base = "sistema_matricula";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/" + base;
    
    public Connection getConexion(){
        Connection con = null;
        try{
            con = DriverManager.getConnection(this.url, this.user, this.password);

        } catch(SQLException e){
            System.err.println(e);
        }
        return con;
    }
    
}
