package dominio.entidades;
import java.sql.*;
import java.util.Vector;
import persistencia.Conexion;

public class Distritos {
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
    public Vector<Distritos> mostarDistritos(Integer idProvincia){
        //Cargamos todos los estados 
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion con = new Conexion(); //Objeto de la clase Conexion
        Connection conn = con.getConexion(); //Aquí llamamos la conexion
        
        Vector<Distritos> datos = new Vector<Distritos>(); //Objeto arreglo de objetos
        Distritos dat = null; //Variable de tipo de una clase, y xq lo usaremos 2 veces
        
        try{
            //Escribimos la consulta                ESTO VARIA EN LA CONSULTA
            String sql = "SELECT * FROM distritos WHERE idProv=" + idProvincia;
            ps = conn.prepareStatement(sql); //preparamos la consulta
            //ejecutamos la consulta y lo guardamos en la variable rs
            rs = ps.executeQuery();
            
            //Agregamos la opción de seleccionar nomás
            dat = new Distritos();
            dat.setId(0);
            dat.setNombre("<Seleccionar>");
            datos.add(dat);
            
            //Para recorrer todas los datos de la BD
            while(rs.next()){
                dat = new Distritos();
                dat.setId(rs.getInt("idDist"));
                dat.setNombre(rs.getString("Distrito"));
                datos.add(dat);
            }
            
            rs.close(); //Cerramos los resultados
            
        } catch(SQLException e){
            System.err.println(e.toString());
        }
        return datos;
    }
}
