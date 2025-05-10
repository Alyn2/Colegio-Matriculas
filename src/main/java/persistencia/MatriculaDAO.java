package persistencia;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dominio.entidades.Alumno;
import dominio.entidades.Grado;
import dominio.entidades.Matricula;
import java.awt.Font;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;

public class MatriculaDAO {
    Conexion conectar = new Conexion(); //Como no heredo necesito instanciar
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List listar() {
        List<Matricula> datos = new ArrayList<>();
        String sql = "SELECT m.id, a.primerNombre, a.segundoNombre, a.apellidoPaterno, a.apellidoMaterno,"
                + " g.grado, m.anioAcademico"
                + " FROM matriculas m JOIN alumno a ON m.id_alumno = a.id JOIN grados g ON m.id_grado = g.id";
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Matricula m = new Matricula();
                
                m.setId(rs.getInt(1));
                m.getAlumno().setPrimerNombre(rs.getString(2));
                m.getAlumno().setSegundoNombre(rs.getString(3));
                m.getAlumno().setApellidoPaterno(rs.getString(4));
                m.getAlumno().setApellidoMaterno(rs.getString(5));
                m.getGrado().setGrado(rs.getString(6));
                m.setAnioAcademico(rs.getString(7));
                                
                datos.add(m);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return datos;
    }

    /*public boolean insertar(Matricula m) {
        String sql = "INSERT INTO matriculas(id_alumno,id_grado,anioAcademico) VALUES (?,?,?)";
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, m.getAlumno().getId());
            ps.setInt(2, m.getGrado().getId());
            ps.setString(3, m.getAnioAcademico());
            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }*/

    public boolean insertar(Matricula matricula) {
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement("SELECT * FROM matriculas WHERE id_alumno = ?");
            ps.setInt(1, matricula.getAlumno().getId());
            rs = ps.executeQuery();

            if (rs.next()) {
                return false;
            }
    
            ps = conn.prepareStatement("INSERT INTO matriculas (id_alumno, id_grado, anioAcademico) VALUES (?, ?, ?)");
            ps.setInt(1, matricula.getAlumno().getId());
            ps.setInt(2, matricula.getGrado().getId());
            ps.setString(3, matricula.getAnioAcademico());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    
    
    public Vector<Alumno> mostrarAlumnos(){
        Vector<Alumno> datos = new Vector<>();
        Alumno dat = null;
        
        try {
            String sql = "SELECT * FROM alumno";
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
                        
            while(rs.next()){
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
    
    
    public Vector<Grado> mostrarGrados(){
        Vector<Grado> datos = new Vector<>();
        Grado dat = null;
        
        try {
            String sql = "SELECT * FROM grados";
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
                        
            while(rs.next()){
                dat = new Grado();
                dat.setId(rs.getInt("id"));
                dat.setGrado(rs.getString("grado"));
                dat.setSeccion(rs.getString("seccion"));
                datos.add(dat);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        return datos;
    }
    
    
    public void generarReporte() {
        Document documento = new Document(PageSize.A2.rotate());

        try {
            String ruta = System.getProperty("user.home");
            //DOCUMENTO CON NOMBRE ESPECIFICO
            String nombre = JOptionPane.showInputDialog(null, "Ingresa nombre del documento");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "\\OneDrive\\Documentos\\NetBeansProjects\\"+nombre+".pdf"));
            documento.open();

            // Crear el título y centrarlo
            Paragraph titulo = new Paragraph("REPORTE DE ALUMNOS MATRICULADOS", FontFactory.getFont("arial",22,Font.BOLD,BaseColor.BLACK));
            titulo.setAlignment(Element.ALIGN_CENTER);
            // Agregar el título al documento
            documento.add(titulo);

            // Agregar un espacio después del título
            Paragraph espacio = new Paragraph("\n");
            documento.add(espacio);
            
            PdfPTable tabla = new PdfPTable(12);
            tabla.addCell("Id matricula");
            tabla.addCell("Alumno");
            tabla.addCell("DNI");
            tabla.addCell("Departamento");
            tabla.addCell("Provincia");
            tabla.addCell("Distrito");
            tabla.addCell("Dirección");
            tabla.addCell("Apoderado");
            tabla.addCell("Celular");
            tabla.addCell("Grado");
            tabla.addCell("Sección");
            tabla.addCell("Año académico");

            try {
                conn = conectar.getConexion();
                
                String sql = "SELECT m.id, a.primerNombre, a.segundoNombre, a.apellidoPaterno, a.apellidoMaterno, "
                        + "a.numeroDocumento, a.departamento, a.provincia, a.distrito, a.direccion, ap.primerNombre, ap.segundoNombre, ap.apellidoPaterno, "
                        + "ap.apellidoMaterno, ap.celular, g.grado, g.seccion, m.anioAcademico"
            + " FROM matriculas m JOIN alumno a ON m.id_alumno = a.id JOIN grados g ON m.id_grado = g.id JOIN apoderado ap ON a.id_apoderado = ap.id";
                
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        tabla.addCell(rs.getString(7));
                        tabla.addCell(rs.getString(8));
                        tabla.addCell(rs.getString(9));
                        tabla.addCell(rs.getString(10));
                        tabla.addCell(rs.getString(11)+" "+rs.getString(12)+" "+rs.getString(13)+" "+rs.getString(14));
                        tabla.addCell(rs.getString(15));
                        tabla.addCell(rs.getString(16));
                        tabla.addCell(rs.getString(17));
                        tabla.addCell(rs.getString(18));
                        
                    } while (rs.next());
                    documento.add(tabla);
                }
            } catch (DocumentException | SQLException e) {
                System.err.println(e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null,"Reporte creado");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
}
