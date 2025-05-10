package aplicacion;

import dominio.entidades.Alumno;
import dominio.entidades.DetallePago;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import persistencia.DetallePagoDAO;
import presentacion.FrmPagos;

public class CtrlPagos implements ActionListener{

    private FrmPagos viewPagos;
    private DetallePago detallePago = new DetallePago();
    private DetallePagoDAO detallePagoDAO = new DetallePagoDAO();
    DefaultTableModel modeloTabla = new DefaultTableModel();
    
    public CtrlPagos(FrmPagos view){
        this.viewPagos = view;
        
        this.viewPagos.btnRegistrar.addActionListener(this);
        this.viewPagos.btnListar.addActionListener(this);
        this.viewPagos.btnCancelar.addActionListener(this);
        this.viewPagos.jBgenerar.addActionListener(this);
        
        listar(viewPagos.jtResumen);

        llenarComboBox();
    }
    
    public void iniciar() {
        viewPagos.setTitle("Pagos");
        viewPagos.setVisible(true);
    }

    public void listar(JTable tabla) {
        this.modeloTabla = (DefaultTableModel) tabla.getModel(); //para que se vea la tabla al ejecutar
        List<DetallePago> lista = detallePagoDAO.listar(); //para que liste en la tabla
        Object[] objeto = new Object[5]; //[5]ponemos la cantidad de columnas
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getPago().getAlumno().getPrimerNombre();
            objeto[2] = lista.get(i).getTipoPago().getDescripcion();
            objeto[3] = lista.get(i).getMontoAbonado();
            objeto[4] = lista.get(i).getPago().getMontoTotal();
            modeloTabla.addRow(objeto); //agreamos todo al modelo
        }
        //Para que todo se muestre en el JTable
        viewPagos.jtResumen.setModel(modeloTabla);
    }
    
    public void guardar(){
        Alumno alumnoSeleccionado = (Alumno) viewPagos.cboAlumno.getSelectedItem();
        int idAlumno = alumnoSeleccionado.getId();

        String tipoPago = viewPagos.cboTipoPago.getSelectedItem().toString();
        double montoAbonado = Double.parseDouble(viewPagos.txtMontoAbonado.getText());

        detallePago.getPago().setId(idAlumno);
        detallePago.getTipoPago().setDescripcion(tipoPago);
        detallePago.setMontoAbonado(montoAbonado);
        detallePago.realizarPago();
        //detallePago.getPago().getMontoTotal(); ver si es necesario
    
        if(detallePagoDAO.insertar(detallePago)){
            JOptionPane.showMessageDialog(viewPagos,"Se registro correctamente");
        } else {
            JOptionPane.showMessageDialog(viewPagos,"Error al registrar");
        }
    }
    
     public void generarexcel(JTable miTabla) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar CSV"); // Título del diálogo
            fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv"));

            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File csvFile = fileChooser.getSelectedFile();
                if (!csvFile.getName().endsWith(".csv")) {
                    csvFile = new File(csvFile.getAbsolutePath() + ".csv");
                }

                FileWriter csvWriter = new FileWriter(csvFile);

                // Especificar el separador de campo personalizado (punto y coma)
                 CSVPrinter csvPrinter = new CSVPrinter(csvWriter, CSVFormat.DEFAULT.withDelimiter(';'));
                //CSVPrinter csvPrinter = new CSVPrinter(csvWriter, CSVFormat.DEFAULT);

                // Escribir el encabezado
                try {
                    DefaultTableModel modeloTabla = (DefaultTableModel) miTabla.getModel();
                    int numFilas = modeloTabla.getRowCount();
                    int numColumnas = modeloTabla.getColumnCount();

                    // Escribir el encabezado
                    for (int j = 0; j < numColumnas; j++) {
                        csvPrinter.print(modeloTabla.getColumnName(j));
                    }
                    csvPrinter.println(); // Salto de línea después del encabezado

                    // Escribir datos de la JTable
                    for (int i = 0; i < numFilas; i++) {
                        for (int j = 0; j < numColumnas; j++) {
                            Object dato = modeloTabla.getValueAt(i, j);
                            csvPrinter.print(dato);
                        }
                        csvPrinter.println(); // Salto de línea después de cada fila
                    }

                    csvPrinter.flush();
                    csvPrinter.close();

                    JOptionPane.showMessageDialog(null, "Reporte CSV creado");

                    // Abrir el archivo CSV con la aplicación predeterminada (Excel)
                    Desktop.getDesktop().open(csvFile);
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewPagos.btnRegistrar) {
            guardar();
            limpiarTabla();
            listar(viewPagos.jtResumen);
        }
        if (e.getSource() == viewPagos.btnListar) {
            limpiarTabla();
            listar(viewPagos.jtResumen);
        }
        if (e.getSource() == viewPagos.btnCancelar) {
            limpiarTabla();
            listar(viewPagos.jtResumen);
        }
          if (e.getSource() == viewPagos.jBgenerar) {
            generarexcel(viewPagos.jtResumen);
        }
    }
    

    public void llenarComboBox(){
        DetallePagoDAO d = new DetallePagoDAO();
        
        DefaultComboBoxModel modeloAlumno = new DefaultComboBoxModel(d.mostrarAlumnos());
        viewPagos.cboAlumno.setModel(modeloAlumno);
    }
    
    public void limpiarTabla(){
        for (int i = 0; i < viewPagos.jtResumen.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i=i-1;
        } 
    }
}
