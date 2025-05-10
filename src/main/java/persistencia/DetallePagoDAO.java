package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dominio.entidades.Alumno;
import dominio.entidades.DetallePago;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DetallePagoDAO {
    Conexion conectar = new Conexion(); //Como no heredo necesito instanciar
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

    public List listar(){
        List<DetallePago> datos = new ArrayList<>();
        String sql = "SELECT dp.id, a.primerNombre, dp.tipoPago, dp.montoAbonado, p.montoTotal " +
                        "FROM detallepago AS dp " +
                        "INNER JOIN pago AS p ON dp.id_pago = p.id " +
                        "INNER JOIN alumno AS a ON p.id_alumno = a.id ";
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetallePago d = new DetallePago();
                d.setId(rs.getInt(1));
                d.getPago().getAlumno().setPrimerNombre(rs.getString(2));
                d.getTipoPago().setDescripcion(rs.getString(3));
                d.setMontoAbonado(rs.getDouble(4));
                d.realizarPago(); //Para que disminuya lo restante a pagar
                d.getPago().setMontoTotal(rs.getDouble(5));
                datos.add(d);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return datos;
    }


    public boolean insertar(DetallePago d) {
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement("INSERT INTO detallepago (id_pago, tipoPago, montoAbonado) VALUES (?,?,?)");
            ps.setInt(1, d.getPago().getId());
            ps.setString(2, d.getTipoPago().getDescripcion());
            ps.setDouble(3, d.getMontoAbonado());
            ps.executeUpdate();
    
            // Reutilizar la misma variable ps para preparar la segunda consulta
            ps = conn.prepareStatement("UPDATE pago SET montoTotal = ? WHERE id = ?");
            ps.setDouble(1, d.getPago().getMontoTotal());
            ps.setInt(2, d.getPago().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public int obtenerIdPagoPorIdAlumno(int idAlumno) {
        String sql = "SELECT id FROM pago WHERE id_alumno = ?";
        int idPago = 0;
        try {
            conn = conectar.getConexion();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            rs = ps.executeQuery();
            if (rs.next()) {
                idPago = rs.getInt("id");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return idPago;
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
}
