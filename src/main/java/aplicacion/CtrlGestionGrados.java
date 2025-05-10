package aplicacion;

import dominio.entidades.Grado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dominio.entidades.Validacion;
import javax.swing.JComboBox;
import persistencia.GradoDAO;
import presentacion.FrmGestionGrados;
//Importar reporta
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;


public class CtrlGestionGrados implements ActionListener {

    Grado grado = new Grado(); //modelo
    GradoDAO gradoDao = new GradoDAO(); //modeValidacionUsuarioidacion valValidacionUsuarionew Validacion();
    FrmGestionGrados viewgrados = new FrmGestionGrados(); //vista
    Validacion validacion = new Validacion();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public CtrlGestionGrados() {
    }

    public CtrlGestionGrados(FrmGestionGrados view) {
        this.viewgrados = view;
        //BOTONES
        this.viewgrados.btnNuevo.addActionListener(this);
        this.viewgrados.btnGuardar.addActionListener(this);
        this.viewgrados.btnEditar.addActionListener(this);
        this.viewgrados.btnActualizar.addActionListener(this);
        this.viewgrados.btnEliminar.addActionListener(this);
        this.viewgrados.btnCancelar.addActionListener(this);
        this.viewgrados.btnBuscar.addActionListener(this);
        
        this.viewgrados.btnGenerarReporte.addActionListener(this);

        viewgrados.txtId.setEnabled(false);
        viewgrados.btnGuardar.setEnabled(false);
        viewgrados.btnActualizar.setEnabled(false);
        viewgrados.btnCancelar.setEnabled(false);

        listar(viewgrados.tablaResumen); //para listar apenas ejecutemos el programa
    }

    public void iniciar() {
        viewgrados.setTitle("Gestion de Grados");
        viewgrados.setVisible(true);
    }

    public void listar(JTable tabla) {
        this.modeloTabla = (DefaultTableModel) tabla.getModel(); //para que se vea la tabla al ejecutar
        List<Grado> lista = gradoDao.listar(); //para que liste en la tabla
        Object[] objeto = new Object[3]; //[3]ponemos la cantidad de columnas

        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getGrado();

            objeto[2] = lista.get(i).getSeccion();
            modeloTabla.addRow(objeto); //agreamos todo al modelo
        }
        //Para que todo se muestre en el JTable
        viewgrados.tablaResumen.setModel(modeloTabla);
    }

    public void listarGrados(JComboBox combo) {
        combo.removeAllItems();
        List<Grado> list = gradoDao.listar();
        for (Grado grado1 : list) {
            combo.addItem(grado1.getId());
        }
    }

    public void guardar() {
        if (viewgrados.txtGrado.getText().equals("") || viewgrados.txtSeccion.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Hay campos vacíos, debe llenar todos los campos");

        } else {
            /*if(validacion.existeDocente(viewCursos.txtPrimerNombre.getText(), viewDocentes.txtSegundoNombre.getText(), 
                                        viewDocentes.txtApellidoPaterno.getText(), viewDocentes.txtApellidoMaterno.getText(),
                                        viewDocentes.txtNumeroDocumento.getText())== 0){*/
            // Enviamos los datos al modelo para insertar a la BD.
            String gradO = viewgrados.txtGrado.getText();
            String seccion = (viewgrados.txtSeccion.getText());

            grado.setGrado(gradO);

            grado.setSeccion(seccion);

            if (gradoDao.insertar(grado)) {
                JOptionPane.showMessageDialog(null, "Grado guardado correctamente");
                limpiarCasilleros(); // Para que limpie los campos en caso se haya registrado
                limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
                listar(viewgrados.tablaResumen);

                viewgrados.jtpGestionGrados.setSelectedIndex(0);
                viewgrados.btnGuardar.setEnabled(false);
                viewgrados.btnNuevo.setEnabled(true);
                viewgrados.btnEditar.setEnabled(true);
                viewgrados.btnEliminar.setEnabled(true);
                viewgrados.btnActualizar.setEnabled(false);
                viewgrados.txtBuscar.setEnabled(true);
                viewgrados.btnBuscar.setEnabled(true);
                viewgrados.btnCancelar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar Grado");
            }
            /*} else {
                JOptionPane.showMessageDialog(null, "Este docente ya existe");
            }*/
        }
    }

    public void llenarCasillerosEditar() {
        int fila = viewgrados.tablaResumen.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(viewgrados, "Debe seleccionar una fila");
            viewgrados.jtpGestionGrados.setSelectedIndex(0);
        } else {
            int idGrado = Integer.parseInt((String) viewgrados.tablaResumen.getValueAt(fila, 0).toString());
            String nombreGrado = (String) viewgrados.tablaResumen.getValueAt(fila, 1);
            String nomSeccion = (String) viewgrados.tablaResumen.getValueAt(fila, 2).toString();

            viewgrados.txtId.setText("" + idGrado);
            viewgrados.txtGrado.setText("" + nombreGrado);
            viewgrados.txtSeccion.setText("" + nomSeccion);

            viewgrados.jtpGestionGrados.setSelectedIndex(1);

            viewgrados.btnGuardar.setEnabled(false);
            viewgrados.btnNuevo.setEnabled(false);
            viewgrados.btnEditar.setEnabled(false);
            viewgrados.btnEliminar.setEnabled(false);
            viewgrados.btnActualizar.setEnabled(true);
            viewgrados.btnCancelar.setEnabled(true);
            viewgrados.txtBuscar.setEnabled(false);
            viewgrados.btnBuscar.setEnabled(false);
        }
    }

    public void actualizar() {
        int idGrado = Integer.parseInt(viewgrados.txtId.getText());
        String nombreGrado = viewgrados.txtGrado.getText();
        String nomSeccion = viewgrados.txtSeccion.getText();

        grado.setId(idGrado);
        grado.setGrado(nombreGrado);
        grado.setSeccion(nomSeccion);

        int respuesta = gradoDao.modificar(grado);

        if (respuesta == 1) {
            JOptionPane.showMessageDialog(viewgrados, "Grado actualizado correctamente");
            limpiarCasilleros(); //limpiar casilleros textField
            limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
            listar(viewgrados.tablaResumen);
            viewgrados.jtpGestionGrados.setSelectedIndex(0);
            viewgrados.btnGuardar.setEnabled(false);
            viewgrados.btnNuevo.setEnabled(true);
            viewgrados.btnEditar.setEnabled(true);
            viewgrados.btnEliminar.setEnabled(true);
            viewgrados.btnActualizar.setEnabled(false);
            viewgrados.btnCancelar.setEnabled(false);
            viewgrados.txtBuscar.setEnabled(true);
            viewgrados.btnBuscar.setEnabled(true);
            viewgrados.txtBuscar.setText("");

        } else {
            JOptionPane.showMessageDialog(viewgrados, "Error al actualizar Grado");
        }
    }

    public void eliminar() {
        int fila = viewgrados.tablaResumen.getSelectedRow();
        viewgrados.jtpGestionGrados.setSelectedIndex(0);

        if (fila == -1) {
            JOptionPane.showMessageDialog(viewgrados, "Debe seleccionar un Grado");
        } else {
            int id = Integer.parseInt((String) viewgrados.tablaResumen.getValueAt(fila, 0).toString());
            gradoDao.Eliminar(id);
            JOptionPane.showMessageDialog(viewgrados, "Grado eliminado correctamente");
            limpiarCasilleros(); //limpiar casilleros textField
            limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
            listar(viewgrados.tablaResumen);
            viewgrados.txtBuscar.setEnabled(true);
            viewgrados.btnBuscar.setEnabled(true);
            viewgrados.txtBuscar.setText("");
        }
    }

    public void buscar(JTable tabla) {
        this.modeloTabla = (DefaultTableModel) tabla.getModel();
        String nombregrado = viewgrados.txtBuscar.getText();
        if (nombregrado.matches("^[a-zA-Z ]+$")) {
            // Solo contiene caracteres, puedes realizar la búsqueda
            List<Grado> grados = gradoDao.buscar(nombregrado);

            if (grados == null || grados.isEmpty()) {
                JOptionPane.showMessageDialog(viewgrados, "No se encontró ningún grado con ese nombre.");
                viewgrados.btnCancelar.setEnabled(true);
            } else {
                for (Grado grado : grados) {
                    Object[] objeto = new Object[3];

                    objeto[0] = grado.getId();
                    objeto[1] = grado.getGrado();
                    objeto[2] = grado.getSeccion();
                    modeloTabla.addRow(objeto);
                }
                viewgrados.tablaResumen.setModel(modeloTabla);
            }
        } else {
            // Muestra un mensaje de error o toma la acción apropiada si se ingresan números
            JOptionPane.showMessageDialog(null, "Por favor, ingrese solo caracteres (letras).");
            viewgrados.btnCancelar.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewgrados.btnNuevo) {
            limpiarCasilleros();
            viewgrados.jtpGestionGrados.setSelectedIndex(1);
            viewgrados.btnGuardar.setEnabled(true);
            viewgrados.btnNuevo.setEnabled(false);
            viewgrados.btnEditar.setEnabled(false);
            viewgrados.btnEliminar.setEnabled(false);
            viewgrados.btnActualizar.setEnabled(false);
            viewgrados.txtBuscar.setEnabled(false);
            viewgrados.btnBuscar.setEnabled(false);
            viewgrados.btnCancelar.setEnabled(true);
            viewgrados.txtBuscar.setText("");
        }
        if (e.getSource() == viewgrados.btnGuardar) {
            guardar();
        }
        //Poner los datos que se selecciona en la tabla en los casilleros
        if (e.getSource() == viewgrados.btnEditar) {
            llenarCasillerosEditar();
        }
        if (e.getSource() == viewgrados.btnActualizar) {
            actualizar();
        }
        if (e.getSource() == viewgrados.btnEliminar) {
            eliminar();
        }
        if (e.getSource() == viewgrados.btnCancelar) {
            limpiarTabla();
            limpiarCasilleros();
            listar(viewgrados.tablaResumen);
            viewgrados.txtBuscar.setText("");
            viewgrados.jtpGestionGrados.setSelectedIndex(0);
            viewgrados.btnCancelar.setEnabled(false);
            viewgrados.btnGuardar.setEnabled(false);
            viewgrados.btnNuevo.setEnabled(true);
            viewgrados.btnEditar.setEnabled(true);
            viewgrados.btnEliminar.setEnabled(true);
            viewgrados.btnActualizar.setEnabled(false);
            viewgrados.txtBuscar.setEnabled(true);
            viewgrados.btnBuscar.setEnabled(true);
        }
        if (e.getSource() == viewgrados.btnBuscar) {
            limpiarTabla();
            buscar(viewgrados.tablaResumen);
        }
        /*if(e.getSource() == viewDocentes.btnSalir){
            viewDocentes.dispose();
        }*/
        if(e.getSource() == viewgrados.btnGenerarReporte){
            gradoDao.generarReporte();
        }
    }

    public void limpiarCasilleros() {
        viewgrados.txtId.setText("");
        viewgrados.txtGrado.setText("");
        viewgrados.txtSeccion.setText("");

    }

    public void limpiarTabla() {
        for (int i = 0; i < viewgrados.tablaResumen.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }
    

}
