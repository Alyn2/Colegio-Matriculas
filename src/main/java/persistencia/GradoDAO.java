package persistencia;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dominio.entidades.Grado;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


//ESTE TIENE GENERAR REPORTE EN PDF
public class GradoDAO implements DAO<Grado, String> {

    Conexion conectar = new Conexion(); //Como no heredo necesito instanciar
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List listar() {
        List<Grado> datos = new ArrayList<>();
        String sql = "SELECT * FROM grados";
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Grado g = new Grado();
                g.setId(rs.getInt(1));
                g.setGrado(rs.getString(2));
                g.setSeccion(rs.getString(3));
                datos.add(g);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return datos;
    }

    @Override
    public boolean insertar(Grado g) {
        String sql = "INSERT INTO grados(id,grado,seccion) VALUES (?,?,?)";

        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, g.getId());

            ps.setString(2, g.getGrado());
            ps.setString(3, g.getSeccion());

            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    @Override
    public int modificar(Grado g) {
        int respuesta = 0; //para que la tabla se resetee cuando actualizamos datos
        String sql = "UPDATE grados set id=?, grado=?, seccion=?" + " WHERE id=?";

        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, g.getId());
            ps.setString(2, g.getGrado());
            ps.setString(3, g.getSeccion());
            ps.setInt(4, g.getId());

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
        String sql = "DELETE FROM grados WHERE id=" + id;
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public List<Grado> buscar(String nombregrado) {
        List<Grado> grad_busc = new ArrayList<>();//array para los grados con coincidencia de busqueda
        String sql = "SELECT * FROM grados WHERE grado LIKE ?";
        try {

            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombregrado + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Grado g = new Grado();
                g.setId(rs.getInt(1));
                g.setGrado(rs.getString(2));
                g.setSeccion(rs.getString(3));
                grad_busc.add(g); // Agregar el grado a la lista
            }
            return grad_busc;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }
    }

    
    //FALTA ADAPTARLO AL MVC
    public void generarReporte() {
        Document documento = new Document();

        try {
            String ruta = System.getProperty("user.home");
            //DOCUMENTO CON NOMBRE ESPECIFICO
            String nombre = JOptionPane.showInputDialog(null, "Ingresa nombre del documento");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "\\Documents\\NetBeansProjects//"+nombre+".pdf"));
            documento.open();

            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Codigo");
            tabla.addCell("Grado");
            tabla.addCell("Seccion");

            try {
                conn = conectar.getConexion();
                String sql = "SELECT * FROM grados";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    do {
                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
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

    public Grado buscarPorId(int id) {
        Grado grado = null;
        try {
            conn = conectar.getConexion();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM grados WHERE id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                grado = new Grado();
                grado.setId(rs.getInt(1));
                grado.setGrado(rs.getString(2));
                grado.setSeccion(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grado;
    }

}
