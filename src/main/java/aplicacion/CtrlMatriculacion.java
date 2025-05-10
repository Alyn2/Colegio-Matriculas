package aplicacion;

import dominio.entidades.Alumno;
import dominio.entidades.Grado;
import dominio.entidades.Matricula;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import persistencia.MatriculaDAO;
import presentacion.FrmMatriculacion;

public class CtrlMatriculacion implements ActionListener {

    private FrmMatriculacion viewMatriculacion;
    private Matricula matricula = new Matricula();
    private MatriculaDAO matriculaDao = new MatriculaDAO();

    public CtrlMatriculacion(FrmMatriculacion view) {
        this.viewMatriculacion = view;

        this.viewMatriculacion.btnNuevo.addActionListener(this);
        this.viewMatriculacion.btnGuardar.addActionListener(this);
        this.viewMatriculacion.btnCancelar.addActionListener(this);
        this.viewMatriculacion.btnGenerarReporte.addActionListener(this);

        this.viewMatriculacion.cboAlumno.setEnabled(false);
        this.viewMatriculacion.cboGrado.setEnabled(false);
        this.viewMatriculacion.btnGuardar.setEnabled(false);
        this.viewMatriculacion.btnCancelar.setEnabled(false);
    }

    public void iniciar() {
        viewMatriculacion.setTitle("Matriculacion");
        viewMatriculacion.setVisible(true);
    }

    

    public void guardar() {
        Alumno alumnoSeleccionado = (Alumno) viewMatriculacion.cboAlumno.getSelectedItem();
        int idAlumno = alumnoSeleccionado.getId();

        Grado gradoSeleccionado = (Grado) viewMatriculacion.cboGrado.getSelectedItem();
        int idGrado = gradoSeleccionado.getId();

        String anioAcademico = "2023";

        Matricula nuevaMatricula = new Matricula(idAlumno, idGrado,anioAcademico);

        MatriculaDAO m = new MatriculaDAO();
        if (m.insertar(nuevaMatricula)) {
                JOptionPane.showMessageDialog(viewMatriculacion, "Matricula registrada correctamente");
                
            } else {
                JOptionPane.showMessageDialog(viewMatriculacion, "Error, este alumno ya esta matriculado");
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewMatriculacion.btnNuevo) {
            this.viewMatriculacion.cboAlumno.setEnabled(true);
            this.viewMatriculacion.cboGrado.setEnabled(true);
            this.viewMatriculacion.btnGuardar.setEnabled(true);
            this.viewMatriculacion.btnCancelar.setEnabled(true);
            this.viewMatriculacion.btnNuevo.setEnabled(false);
            
            llenarComboBox();
        }
        if (e.getSource() == viewMatriculacion.btnGuardar) {                  
            this.viewMatriculacion.cboAlumno.setEnabled(false);
            this.viewMatriculacion.cboGrado.setEnabled(false);
            this.viewMatriculacion.btnGuardar.setEnabled(false);
            this.viewMatriculacion.btnCancelar.setEnabled(false);
            this.viewMatriculacion.btnNuevo.setEnabled(true);

            guardar();
            vaciarComboBox(this.viewMatriculacion.cboAlumno);
            vaciarComboBox(this.viewMatriculacion.cboGrado);
        }
        if (e.getSource() == viewMatriculacion.btnCancelar) {
            this.viewMatriculacion.cboAlumno.setEnabled(false);
            this.viewMatriculacion.cboGrado.setEnabled(false);
            this.viewMatriculacion.btnGuardar.setEnabled(false);
            this.viewMatriculacion.btnCancelar.setEnabled(false);
            this.viewMatriculacion.btnNuevo.setEnabled(true);

            vaciarComboBox(this.viewMatriculacion.cboAlumno);
            vaciarComboBox(this.viewMatriculacion.cboGrado);
        }
        if (e.getSource() == viewMatriculacion.btnGenerarReporte){
            matriculaDao.generarReporte();
        }
    }
    
    public void llenarComboBox(){
        MatriculaDAO m = new MatriculaDAO();
        
        DefaultComboBoxModel modeloAlumno = new DefaultComboBoxModel(m.mostrarAlumnos());
        viewMatriculacion.cboAlumno.setModel(modeloAlumno);
        
        DefaultComboBoxModel modeloGrado = new DefaultComboBoxModel(m.mostrarGrados());
        viewMatriculacion.cboGrado.setModel(modeloGrado);
    }

    public void vaciarComboBox(JComboBox comboBox) {
        comboBox.removeAllItems();
    }

}
