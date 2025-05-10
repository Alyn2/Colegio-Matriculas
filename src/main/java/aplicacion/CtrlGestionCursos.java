package aplicacion;

import dominio.entidades.Curso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dominio.entidades.Validacion;
import persistencia.CursoDAO;
import presentacion.FrmGestionCursos;

public class CtrlGestionCursos implements ActionListener {

    Curso curso = new Curso(); //modelo
    CursoDAO cursoDao = new CursoDAO(); //modeValidacionUsuarioidacion valValidacionUsuarionew Validacion();
    FrmGestionCursos viewCursos = new FrmGestionCursos(); //vista
    Validacion validacion = new Validacion();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public CtrlGestionCursos() {
    }

    public CtrlGestionCursos(FrmGestionCursos view) {
        this.viewCursos = view;
        //BOTONES
        this.viewCursos.btnNuevo.addActionListener(this);
        this.viewCursos.btnGuardar.addActionListener(this);
        this.viewCursos.btnEditar.addActionListener(this);
        this.viewCursos.btnActualizar.addActionListener(this);
        this.viewCursos.btnEliminar.addActionListener(this);
        this.viewCursos.btnCancelar.addActionListener(this);
        this.viewCursos.btnBuscar.addActionListener(this);

        viewCursos.txtId.setEnabled(false);
        viewCursos.btnGuardar.setEnabled(false);
        viewCursos.btnActualizar.setEnabled(false);
        viewCursos.btnCancelar.setEnabled(false);

        listar(viewCursos.tablaResumen); //para listar apenas ejecutemos el programa
    }

    public void iniciar() {
        viewCursos.setTitle("Gestion de Cursos");
        viewCursos.setVisible(true);
    }

    public void listar(JTable tabla) {
        this.modeloTabla = (DefaultTableModel) tabla.getModel(); //para que se vea la tabla al ejecutar
        List<Curso> lista = cursoDao.listar(); //para que liste en la tabla
        Object[] objeto = new Object[6]; //[5]ponemos la cantidad de columnas

        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNomcurso();
            objeto[2] = lista.get(i).getDocente().getId();
            objeto[3] = lista.get(i).getCreditos();
            objeto[4] = lista.get(i).getHoras();
            objeto[5] = lista.get(i).getGrado().getId();
            modeloTabla.addRow(objeto); //agreamos todo al modelo
        }
        //Para que todo se muestre en el JTable
        viewCursos.tablaResumen.setModel(modeloTabla);
    }

    public void guardar() {
        if (viewCursos.txtCurso.getText().equals("") || viewCursos.txtIdDocente.getText().equals("")
                || viewCursos.txtCreditos.getText().equals("") || viewCursos.txtHoras1.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Hay campos vacíos, debe llenar todos los campos");

        } else {
            /*if(validacion.existeDocente(viewCursos.txtPrimerNombre.getText(), viewDocentes.txtSegundoNombre.getText(), 
                                        viewDocentes.txtApellidoPaterno.getText(), viewDocentes.txtApellidoMaterno.getText(),
                                        viewDocentes.txtNumeroDocumento.getText())== 0){*/
            // Enviamos los datos al modelo para insertar a la BD.
            String nombreCurso = viewCursos.txtCurso.getText();
            int idDocente = Integer.parseInt(viewCursos.txtIdDocente.getText());
            int creditos = Integer.parseInt(viewCursos.txtCreditos.getText());
            int horas = Integer.parseInt(viewCursos.txtHoras1.getText());

            int idGrado = Integer.parseInt(viewCursos.cb_IDGrado.getSelectedItem().toString());
            //int idGrado = Integer.parseInt(viewCursos.txtidGrados.getText());
            System.out.println(idGrado);
            curso.setNomcurso(nombreCurso);
            curso.getDocente().setId(idDocente);
            curso.setCreditos(creditos);
            curso.setHoras(horas);
            curso.getGrado().setId(idGrado);

            if (cursoDao.insertar(curso)) {
                JOptionPane.showMessageDialog(null, "Curso guardado correctamente");
                limpiarCasilleros(); // Para que limpie los campos en caso se haya registrado
                limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
                listar(viewCursos.tablaResumen);

                viewCursos.jtpGestionCursos.setSelectedIndex(0);
                viewCursos.btnGuardar.setEnabled(false);
                viewCursos.btnNuevo.setEnabled(true);
                viewCursos.btnEditar.setEnabled(true);
                viewCursos.btnEliminar.setEnabled(true);
                viewCursos.btnActualizar.setEnabled(false);
                viewCursos.txtBuscar.setEnabled(true);
                viewCursos.btnBuscar.setEnabled(true);
                viewCursos.btnCancelar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar Curso");
            }
            /*} else {
                JOptionPane.showMessageDialog(null, "Este docente ya existe");
            }*/
        }
    }

    public void llenarCasillerosEditar() {
        int fila = viewCursos.tablaResumen.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(viewCursos, "Debe seleccionar una fila");
            viewCursos.jtpGestionCursos.setSelectedIndex(0);
        } else {
            int idCurso = Integer.parseInt((String) viewCursos.tablaResumen.getValueAt(fila, 0).toString());
            String nombreCurso = (String) viewCursos.tablaResumen.getValueAt(fila, 1);
            int idDocente = Integer.parseInt((String) viewCursos.tablaResumen.getValueAt(fila, 2).toString());
            int creditos = Integer.parseInt((String) viewCursos.tablaResumen.getValueAt(fila, 3).toString());
            int horas = Integer.parseInt((String) viewCursos.tablaResumen.getValueAt(fila, 4).toString());
            int idGrado = Integer.parseInt((String) viewCursos.tablaResumen.getValueAt(fila, 5).toString());

            viewCursos.txtId.setText("" + idCurso);
            viewCursos.txtCurso.setText("" + nombreCurso);
            viewCursos.txtIdDocente.setText("" + idDocente);
            viewCursos.txtCreditos.setText("" + creditos);
            viewCursos.txtHoras1.setText("" + horas);
            viewCursos.cb_IDGrado.setSelectedItem(idGrado);

            viewCursos.jtpGestionCursos.setSelectedIndex(1);

            viewCursos.btnGuardar.setEnabled(false);
            viewCursos.btnNuevo.setEnabled(false);
            viewCursos.btnEditar.setEnabled(false);
            viewCursos.btnEliminar.setEnabled(false);
            viewCursos.btnActualizar.setEnabled(true);
            viewCursos.btnCancelar.setEnabled(true);
            viewCursos.txtBuscar.setEnabled(false);
            viewCursos.btnBuscar.setEnabled(false);
        }
    }

    public void actualizar() {
        int id = Integer.parseInt(viewCursos.txtId.getText());
        String nombreCurso = viewCursos.txtCurso.getText();
        int idDocente = Integer.parseInt(viewCursos.txtIdDocente.getText());
        int creditos = Integer.parseInt(viewCursos.txtCreditos.getText());
        int horas = Integer.parseInt(viewCursos.txtHoras1.getText());
        int idGrado = Integer.parseInt(viewCursos.cb_IDGrado.getSelectedItem().toString());

        curso.setId(id);
        curso.setNomcurso(nombreCurso);
        curso.getDocente().setId(idDocente);
        curso.setCreditos(creditos);
        curso.setHoras(horas);
        curso.getGrado().setId(idGrado);
        int respuesta = cursoDao.modificar(curso);

        if (respuesta == 1) {
            JOptionPane.showMessageDialog(viewCursos, "Curso actualizado correctamente");
            limpiarCasilleros(); //limpiar casilleros textField
            limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
            listar(viewCursos.tablaResumen);
            viewCursos.jtpGestionCursos.setSelectedIndex(0);
            viewCursos.btnGuardar.setEnabled(false);
            viewCursos.btnNuevo.setEnabled(true);
            viewCursos.btnEditar.setEnabled(true);
            viewCursos.btnEliminar.setEnabled(true);
            viewCursos.btnActualizar.setEnabled(false);
            viewCursos.btnCancelar.setEnabled(false);
            viewCursos.txtBuscar.setEnabled(true);
            viewCursos.btnBuscar.setEnabled(true);
            viewCursos.txtBuscar.setText("");

        } else {
            JOptionPane.showMessageDialog(viewCursos, "Error al actualizar Curso");
        }
    }

    public void eliminar() {
        int fila = viewCursos.tablaResumen.getSelectedRow();
        viewCursos.jtpGestionCursos.setSelectedIndex(0);

        if (fila == -1) {
            JOptionPane.showMessageDialog(viewCursos, "Debe seleccionar un Curso");
        } else {
            int id = Integer.parseInt((String) viewCursos.tablaResumen.getValueAt(fila, 0).toString());
            cursoDao.Eliminar(id);
            JOptionPane.showMessageDialog(viewCursos, "Curso eliminado correctamente");
            limpiarCasilleros(); //limpiar casilleros textField
            limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
            listar(viewCursos.tablaResumen);
            viewCursos.txtBuscar.setEnabled(true);
            viewCursos.btnBuscar.setEnabled(true);
            viewCursos.txtBuscar.setText("");
        }
    }

public void buscar(JTable tabla) {
    this.modeloTabla = (DefaultTableModel) tabla.getModel();
    String nombreCurso = viewCursos.txtBuscar.getText();
    if (nombreCurso.matches("^[a-zA-Z ]+$")) {
        List<Curso> cursos = cursoDao.buscar(nombreCurso);

        if (cursos == null || cursos.isEmpty()) {
            JOptionPane.showMessageDialog(viewCursos, "No se encontró ningún curso con ese nombre.");
            viewCursos.btnCancelar.setEnabled(true);
        } else {
            for (Curso curso : cursos) {
                Object[] objeto = new Object[6];

                objeto[0] = curso.getId();
                objeto[1] = curso.getNomcurso();
                objeto[2] = curso.getDocente().getId();
                objeto[3] = curso.getCreditos();
                objeto[4] = curso.getHoras();
                objeto[5] = curso.getGrado().getId();
                modeloTabla.addRow(objeto);
            }
            viewCursos.tablaResumen.setModel(modeloTabla);
        }
    } else {
        // Muestra un mensaje de error o toma la acción apropiada si se ingresan números
        JOptionPane.showMessageDialog(null, "Por favor, ingrese solo caracteres (letras).");
        viewCursos.btnCancelar.setEnabled(true);
    }
}

    /*
    public void buscar(JTable tabla) {
        this.modeloTabla = (DefaultTableModel) tabla.getModel();
        String nombrecurso = viewCursos.txtBuscar.getText();
        if (nombrecurso.matches("^[a-zA-Z ]+$")) {
            curso = cursoDao.buscar(nombrecurso);

            if (curso == null || curso.getId() == 0) {
                JOptionPane.showMessageDialog(viewCursos, "No se encontró ningún curso con ese nombre.");
                viewCursos.btnCancelar.setEnabled(true);
            } else {
                Object[] objeto = new Object[6];
                objeto[0] = curso.getId();
                objeto[1] = curso.getCurso();
                objeto[2] = curso.getDocente().getId();
                objeto[3] = curso.getCreditos();
                objeto[4] = curso.getHoras();
                objeto[5] = curso.getGrado().getId();
                modeloTabla.addRow(objeto);

                viewCursos.tablaResumen.setModel(modeloTabla);
                viewCursos.btnCancelar.setEnabled(true);
            }
        } else {
             // Muestra un mensaje de error o toma la acción apropiada si se ingresan números
            JOptionPane.showMessageDialog(null, "Por favor, ingrese solo caracteres (letras).");
            viewCursos.btnCancelar.setEnabled(true);
        }
    }
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewCursos.btnNuevo) {
            limpiarCasilleros();
            viewCursos.jtpGestionCursos.setSelectedIndex(1);
            viewCursos.btnGuardar.setEnabled(true);
            viewCursos.btnNuevo.setEnabled(false);
            viewCursos.btnEditar.setEnabled(false);
            viewCursos.btnEliminar.setEnabled(false);
            viewCursos.btnActualizar.setEnabled(false);
            viewCursos.txtBuscar.setEnabled(false);
            viewCursos.btnBuscar.setEnabled(false);
            viewCursos.btnCancelar.setEnabled(true);
            viewCursos.txtBuscar.setText("");
        }
        if (e.getSource() == viewCursos.btnGuardar) {
            guardar();
        }
        //Poner los datos que se selecciona en la tabla en los casilleros4
        if (e.getSource() == viewCursos.btnEditar) {
            llenarCasillerosEditar();
        }
        if (e.getSource() == viewCursos.btnActualizar) {
            actualizar();
        }
        if (e.getSource() == viewCursos.btnEliminar) {
            eliminar();
        }
        if (e.getSource() == viewCursos.btnCancelar) {
            limpiarTabla();
            limpiarCasilleros();
            listar(viewCursos.tablaResumen);
            viewCursos.txtBuscar.setText("");
            viewCursos.jtpGestionCursos.setSelectedIndex(0);
            viewCursos.btnCancelar.setEnabled(false);
            viewCursos.btnGuardar.setEnabled(false);
            viewCursos.btnNuevo.setEnabled(true);
            viewCursos.btnEditar.setEnabled(true);
            viewCursos.btnEliminar.setEnabled(true);
            viewCursos.btnActualizar.setEnabled(false);
            viewCursos.txtBuscar.setEnabled(true);
            viewCursos.btnBuscar.setEnabled(true);
        }
        if (e.getSource() == viewCursos.btnBuscar) {
            limpiarTabla();
            buscar(viewCursos.tablaResumen);
        }
        /*if(e.getSource() == viewDocentes.btnSalir){
            viewDocentes.dispose();
        }*/
    }

    public void limpiarCasilleros() {
        viewCursos.txtId.setText("");
        viewCursos.txtCurso.setText("");
        viewCursos.txtIdDocente.setText("");
        viewCursos.txtCreditos.setText("");
        viewCursos.txtHoras1.setText("");
        viewCursos.cb_IDGrado.setSelectedIndex(0);
    }

    public void limpiarTabla() {
        for (int i = 0; i < viewCursos.tablaResumen.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }

}
