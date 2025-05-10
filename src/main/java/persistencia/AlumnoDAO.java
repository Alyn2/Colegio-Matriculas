package persistencia;

import dominio.entidades.Alumno;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlumnoDAO implements DAO<Alumno, String>{
    Conexion conectar = new Conexion();
    Connection conn; 
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List listar(){
        List<Alumno> datos = new ArrayList<>();
        String sql = "SELECT * FROM alumno";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();            
            while(rs.next()){
                Alumno a = new Alumno();
                a.setId(rs.getInt(1));
                a.setPrimerNombre(rs.getString(2));
                a.setSegundoNombre(rs.getString(3));
                a.setApellidoPaterno(rs.getString(4));
                a.setApellidoMaterno(rs.getString(5));
                a.setGenero(rs.getString(6));
                a.getDocumentoIdentidad().setTipoDocumento(rs.getString(7));
                a.getDocumentoIdentidad().setNumeroDocumento(rs.getString(8));
                a.setFechaNacimiento(rs.getDate(9));
                a.setDepartamento(rs.getString(10));
                a.setProvincia(rs.getString(11));
                a.setDistrito(rs.getString(12));
                a.setDireccion(rs.getString(13));
                a.getApoderado().setId(rs.getInt(14)); //Puede haber sido su nombre apellido...
                datos.add(a);
            }
        } catch(Exception e){
            System.err.println(e);
        }
        return datos;
    }
    
    
    @Override
    public boolean insertar(Alumno a){
        String sql = "INSERT INTO alumno(primerNombre,segundoNombre,apellidoPaterno,apellidoMaterno,genero,tipoDocumento,"
                + "numeroDocumento,fechaNacimiento,departamento,"
                + "provincia, distrito,direccion,id_apoderado)"
                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, a.getPrimerNombre());
            ps.setString(2, a.getSegundoNombre());
            ps.setString(3, a.getApellidoPaterno());
            ps.setString(4, a.getApellidoMaterno());
            ps.setString(5, a.getGenero());
            ps.setString(6, a.getDocumentoIdentidad().getTipoDocumento());
            ps.setString(7, a.getDocumentoIdentidad().getNumeroDocumento());
            ps.setDate(8, a.getFechaNacimiento());
            ps.setString(9, a.getDepartamento());
            ps.setString(10, a.getProvincia());
            ps.setString(11, a.getDistrito());
            ps.setString(12, a.getDireccion());
            ps.setInt(13, a.getApoderado().getId());
            
            ps.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    
    @Override
    public int modificar(Alumno a){
        int respuesta = 0; //para que la tabla se resetee cuando actualizamos datos
        String sql = "UPDATE alumno set primerNombre=?, segundoNombre=?, apellidoPaterno=?, apellidoMaterno=?, genero=?, "
                + "tipoDocumento=?, numeroDocumento=?, fechaNacimiento=?,"
                + " departamento=?, provincia=?, distrito=?, direccion=?, id_apoderado=?"
                + " WHERE id=?";
        
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, a.getPrimerNombre());
            ps.setString(2, a.getSegundoNombre());
            ps.setString(3, a.getApellidoPaterno());
            ps.setString(4, a.getApellidoMaterno());
            ps.setString(5, a.getGenero());
            ps.setString(6, a.getDocumentoIdentidad().getTipoDocumento());
            ps.setString(7, a.getDocumentoIdentidad().getNumeroDocumento());
            ps.setDate(8, a.getFechaNacimiento());
            ps.setString(9, a.getDepartamento());
            ps.setString(10, a.getProvincia());
            ps.setString(11, a.getDistrito());
            ps.setString(12, a.getDireccion());
            ps.setInt(13, a.getApoderado().getId());
            ps.setInt(14, a.getId());
            
            respuesta = ps.executeUpdate();
            
            if(respuesta == 1){
                return 1;
            } else{
                return 0;
            }
            
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return respuesta;
    }
    
    
    public void Eliminar(int id){
        String sql = "DELETE FROM alumno WHERE id="+id;
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    
    //VALIDACIONES
    public int existeAlumno(String primerNombre, String segundoNombre,String apellidoPaterno, String apellidoMaterno, String numeroDocumento){
        ps = null;
        rs = null;  //Ya que esta consulta trae algo
        conn = conectar.getConexion();
        
        //consultamos si hay usuarios ya creados cone se nombre de usuario
        String sql = "SELECT COUNT(id) FROM alumno WHERE primerNombre=? AND segundoNombre=? "
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
    @Override
    public List<Alumno> buscar(String apellidoPaterno){
    List<Alumno> alum_busc = new ArrayList<>();//array para los alumnos con coincidencia de busqueda
    String sql = "SELECT * FROM alumno WHERE apellidoPaterno LIKE ?";
    try {
        conn = conectar.getConexion();
        ps = conn.prepareStatement(sql);
        ps.setString(1, apellidoPaterno + "%");
        rs = ps.executeQuery();
        while(rs.next()){
                Alumno a=new Alumno();
                a.setId(rs.getInt(1));
                a.setPrimerNombre(rs.getString(2));
                a.setSegundoNombre(rs.getString(3));
                a.setApellidoPaterno(rs.getString(4));
                a.setApellidoMaterno(rs.getString(5));
                a.setGenero(rs.getString(6));
                a.getDocumentoIdentidad().setTipoDocumento(rs.getString(7));
                a.getDocumentoIdentidad().setNumeroDocumento(rs.getString(8));
                a.setFechaNacimiento(rs.getDate(9));
                a.setDepartamento(rs.getString(10));
                a.setProvincia(rs.getString(11));
                a.setDistrito(rs.getString(12));
                a.setDireccion(rs.getString(13));
                a.getApoderado().setId(rs.getInt(14));
                alum_busc.add(a);
            }
            return alum_busc;
        } catch(Exception e){
            System.err.println(e);
            return null;
        } 
    }
    /*
    @Override
    public Alumno buscar(String apellidoPaterno){
        Alumno a = null;
        String sql = "SELECT * FROM alumno WHERE apellidoPaterno = ?";
        try{
            a = new Alumno();
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, apellidoPaterno);
            rs = ps.executeQuery();  
                        
            while(rs.next()){
                a.setId(rs.getInt(1));
                a.setPrimerNombre(rs.getString(2));
                a.setSegundoNombre(rs.getString(3));
                a.setApellidoPaterno(rs.getString(4));
                a.setApellidoMaterno(rs.getString(5));
                a.setGenero(rs.getString(6));
                a.getDocumentoIdentidad().setTipoDocumento(rs.getString(7));
                a.getDocumentoIdentidad().setNumeroDocumento(rs.getString(8));
                a.setFechaNacimiento(rs.getDate(9));
                a.setDepartamento(rs.getString(10));
                a.setProvincia(rs.getString(11));
                a.setDistrito(rs.getString(12));
                a.setDireccion(rs.getString(13));
                a.getApoderado().setId(rs.getInt(14));
            }
            return a;
        } catch(Exception e){
            System.err.println(e);
            return null;
        }
    }*/
    
    public Alumno buscarPorId(int id) {
        Alumno alumno = null;
        try {
            conn = conectar.getConexion();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM alumno WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                alumno = new Alumno();
                alumno.setId(rs.getInt(1));
                alumno.setPrimerNombre(rs.getString(2));
                alumno.setSegundoNombre(rs.getString(3));
                alumno.setApellidoPaterno(rs.getString(4));
                alumno.setApellidoMaterno(rs.getString(5));
                alumno.setGenero(rs.getString(6));
                alumno.getDocumentoIdentidad().setTipoDocumento(rs.getString(7));
                alumno.getDocumentoIdentidad().setNumeroDocumento(rs.getString(8));
                alumno.setFechaNacimiento(rs.getDate(9));
                alumno.setDepartamento(rs.getString(10));
                alumno.setProvincia(rs.getString(11));
                alumno.setDistrito(rs.getString(12));
                alumno.setDireccion(rs.getString(13));
                alumno.getApoderado().setId(rs.getInt(14));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumno;
    }
    
}
