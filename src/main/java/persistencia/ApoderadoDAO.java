package persistencia;

import dominio.entidades.Apoderado;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApoderadoDAO implements DAO<Apoderado, String>{
    Conexion conectar = new Conexion();
    Connection conn; 
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List listar(){
        List<Apoderado> datos = new ArrayList<>();
        String sql = "SELECT * FROM apoderado";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();            
            while(rs.next()){
                Apoderado a = new Apoderado();
                a.setId(rs.getInt(1));
                a.setPrimerNombre(rs.getString(2));
                a.setSegundoNombre(rs.getString(3));
                a.setApellidoPaterno(rs.getString(4));
                a.setApellidoMaterno(rs.getString(5));
                a.setGenero(rs.getString(6));
                a.getDocumentoIdentidad().setTipoDocumento(rs.getString(7));
                a.getDocumentoIdentidad().setNumeroDocumento(rs.getString(8));
                a.setCelular(rs.getString(9));
                a.setCorreo(rs.getString(10)); 
                datos.add(a);
            }
        } catch(Exception e){
            System.err.println(e);
        }
        return datos;
    }
    
    
    @Override
    public boolean insertar(Apoderado a){
        String sql = "INSERT INTO apoderado(primerNombre,segundoNombre,apellidoPaterno,apellidoMaterno,genero,tipoDocumento,"
                + "numeroDocumento,celular,correo)"
                + " VALUES (?,?,?,?,?,?,?,?,?)";
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
            ps.setString(8, a.getCelular());
            ps.setString(9, a.getCorreo());
            
            ps.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        
    }
    
    @Override
    public int modificar(Apoderado a){
        int respuesta = 0; //para que la tabla se resetee cuando actualizamos datos
        String sql = "UPDATE apoderado set primerNombre=?, segundoNombre=?, apellidoPaterno=?, apellidoMaterno=?, genero=?, "
                + "tipoDocumento=?, numeroDocumento=?, celular=?,correo=? WHERE id=?";
        
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
            ps.setString(8, a.getCelular());
            ps.setString(9, a.getCorreo());
            ps.setInt(10, a.getId());
            
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
        String sql = "DELETE FROM apoderado WHERE id="+id;
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    
    //VALIDACIONES
    public int existeApoderado(String primerNombre, String segundoNombre,String apellidoPaterno, String apellidoMaterno, String numeroDocumento){
        ps = null;
        rs = null;  //Ya que esta consulta trae algo
        conn = conectar.getConexion();
        
        //consultamos si hay usuarios ya creados cone se nombre de usuario
        String sql = "SELECT COUNT(id) FROM apoderado WHERE primerNombre=? AND segundoNombre=? "
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
        Logger.getLogger(ApoderadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            return 1;
        }
    }
    
    
    @Override
    public List<Apoderado> buscar(String apellidoPaterno){
        List<Apoderado> apod_busc = new ArrayList<>();//array para los apoderados con coincidencia de busqueda
        String sql = "SELECT * FROM apoderado WHERE apellidoPaterno LIKE ?";
        try{
            
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, apellidoPaterno + "%");
            rs = ps.executeQuery();  
                        
            while(rs.next()){
                Apoderado a=new Apoderado();
                a.setId(rs.getInt(1));
                a.setPrimerNombre(rs.getString(2));
                a.setSegundoNombre(rs.getString(3));
                a.setApellidoPaterno(rs.getString(4));
                a.setApellidoMaterno(rs.getString(5));
                a.setGenero(rs.getString(6));
                a.getDocumentoIdentidad().setTipoDocumento(rs.getString(7));
                a.getDocumentoIdentidad().setNumeroDocumento(rs.getString(8));
                a.setCelular(rs.getString(9));
                a.setCorreo(rs.getString(10));
                apod_busc.add(a);
            }
            return apod_busc;
        } catch(Exception e){
            System.err.println(e);
            return null;
        }
    }
    
}
