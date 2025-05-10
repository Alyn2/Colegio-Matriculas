package persistencia;

import dominio.entidades.Docente;
import dominio.entidades.Grado;

import java.sql.*;
import java.util.*;

public class DocenteDAO implements DAO<Docente, String>{
    Conexion conectar = new Conexion(); //Como no heredo necesito instanciar
    Connection conn; 
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List listar(){
        List<Docente> datos = new ArrayList<>();
        String sql = "SELECT * FROM docente";
        try{
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();            
            while(rs.next()){
                Docente d = new Docente();
                d.setId(rs.getInt(1));
                d.setPrimerNombre(rs.getString(2));
                d.setSegundoNombre(rs.getString(3));
                d.setApellidoPaterno(rs.getString(4));
                d.setApellidoMaterno(rs.getString(5));
                d.setGenero(rs.getString(6));
                d.getDocumentoIdentidad().setTipoDocumento(rs.getString(7));
                d.getDocumentoIdentidad().setNumeroDocumento(rs.getString(8));
                d.setTitulo(rs.getString(9));
                d.setTelefono(rs.getString(10));
                d.setCorreo(rs.getString(11));
                
                datos.add(d);
            }
        } catch(Exception e){
            System.err.println(e);
        }
        return datos;
    }
    
    @Override
    public boolean insertar(Docente d){
        String sql = "INSERT INTO docente(primerNombre,segundoNombre,apellidoPaterno,apellidoMaterno,genero,"
                + "tipoDocumento,numeroDocumento,titulo,telefono,correo) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, d.getPrimerNombre());
            ps.setString(2, d.getSegundoNombre());
            ps.setString(3, d.getApellidoPaterno());
            ps.setString(4, d.getApellidoMaterno());
            ps.setString(5, d.getGenero());
            ps.setString(6, d.getDocumentoIdentidad().getTipoDocumento());
            ps.setString(7, d.getDocumentoIdentidad().getNumeroDocumento());
            ps.setString(8, d.getTitulo());
            ps.setString(9, d.getTelefono());
            ps.setString(10, d.getCorreo());
            
            ps.executeUpdate();
            
            return true;
            
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
    
    
    @Override
    public int modificar(Docente d){
        int respuesta = 0; //para que la tabla se resetee cuando actualizamos datos
        String sql = "UPDATE docente set primerNombre=?, segundoNombre=?, apellidoPaterno=?, apellidoMaterno=?, "
                + "genero=?, tipoDocumento=?, numeroDocumento=?, titulo=?, telefono=?, correo=?"
                + " WHERE id=?";
        
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, d.getPrimerNombre());
            ps.setString(2, d.getSegundoNombre());
            ps.setString(3, d.getApellidoPaterno());
            ps.setString(4, d.getApellidoMaterno());
            ps.setString(5, d.getGenero());
            ps.setString(6, d.getDocumentoIdentidad().getTipoDocumento());
            ps.setString(7, d.getDocumentoIdentidad().getNumeroDocumento());
            ps.setString(8, d.getTitulo());
            ps.setString(9, d.getTelefono());
            ps.setString(10, d.getCorreo());
            ps.setInt(11, d.getId());
            
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
    
    
    public void Eliminar(int id){
        String sql = "DELETE FROM docente WHERE id="+id;
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    
    @Override
    public List<Docente> buscar(String apellidoPaterno){
        List<Docente> doce_busc = new ArrayList<>();//array para los docentes con coincidencia de busqueda

        String sql = "SELECT * FROM docente WHERE apellidoPaterno LIKE ?";
        try{
           
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, apellidoPaterno + "%");
            rs = ps.executeQuery();  
                        
            while(rs.next()){
                Docente d=new Docente();
                d.setId(rs.getInt(1));
                d.setPrimerNombre(rs.getString(2));
                d.setSegundoNombre(rs.getString(3));
                d.setApellidoPaterno(rs.getString(4));
                d.setApellidoMaterno(rs.getString(5));
                d.setGenero(rs.getString(6));
                d.getDocumentoIdentidad().setTipoDocumento(rs.getString(7));
                d.getDocumentoIdentidad().setNumeroDocumento(rs.getString(8));
                d.setTitulo(rs.getString(9));
                d.setTelefono(rs.getString(10));
                d.setCorreo(rs.getString(11));
                doce_busc.add(d); // Agregar el docente a la lista
            }
            return doce_busc;
        } catch(Exception e){
            System.err.println(e);
            return null;
        }
    }

    
}
