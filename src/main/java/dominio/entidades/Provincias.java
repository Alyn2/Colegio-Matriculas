package dominio.entidades;
import java.sql.*;
import java.util.Vector;
import persistencia.Conexion;

public class Provincias {
    private int id;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    //MÉTODOS MANUEALES
    public String toString(){
        return this.nombre;
    }
    
/*método para agregar los valores al combobox (asignamos los indice como id
  y el estado como el nombre que saldrá en el combobox)*/
    public Vector<Provincias> mostarProvincias(Integer idDepartamento){
        //Cargamos todos los estados 
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion con = new Conexion(); //Objeto de la clase Conexion
        Connection conn = con.getConexion(); //Aquí llamamos la conexion
        
        Vector<Provincias> datos = new Vector<Provincias>(); //Objeto arreglo de objetos
        Provincias dat = null; //Variable de tipo de una clase, y xq lo usaremos 2 veces
        
        try{
            //Escribimos la consulta                ESTO VARIA EN LA CONSULTA
            String sql = "SELECT * FROM provincias WHERE idDepa=" + idDepartamento;
            ps = conn.prepareStatement(sql); //preparamos la consulta
            //ejecutamos la consulta y lo guardamos en la variable rs
            rs = ps.executeQuery();
            
            //Agregamos la opción de seleccionar nomás
            dat = new Provincias();
            dat.setId(0);
            dat.setNombre("<Seleccionar>");
            datos.add(dat);
            
            //Para recorrer todas los datos de la BD
            while(rs.next()){
                dat = new Provincias();
                dat.setId(rs.getInt("idProv"));
                dat.setNombre(rs.getString("Provincia"));
                datos.add(dat);
            }
            
            rs.close(); //Cerramos los resultados
            
        } catch(SQLException e){
            System.err.println(e.toString());
        }
        return datos;
    }
}
