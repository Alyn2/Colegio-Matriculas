package persistencia;

import dominio.entidades.Alumno;
import dominio.entidades.Curso;
import dominio.entidades.Nota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class NotaDAO {

    Conexion conectar = new Conexion(); //Como no heredo necesito instanciar
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List listarNotas(int IdAlumno) {
        List<Nota> datos = new ArrayList<>();
        String sql = "SELECT c.nombreCurso, n.1b, n.2b, n.3b, n.4b, n.promedio, n.condicion FROM alumno a JOIN nota n ON a.id = n.idAlumno JOIN cursos c ON n.idCurso = c.id WHERE a.id = ?;";
    
        try {
    
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, IdAlumno);
            rs = ps.executeQuery();
    
            while (rs.next()) {
                Nota n = new Nota();
    
                Curso curso = new Curso();
                curso.setNomcurso(rs.getString(1));
    
                n.setCurso(curso);
                n.setB1(rs.getDouble(2));
                n.setB2(rs.getDouble(3));
                n.setB3(rs.getDouble(4));
                n.setB4(rs.getDouble(5));
                n.setPromedio(); //Estos 2 metodos vienen ya con un valor calculado en la clase Nota
                n.setCondicion();
    
                datos.add(n);
            }
            return datos;
        } catch (Exception e) {
            System.err.println(e);
            return null;
    
        }
    }

    public Vector<Alumno> mostrarAlumnos() {
        Vector<Alumno> datos = new Vector<>();
        Alumno dat = null;

        try {
            String sql = "SELECT * FROM alumno";
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                dat = new Alumno();
                dat.setId(rs.getInt("id"));
                dat.setPrimerNombre(rs.getString("primerNombre"));
                dat.setApellidoPaterno(rs.getString("apellidoPaterno"));
                dat.setApellidoMaterno(rs.getString("apellidoMaterno"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return datos;
    }

    public Vector<Curso> mostrarCursos() {
        Vector<Curso> datos = new Vector<>();
        Curso dat = null;

        try {
            String sql = "SELECT * FROM Cursos";
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                dat = new Curso();
                dat.setId(rs.getInt("id"));
                dat.setNomcurso(rs.getString("nombreCurso"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return datos;
    }

    public boolean insertar(Nota nota) {
        try {
            conn = conectar.getConexion();
    
            double promedio = (nota.getB1() + nota.getB2() + nota.getB3() + nota.getB4()) / 4;
    
            ps = conn.prepareStatement("INSERT INTO nota (idCurso, idAlumno, 1b, 2b, 3b, 4b, promedio, condicion) VALUES (?,?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, nota.getCurso().getId());
            ps.setInt(2, nota.getAlumno().getId());
            ps.setDouble(3, nota.getB1());
            ps.setDouble(4, nota.getB2());
            ps.setDouble(5, nota.getB3());
            ps.setDouble(6, nota.getB4());
            ps.setDouble(7, promedio);
            ps.setString(8, nota.getCondicion()); 
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Double> obtenerNotas(int idAlumno, int idCurso) {
        List<Double> notas = new ArrayList<>();
        String sql = "SELECT 1b, 2b , 3b , 4b FROM nota WHERE idAlumno = ? AND idCurso = ?;";
    
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idCurso);
            rs = ps.executeQuery();
    
            if (rs.next()) {
                notas.add(rs.getDouble("1b"));
                notas.add(rs.getDouble("2b"));
                notas.add(rs.getDouble("3b"));
                notas.add(rs.getDouble("4b"));
            }
    
            return notas;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    } 
    
    public boolean editarNota(Nota nota) {
        try {
            conn = conectar.getConexion();
    
            double promedio = (nota.getB1() + nota.getB2() + nota.getB3() + nota.getB4()) / 4;
    
            ps = conn.prepareStatement("UPDATE nota SET 1b = ?, 2b = ?, 3b = ?, 4b = ?, promedio = ?, condicion=? WHERE idCurso = ? AND idAlumno = ?");
            ps.setDouble(1, nota.getB1());
            ps.setDouble(2, nota.getB2());
            ps.setDouble(3, nota.getB3());
            ps.setDouble(4, nota.getB4());
            ps.setDouble(5, promedio);
            ps.setString(6, nota.getCondicion()); 
            ps.setInt(7, nota.getCurso().getId());
            ps.setInt(8, nota.getAlumno().getId());            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
