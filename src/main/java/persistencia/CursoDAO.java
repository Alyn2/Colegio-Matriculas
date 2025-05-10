package persistencia;

import dominio.entidades.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO implements DAO<Curso, String> {

    Conexion conectar = new Conexion(); //Como no heredo necesito instanciar
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List listar() {
        List<Curso> datos = new ArrayList<>();
        String sql = "SELECT * FROM cursos";
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Curso c = new Curso();
                c.setId(rs.getInt(1));
                c.setNomcurso(rs.getString(2));
                c.getDocente().setId(rs.getInt(3));
                c.setCreditos(rs.getInt(4));
                c.setHoras(rs.getInt(5));
                c.getGrado().setId(rs.getInt(6));
                datos.add(c);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return datos;
    }

    @Override
    public boolean insertar(Curso c) {
        String sql = "INSERT INTO cursos(nombreCurso,id_docente,creditos,horas,id_grados) VALUES (?,?,?,?,?)";
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);

            ps.setString(1, c.getNomcurso());
            ps.setInt(2, c.getDocente().getId());
            ps.setInt(3, c.getCreditos());
            ps.setInt(4, c.getHoras());
            ps.setInt(5, c.getGrado().getId());
            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public int modificar(Curso c) {
        int respuesta = 0; //para que la tabla se resetee cuando actualizamos datos
        String sql = "UPDATE cursos set nombreCurso=?, id_docente=?, creditos=?, horas=?, id_grados=? "
                + " WHERE id=?";

        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);

            ps.setString(1, c.getNomcurso());
            ps.setInt(2, c.getDocente().getId());
            ps.setInt(3, c.getCreditos());
            ps.setInt(4, c.getHoras());
            ps.setInt(5, c.getGrado().getId());
            ps.setInt(6, c.getId());

            respuesta = ps.executeUpdate();

            if (respuesta == 1) {
                return 1;
            } else {
                return 0;
            }

        } catch (Exception e) {
            System.err.println("");
        }

        return respuesta;
    }

    public void Eliminar(int id) {
        String sql = "DELETE FROM cursos WHERE id=" + id;
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /*
    @Override
    public Curso buscar(String nombrecurso){
        Curso c = null;
        String sql = "SELECT * FROM cursos WHERE nombreCurso LIKE ?";
        try{
            c = new Curso();
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nombrecurso + "%");
            rs = ps.executeQuery();  
                        
            while(rs.next()){
                c.setId(rs.getInt(1));
                c.setCurso(rs.getString(2));
                c.getDocente().setId(rs.getInt(3));
                c.setCreditos(rs.getInt(4));
                c.setHoras(rs.getInt(5));
                c.getGrado().setId(rs.getInt(6));
            }
            return c;
        } catch(Exception e){
            System.err.println(e);
            return null;
        }
    }*/

@Override
public List<Curso> buscar(String nombrecurso) {
    List<Curso> cur_busc = new ArrayList<>();//array para los cursos con coincidencia de busqueda
    String sql = "SELECT * FROM cursos WHERE nombreCurso LIKE ?";
    try {
        conn = conectar.getConexion();
        ps = conn.prepareStatement(sql);
        ps.setString(1, nombrecurso + "%");
        rs = ps.executeQuery();
        while (rs.next()) {
            Curso c = new Curso();
            c.setId(rs.getInt(1));
            c.setNomcurso(rs.getString(2));
            c.getDocente().setId(rs.getInt(3));
            c.setCreditos(rs.getInt(4));
            c.setHoras(rs.getInt(5));
            c.getGrado().setId(rs.getInt(6));
            cur_busc.add(c);
        }
    } catch (Exception e) {
        System.err.println(e);
    }
    return cur_busc;
}
}
