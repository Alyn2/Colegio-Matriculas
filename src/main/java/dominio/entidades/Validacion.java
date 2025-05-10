package dominio.entidades;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import persistencia.Conexion;
import persistencia.UsuarioDAO;


public class Validacion {
    Conexion conectar = new Conexion();
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    
    public int existeUsuario(String usuario){
        ps = null;
        rs = null;  //Ya que esta consulta trae algo
        conn = conectar.getConexion();
        
        //consultamos si hay usuarios ya creados cone se nombre de usuario
        String sql = "SELECT COUNT(id) FROM usuarios WHERE usuario = ?";
        
        try {
            //Preparamos la consulta
            ps = conn.prepareStatement(sql);
            //Enviaremos un solo valor
            ps.setString(1, usuario);
            //Vamos a ejecutar la consulta que regresa datos
            rs = ps.executeQuery();
            
            //Vamos a contar si hay usuarios con ese mismo nick
            if(rs.next()){
                //El 1 no dejará registrar
                return rs.getInt(1); 
            } 
            
            return 1; //En caso tenga algún problema
            
        } catch (SQLException ex) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            return 1;
        }
    }
    
    
    public int existeDocente(String primerNombre, String segundoNombre,String apellidoPaterno, String apellidoMaterno, String numeroDocumento){
        ps = null;
        rs = null;  //Ya que esta consulta trae algo
        conn = conectar.getConexion();
        
        //consultamos si hay usuarios ya creados cone se nombre de usuario
        String sql = "SELECT COUNT(id) FROM docente WHERE primerNombre=? AND segundoNombre=? "
                + "AND apellidoPaterno=? AND apellidoMaterno=? AND numeroDocumento=?";
        
        try {
            //Preparamos la consulta
            ps = conn.prepareStatement(sql);
            //Enviaremos un solo valor
            ps.setString(1, primerNombre);
            ps.setString(2, segundoNombre);
            ps.setString(3, apellidoPaterno);
            ps.setString(4, apellidoMaterno);
            ps.setString(5, numeroDocumento);
            //Vamos a ejecutar la consulta que regresa datos
            rs = ps.executeQuery();
            
            //Vamos a contar si hay usuarios con ese mismo nick
            if(rs.next()){
                //El 1 no dejará registrar
                return rs.getInt(1); 
            } 
            
            return 1; //En caso tenga algún problema
            
        } catch (SQLException ex) {
        Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            return 1;
        }
    }
    
    
    public boolean esEmail(String correo){
        //Patrón para validar Email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+"
                + "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        
        Matcher mather = pattern.matcher(correo);
        
        //Retorna true o false en caso coincida o no con el patron de correo
        return mather.find(); 
    }
   
    
    //Para entrar al Home
    public boolean login(Usuario user){
        ps = null;
        rs = null;  //Ya que esta consulta trae algo
        conn = conectar.getConexion();
        
        //consultamos si hay usuarios ya creados con ese nombre de usuario
        //+consulta multitabla 3er video "Privilegio y Roles de Usuario
        String sql = "SELECT u.id, u.usuario, u.password, u.nombre, u.id_rol, r.nombre, u.estado "
                                + "FROM usuarios AS u "
                                + "INNER JOIN roles AS r ON u.id_rol = r.id "
                                + "WHERE usuario = ? AND estado = 'Activo'";
        
        try {
            //Preparamos la consulta
            ps = conn.prepareStatement(sql);
            //Enviaremos un solo valor
            ps.setString(1, user.getUsuario());
            //Vamos a ejecutar la consulta que regresa datos
            rs = ps.executeQuery();
            
            //Vamos recorrer los resultados que nos da la consulta
            if(rs.next()){
                //comparamos que las password (BD y txtField) sean las mismas
                if(user.getPassword().equals(rs.getString(3))) {//la 3 es password
                    //Los datos que consulte los paso al modelo para que acceda a ellos
                                //Para el last_session, o sea la hora y fecha que inicio sesion
                    String sqlUpdate = "UPDATE usuarios SET last_session = ? WHERE id = ?";
                    //Ejecutamos esta consulta de insercion/update
                    ps = conn.prepareStatement(sqlUpdate);
                    ps.setString(1, user.getLast_session());
                    ps.setInt(2, rs.getInt(1)); //El id de la consulta sql de arriba
                    ps.execute(); //Ejecuta la consulta, ya que no trae nada solo inserta
                            
                    user.setId(rs.getInt(1));//para el u.id - 3er video
                    user.setNombre(rs.getString(4)); //para el u.nombre - 3er video
                    user.getRol().setId(rs.getInt(5)); //para el u.id_tipo - 3er video
                    user.getRol().setNombre(rs.getString(6)); //para el t.nombre - 3er video
                    user.setEstado(rs.getString(7));
                    return true; //Solo cuando coincida el usuario y el passwod es true
                } else {
                    return false;
                }
            } 
            
            return false;
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
