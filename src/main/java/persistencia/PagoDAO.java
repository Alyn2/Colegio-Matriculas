package persistencia;

import dominio.entidades.Matricula;
import dominio.entidades.Pago;
import dominio.servicios.Pago_aniooEscolar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class PagoDAO {

    Conexion conectar = new Conexion(); //Como no heredo necesito instanciar
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;

   public List<Pago> listar() {
    // Create a list to store Matricula objects
    List<Pago> datos = new ArrayList<>();

    // SQL query to retrieve data from the database
    String sql = "SELECT m.id_pago, a.primerNombre, a.segundoNombre, a.apellidoPaterno, a.apellidoMaterno,"
            + " p.tipoPago, p.montoAbonado, p.montoTotal"
            + " FROM matriculas m"
            + " JOIN alumno a ON m.id_alumno = a.id"
            + " JOIN pagos p ON m.id_pago = p.id_pago";

    try {
        // Establish a database connection
        conn = conectar.getConexion();

        // Prepare a SQL statement
        ps = conn.prepareStatement(sql);

        // Execute the query and get the result set
        rs = ps.executeQuery();

        // Iterate through the result set and populate Matricula objects
        while (rs.next()) {
            Pago p = new Pago();

            // Set values for the Matricula object from the result set
            p.setId(rs.getInt(1));
            p.getAlumno().setPrimerNombre(rs.getString(2));
            p.getAlumno().setSegundoNombre(rs.getString(3));
            p.getAlumno().setApellidoPaterno(rs.getString(4));
            p.getAlumno().setApellidoMaterno(rs.getString(5));
            p.setMontoTotal(rs.getDouble(8));

            // Add the populated Matricula object to the list
            datos.add(p);
        }
    } catch (Exception e) {
        // Handle any exceptions that may occur during database operations
        System.err.println(e);
    } finally {
        // Close the database resources in a finally block to ensure they are closed
        // This is important for preventing resource leaks
        // Close the result set, prepared statement, and connection
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    // Return the list of Matricula objects
    return datos;

   }
}