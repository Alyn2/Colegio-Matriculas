package persistencia;

import dominio.entidades.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

                      //si heredo ya no necesito instanciar
public class UsuarioDAO extends Conexion implements DAO<Usuario, String> {
    Connection conn; 
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    //Para la JTable
    public List listar(){
        List<Usuario> datos = new ArrayList<>();
        //String sql = "SELECT * FROM usuarios"; Lo cambiamos para que aparezca el nombre del rol y no el id_rol
        String sql = "SELECT u.id, u.usuario, u.password, u.nombre,  u.estado, "
                + "u.last_session, r.nombre FROM usuarios AS u INNER JOIN roles AS r ON u.id_rol = r.id";
        try{
            conn = getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();            
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt(1));
                u.setUsuario(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setNombre(rs.getString(4));
                u.setEstado(rs.getString(5));
                u.setLast_session(rs.getString(6));
                u.getRol().setNombre(rs.getString(7));
                
                datos.add(u);
            }
        } catch(Exception e){
            System.err.println(e);
        }
        return datos;
    }
    
    @Override
    public boolean insertar(Usuario user){
        conn = getConexion();
        
        //consulta de inserción a la BD
        String sql = "INSERT INTO usuarios(usuario, password, nombre, estado, id_rol)"
                     + " VALUES(?,?,?,?,?)";
        
        try {
            //Preparamos la consulta
            ps = conn.prepareStatement(sql);
            //Vamos a indicar los valores para los campos de la consulta
            ps.setString(1, user.getUsuario());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNombre());
            ps.setString(4, user.getEstado());
            ps.setInt(5, user.getRol().getId());
            //Vamos a ejecutar esta consulta de insercción (no trae nada devuelta)
            ps.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            return false;
        }
    }
    
    @Override
    public int modificar(Usuario u){
        int respuesta = 0; //para que la tabla se resetee cuando actualizamos datos
        String sql = "UPDATE usuarios set usuario=?, password=?, nombre=?, estado=?, id_rol=?"
                + " WHERE id=?";
        
        try {
            conn = getConexion();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, u.getUsuario());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getNombre());
            ps.setString(4, u.getEstado());
            ps.setInt(5, u.getRol().getId());
            ps.setInt(6, u.getId());
            
            respuesta = ps.executeUpdate();
            
            if(respuesta == 1){
                return 1;
            } else{
                return 0;
            }
            
        } catch (Exception e) {
            System.err.println("");
        }
        
        return respuesta;
    }
    
    
    public boolean DarBaja(int id) {
        // Consulta para obtener el estado actual del usuario
        String sqlSelect = "SELECT estado FROM usuarios WHERE id=?";

        try {
            conn = getConexion();
            ps = conn.prepareStatement(sqlSelect);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String estadoActual = rs.getString("estado");

                if ("Inactivo".equalsIgnoreCase(estadoActual)) {
                    // El usuario ya está inactivo.
                    return false;
                } else {
                    // El usuario no está inactivo, proceder con la actualización.
                    String sqlUpdate = "UPDATE usuarios SET estado='Inactivo' WHERE id=?";
                    ps = conn.prepareStatement(sqlUpdate);
                    ps.setInt(1, id);
                    ps.executeUpdate();
                    return true;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
    //No se está utilizando esto, solo el dar de baja.
    public void eliminar(int id){
        String sql = "DELETE FROM usuarios WHERE id="+id;
        try {
            conn = getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    @Override
    public List<Usuario> buscar(String nombreusuario){
        List<Usuario> user_busc = new ArrayList<>();//array para los usuarios con coincidencia de busqueda

        String sql = "SELECT * FROM usuarios WHERE nombre LIKE ?";
        try{
            conn = getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombreusuario + "%");
            rs = ps.executeQuery();  
                        
            while(rs.next()){
                Usuario u=new Usuario();
                u.setId(rs.getInt(1));
                u.setUsuario(rs.getString(2));
                
                u.setPassword(rs.getString(3));
                u.setNombre(rs.getString(4));
                u.setEstado(rs.getString(5));
                u.setLast_session(rs.getString(6));
                u.getRol().setNombre(rs.getString(7));
                user_busc.add(u); // Agregar el usuario a la lista
            }
            return user_busc;
        } catch(Exception e){
            System.err.println(e);
            return null;
        }
    }

}
