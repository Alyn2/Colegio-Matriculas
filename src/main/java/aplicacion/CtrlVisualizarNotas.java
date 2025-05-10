package aplicacion;

import dominio.entidades.Alumno;
import dominio.entidades.Curso;
import dominio.entidades.Nota;
import java.awt.Desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import persistencia.NotaDAO;
import presentacion.FrmvizualizarNotas;

public class CtrlVisualizarNotas implements ActionListener {

    private FrmvizualizarNotas viewVerNotas;
    private Nota nota = new Nota();
    private NotaDAO notaDao = new NotaDAO();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public CtrlVisualizarNotas(FrmvizualizarNotas view) {
        this.viewVerNotas = view;
        this.viewVerNotas.jBListar.addActionListener(this);
        this.viewVerNotas.jBExcelGenerator.addActionListener(this);
        this.viewVerNotas.jCboAlumnos.addActionListener(this);
        this.viewVerNotas.jBcancelar.addActionListener(this);
        this.viewVerNotas.jBeditar.addActionListener(this);
        this.viewVerNotas.jBnuevo.addActionListener(this);
        this.viewVerNotas.jBregistrar.addActionListener(this);
        this.viewVerNotas.jCcurso.addActionListener(this);

        this.viewVerNotas.jTbim1.addActionListener(this);
        this.viewVerNotas.jTbim2.addActionListener(this);
        this.viewVerNotas.jTbim3.addActionListener(this);
        this.viewVerNotas.jTbim4.addActionListener(this);
        this.viewVerNotas.jBmostrar.addActionListener(this);

        this.viewVerNotas.jBListar.setEnabled(true);
        this.viewVerNotas.jBExcelGenerator.setEnabled(false);
        llenadodecomboBox();
    }

    public void iniciar() {
        viewVerNotas.setTitle("Ver Notas");
        viewVerNotas.setVisible(true);
    }

    public void listar(JTable tabla) {

        this.modeloTabla = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < viewVerNotas.jTableNotas.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
        Alumno alumnoSeleccionado = (Alumno) viewVerNotas.jCboAlumnos.getSelectedItem();
        int IdAlumno = alumnoSeleccionado.getId();
        System.out.println(IdAlumno);
        List<Nota> lista = notaDao.listarNotas(IdAlumno);

        Object[] objeto = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getCurso().getNomcurso();
            objeto[1] = lista.get(i).getB1();
            objeto[2] = lista.get(i).getB2();
            objeto[3] = lista.get(i).getB3();
            objeto[4] = lista.get(i).getB4();
            objeto[5] = lista.get(i).getPromedio();
            objeto[6] = lista.get(i).getCondicion();

            modeloTabla.addRow(objeto); //agreamos todo al modelo
        }
        viewVerNotas.jTableNotas.setModel(modeloTabla);
    }

    public void mostrarNotas() {
        Alumno alumnoSeleccionado = (Alumno) viewVerNotas.jCboAlumnos.getSelectedItem();

        Curso cursoSeleccionado = (Curso) viewVerNotas.jCcurso.getSelectedItem();

        List<Double> lista = notaDao.obtenerNotas(alumnoSeleccionado.getId(), cursoSeleccionado.getId());

        System.out.println("mostrar notas");

        viewVerNotas.jTbim1.setText(String.valueOf(lista.get(0)));
        viewVerNotas.jTbim2.setText(String.valueOf(lista.get(1)));
        viewVerNotas.jTbim3.setText(String.valueOf(lista.get(2)));
        viewVerNotas.jTbim4.setText(String.valueOf(lista.get(3)));
    }

    public void llenarComboBox() {
        NotaDAO m = new NotaDAO();

        DefaultComboBoxModel modeloAlumno = new DefaultComboBoxModel(m.mostrarAlumnos());
        viewVerNotas.jCboAlumnos.setModel(modeloAlumno);

    }

    public void llenadodecomboBox() {
        NotaDAO m = new NotaDAO();

        DefaultComboBoxModel modeloAlumno = new DefaultComboBoxModel(m.mostrarAlumnos());
        viewVerNotas.jCboAlumnos.setModel(modeloAlumno);

        DefaultComboBoxModel modeloGrado = new DefaultComboBoxModel(m.mostrarCursos());
        viewVerNotas.jCcurso.setModel(modeloGrado);
    }

    public void vaciarComboBox(JComboBox comboBox) {
        comboBox.removeAllItems();
    }

    public void insertar() {
        Alumno alumnoSeleccionado = (Alumno) viewVerNotas.jCboAlumnos.getSelectedItem();
        Curso cursoSeleccionado = (Curso) viewVerNotas.jCcurso.getSelectedItem();

        double b1 = Double.parseDouble(viewVerNotas.jTbim1.getText());
        double b2 = Double.parseDouble(viewVerNotas.jTbim2.getText());
        double b3 = Double.parseDouble(viewVerNotas.jTbim3.getText());
        double b4 = Double.parseDouble(viewVerNotas.jTbim4.getText());

        if (b1 < 0.0 || b1 > 20.0 || b2 < 0.0 || b2 > 20.0 || b3 < 0.0 || b3 > 20.0 || b4 < 0.0 || b4 > 20.0) {
            JOptionPane.showMessageDialog(viewVerNotas, "Error, las notas deben estar entre 0 y 20");
        } else {
            double promedio = Math.round(((b1 + b2 + b3 + b4) / 4) * 100.0) / 100.0;
            String condicion = "";
            if (promedio >= 11.0) {
                condicion = "aprobado";
            } else {
                condicion = "desaprobado";
            }

            Nota newnotas = new Nota(0, cursoSeleccionado, alumnoSeleccionado, b1, b2, b3, b4, promedio, condicion);

            NotaDAO m = new NotaDAO();
            if (m.insertar(newnotas)) {
                JOptionPane.showMessageDialog(viewVerNotas, "Notas registradas correctamente");
                this.viewVerNotas.jCcurso.setEnabled(false);
                this.viewVerNotas.jTbim1.setEnabled(false);
                this.viewVerNotas.jTbim2.setEnabled(false);
                this.viewVerNotas.jTbim3.setEnabled(false);
                this.viewVerNotas.jTbim4.setEnabled(false);
                this.viewVerNotas.jBExcelGenerator.setEnabled(true);
                viewVerNotas.jtpVisualizarNotas.setSelectedIndex(0);
                listar(viewVerNotas.jTableNotas);
            } else {
                JOptionPane.showMessageDialog(viewVerNotas, "Error, Este alumno ya tiene nota");
            }
        }
    }

    public void Modificar() {
        Alumno alumnoSeleccionado = (Alumno) viewVerNotas.jCboAlumnos.getSelectedItem();
        Curso cursoSeleccionado = (Curso) viewVerNotas.jCcurso.getSelectedItem();

        double b1 = Double.parseDouble(viewVerNotas.jTbim1.getText());
        double b2 = Double.parseDouble(viewVerNotas.jTbim2.getText());
        double b3 = Double.parseDouble(viewVerNotas.jTbim3.getText());
        double b4 = Double.parseDouble(viewVerNotas.jTbim4.getText());

        if (b1 < 0.0 || b1 > 20.0 || b2 < 0.0 || b2 > 20.0 || b3 < 0.0 || b3 > 20.0 || b4 < 0.0 || b4 > 20.0) {
            JOptionPane.showMessageDialog(viewVerNotas, "Error, las notas deben estar entre 0 y 20");
        } else {
            double promedio = Math.round(((b1 + b2 + b3 + b4) / 4) * 100.0) / 100.0;
            String condicion = "";
            if (promedio >= 11.0) {
                condicion = "aprobado";
            } else {
                condicion = "desaprobado";
            }

            Nota editnotas = new Nota(0, cursoSeleccionado, alumnoSeleccionado, b1, b2, b3, b4, promedio, condicion);
            System.out.println("llegue MODIFICAR");
            NotaDAO m = new NotaDAO();
            if (m.editarNota(editnotas)) {
                JOptionPane.showMessageDialog(viewVerNotas, "Notas actualizada correctamente");
                this.viewVerNotas.jCboAlumnos.setEnabled(true);
                this.viewVerNotas.jCcurso.setEnabled(false);
                this.viewVerNotas.jTbim1.setEnabled(false);
                this.viewVerNotas.jTbim2.setEnabled(false);
                this.viewVerNotas.jTbim3.setEnabled(false);
                this.viewVerNotas.jTbim4.setEnabled(false);
                this.viewVerNotas.jBExcelGenerator.setEnabled(false);
                viewVerNotas.jtpVisualizarNotas.setSelectedIndex(1);
            } else {
                JOptionPane.showMessageDialog(viewVerNotas, "Error, no se pudo actualizar");
            }
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

        if (e.getSource() == viewVerNotas.jBListar) {
            this.viewVerNotas.jBExcelGenerator.setEnabled(true);
            this.viewVerNotas.jBregistrar.setEnabled(true);
            this.viewVerNotas.jCcurso.setEnabled(false);
            this.viewVerNotas.jTbim1.setEnabled(false);
            this.viewVerNotas.jTbim2.setEnabled(false);
            this.viewVerNotas.jTbim3.setEnabled(false);
            this.viewVerNotas.jTbim4.setEnabled(false);
            listar(viewVerNotas.jTableNotas);
            limpiarCasilleros();
            viewVerNotas.jtpVisualizarNotas.setSelectedIndex(0);

        }

        if (e.getSource() == viewVerNotas.jBnuevo) {
            this.viewVerNotas.jCcurso.setEnabled(true);
            this.viewVerNotas.jTbim1.setEnabled(true);
            this.viewVerNotas.jTbim2.setEnabled(true);
            this.viewVerNotas.jTbim3.setEnabled(true);
            this.viewVerNotas.jTbim4.setEnabled(true);
            this.viewVerNotas.jBregistrar.setEnabled(true);
            this.viewVerNotas.jBExcelGenerator.setEnabled(false);
            limpiarCasilleros();
            viewVerNotas.jtpVisualizarNotas.setSelectedIndex(1);
        }

        if (e.getSource() == viewVerNotas.jBcancelar) {
            this.viewVerNotas.jCboAlumnos.setEnabled(false);
            this.viewVerNotas.jCcurso.setEnabled(false);
            this.viewVerNotas.jTbim1.setEnabled(false);
            this.viewVerNotas.jTbim2.setEnabled(false);
            this.viewVerNotas.jTbim3.setEnabled(false);
            this.viewVerNotas.jTbim4.setEnabled(false);
            this.viewVerNotas.jBExcelGenerator.setEnabled(true);
            limpiarCasilleros();
            viewVerNotas.jtpVisualizarNotas.setSelectedIndex(0);
        }

        if (e.getSource() == viewVerNotas.jBregistrar) {
            insertar();            
        }

        if (e.getSource() == viewVerNotas.jBeditar) {
            Modificar();
        }

        if (e.getSource() == viewVerNotas.jBmostrar) {
            viewVerNotas.jtpVisualizarNotas.setSelectedIndex(1);
            mostrarNotas();
        }
         if (e.getSource() == viewVerNotas.jBExcelGenerator) {
            generarexcel(viewVerNotas.jTableNotas);
        }

    }

    public void limpiarCasilleros() {
        viewVerNotas.jTbim1.setText("");
        viewVerNotas.jTbim2.setText("");
        viewVerNotas.jTbim3.setText("");
        viewVerNotas.jTbim4.setText("");
    }

}
