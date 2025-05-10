package aplicacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dominio.entidades.Alumno;
import dominio.entidades.Validacion;
import persistencia.AlumnoDAO;
import presentacion.FrmGestionAlumnos;

public class CtrlGestionAlumnos implements ActionListener {

    Alumno alumno = new Alumno(); //modelo
    AlumnoDAO alumnoDao = new AlumnoDAO(); //modelo
    FrmGestionAlumnos viewAlumnos = new FrmGestionAlumnos(); //vista
    Validacion validacion = new Validacion();
    DefaultTableModel modeloTabla = new DefaultTableModel();

    public CtrlGestionAlumnos(FrmGestionAlumnos view) {
        this.viewAlumnos = view;
        //BOTONES
        this.viewAlumnos.btnNuevo.addActionListener(this);
        this.viewAlumnos.btnGuardar.addActionListener(this);
        this.viewAlumnos.btnEditar.addActionListener(this);
        this.viewAlumnos.btnActualizar.addActionListener(this);
        this.viewAlumnos.btnEliminar.addActionListener(this);
        this.viewAlumnos.btnCancelar.addActionListener(this);
        this.viewAlumnos.btnBuscar.addActionListener(this);

        viewAlumnos.txtId.setEnabled(false);
        viewAlumnos.btnGuardar.setEnabled(false);
        viewAlumnos.btnActualizar.setEnabled(false);
        viewAlumnos.btnCancelar.setEnabled(false);

        listar(viewAlumnos.tablaResumen); //para listar apenas ejecutemos el programa
    }

    public void iniciar() {
        viewAlumnos.setTitle("Gestion de Alumnos");
        viewAlumnos.setVisible(true);
    }

    public void listar(JTable tabla) {
        this.modeloTabla = (DefaultTableModel) tabla.getModel(); //para que se vea la tabla al ejecutar
        List<Alumno> lista = alumnoDao.listar(); //para que liste en la tabla
        Object[] objeto = new Object[14]; //[14]ponemos la cantidad de columnas

        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getPrimerNombre();
            objeto[2] = lista.get(i).getSegundoNombre();
            objeto[3] = lista.get(i).getApellidoPaterno();
            objeto[4] = lista.get(i).getApellidoMaterno();
            objeto[5] = lista.get(i).getGenero();
            objeto[6] = lista.get(i).getDocumentoIdentidad().getTipoDocumento();
            objeto[7] = lista.get(i).getDocumentoIdentidad().getNumeroDocumento();
            objeto[8] = lista.get(i).getFechaNacimiento();
            objeto[9] = lista.get(i).getDepartamento();
            objeto[10] = lista.get(i).getProvincia();
            objeto[11] = lista.get(i).getDistrito();
            objeto[12] = lista.get(i).getDireccion();
            objeto[13] = lista.get(i).getApoderado().getId();
            modeloTabla.addRow(objeto); //agreamos todo al modelo
        }
        //Para que todo se muestre en el JTable
        viewAlumnos.tablaResumen.setModel(modeloTabla);
    }

    public void guardar() {
        // Validaciones
        if (viewAlumnos.txtPrimerNombre.getText().equals("") || viewAlumnos.txtSegundoNombre.getText().equals("")
                || viewAlumnos.cboTipoDocumento.getSelectedIndex() == 0 || viewAlumnos.cboGenero.getSelectedIndex() == 0
                || viewAlumnos.txtApoderado.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Hay campos vacíos, debe llenar todos los campos");

        } else {

            if (alumnoDao.existeAlumno(viewAlumnos.txtPrimerNombre.getText(), viewAlumnos.txtSegundoNombre.getText(),
                    viewAlumnos.txtApellidoPaterno.getText(), viewAlumnos.txtApellidoMaterno.getText(),
                     viewAlumnos.txtNumeroDocumento.getText()) == 0) {

                // Enviamos los datos al modelo para insertar a la BD.
                String primerNombre = viewAlumnos.txtPrimerNombre.getText();
                String segundoNombre = viewAlumnos.txtSegundoNombre.getText();
                String apellidoPaterno = viewAlumnos.txtApellidoPaterno.getText();
                String apellidoMaterno = viewAlumnos.txtApellidoMaterno.getText();
                String genero = viewAlumnos.cboGenero.getSelectedItem().toString();
                String tipoDocumento = viewAlumnos.cboTipoDocumento.getSelectedItem().toString();
                String numeroDocumento = viewAlumnos.txtNumeroDocumento.getText();

                Date date = viewAlumnos.jdcFechaNacimiento.getDate();
                long d = date.getTime();
                java.sql.Date fecha = new java.sql.Date(d);

                String departamento = viewAlumnos.cboDepartamento.getSelectedItem().toString();
                String provincia = viewAlumnos.cboProvincia.getSelectedItem().toString();
                String distrito = viewAlumnos.cboDistrito.getSelectedItem().toString();
                String direccion = viewAlumnos.txtDireccion.getText();
                int id_apoderado = Integer.parseInt(viewAlumnos.txtApoderado.getText());

                alumno.setPrimerNombre(primerNombre);
                alumno.setSegundoNombre(segundoNombre);
                alumno.setApellidoPaterno(apellidoPaterno);
                alumno.setApellidoMaterno(apellidoMaterno);
                alumno.setGenero(genero);
                alumno.getDocumentoIdentidad().setTipoDocumento(tipoDocumento);
                alumno.getDocumentoIdentidad().setNumeroDocumento(numeroDocumento);
                alumno.setFechaNacimiento(fecha);
                alumno.setDepartamento(departamento);
                alumno.setProvincia(provincia);
                alumno.setDistrito(distrito);
                alumno.setDireccion(direccion);
                alumno.getApoderado().setId(id_apoderado);

                if (alumnoDao.insertar(alumno)) {
                    JOptionPane.showMessageDialog(null, "Alumno guardado correctamente");
                    limpiarCasilleros(); // Para que limpie los campos en caso se haya registrado
                    limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
                    listar(viewAlumnos.tablaResumen);

                    viewAlumnos.jtpGestionAlumnos.setSelectedIndex(0);
                    viewAlumnos.btnGuardar.setEnabled(false);
                    viewAlumnos.btnNuevo.setEnabled(true);
                    viewAlumnos.btnEditar.setEnabled(true);
                    viewAlumnos.btnEliminar.setEnabled(true);
                    viewAlumnos.btnActualizar.setEnabled(false);
                    viewAlumnos.btnCancelar.setEnabled(false);
                    viewAlumnos.txtBuscar.setEnabled(true);
                    viewAlumnos.btnBuscar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar Docente");
                }
            } else {
                JOptionPane.showMessageDialog(null, "El correo electrónico no es válido");
            }
        }
    }

    public void llenarCasillerosEditar() {
        int fila = viewAlumnos.tablaResumen.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(viewAlumnos, "Debe seleccionar una fila");
            viewAlumnos.jtpGestionAlumnos.setSelectedIndex(0);
        } else {
            int id = Integer.parseInt((String) viewAlumnos.tablaResumen.getValueAt(fila, 0).toString());
            String nom1 = (String) viewAlumnos.tablaResumen.getValueAt(fila, 1);
            String nom2 = (String) viewAlumnos.tablaResumen.getValueAt(fila, 2);
            String ape1 = (String) viewAlumnos.tablaResumen.getValueAt(fila, 3);
            String ape2 = (String) viewAlumnos.tablaResumen.getValueAt(fila, 4);
            String gen = (String) viewAlumnos.tablaResumen.getValueAt(fila, 5);
            String tipoDo = (String) viewAlumnos.tablaResumen.getValueAt(fila, 6);
            String numDo = (String) viewAlumnos.tablaResumen.getValueAt(fila, 7);

            // Obtener la fecha directamente
            java.sql.Date fechaCasillero = (java.sql.Date) viewAlumnos.tablaResumen.getValueAt(fila, 8);

            String depa = (String) viewAlumnos.tablaResumen.getValueAt(fila, 9);
            String prov = (String) viewAlumnos.tablaResumen.getValueAt(fila, 10);
            String dis = (String) viewAlumnos.tablaResumen.getValueAt(fila, 11);
            String dire = (String) viewAlumnos.tablaResumen.getValueAt(fila, 12);
            int ipApo = (int) viewAlumnos.tablaResumen.getValueAt(fila, 13);

            viewAlumnos.txtId.setText("" + id);
            viewAlumnos.txtPrimerNombre.setText(nom1);
            viewAlumnos.txtSegundoNombre.setText(nom2);
            viewAlumnos.txtApellidoPaterno.setText(ape1);
            viewAlumnos.txtApellidoMaterno.setText(ape2);
            viewAlumnos.cboGenero.setSelectedItem(gen);
            viewAlumnos.cboTipoDocumento.setSelectedItem(tipoDo);
            viewAlumnos.txtNumeroDocumento.setText(numDo);

            // Asignar la fecha directamente al componente de fecha
            viewAlumnos.jdcFechaNacimiento.setDate(fechaCasillero);

            viewAlumnos.cboDepartamento.setSelectedItem(depa);
            viewAlumnos.cboProvincia.setSelectedItem(prov);
            viewAlumnos.cboDistrito.setSelectedItem(dis);
            viewAlumnos.txtDireccion.setText(dire);
            viewAlumnos.txtApoderado.setText("" + ipApo);

            viewAlumnos.jtpGestionAlumnos.setSelectedIndex(1);

            viewAlumnos.btnGuardar.setEnabled(false);
            viewAlumnos.btnNuevo.setEnabled(false);
            viewAlumnos.btnEditar.setEnabled(false);
            viewAlumnos.btnEliminar.setEnabled(false);
            viewAlumnos.btnActualizar.setEnabled(true);
            viewAlumnos.btnCancelar.setEnabled(true);
            viewAlumnos.txtBuscar.setEnabled(false);
            viewAlumnos.btnBuscar.setEnabled(false);
        }
    }

    public void actualizar() {
        int id = Integer.parseInt(viewAlumnos.txtId.getText());
        String primerNombre = viewAlumnos.txtPrimerNombre.getText();
        String segundoNombre = viewAlumnos.txtSegundoNombre.getText();
        String apellidoPaterno = viewAlumnos.txtApellidoPaterno.getText();
        String apellidoMaterno = viewAlumnos.txtApellidoMaterno.getText();
        String genero = viewAlumnos.cboGenero.getSelectedItem().toString();
        String tipoDocumento = viewAlumnos.cboTipoDocumento.getSelectedItem().toString();
        String numeroDocumento = viewAlumnos.txtNumeroDocumento.getText();

        Date date = viewAlumnos.jdcFechaNacimiento.getDate();
        long d = date.getTime();
        java.sql.Date fecha = new java.sql.Date(d);

        String departamento = viewAlumnos.cboDepartamento.getSelectedItem().toString();
        String provincia = viewAlumnos.cboProvincia.getSelectedItem().toString();
        String distrito = viewAlumnos.cboDistrito.getSelectedItem().toString();
        String direccion = viewAlumnos.txtDireccion.getText();
        int id_apoderado = Integer.parseInt(viewAlumnos.txtApoderado.getText());

        alumno.setId(id);
        alumno.setPrimerNombre(primerNombre);
        alumno.setSegundoNombre(segundoNombre);
        alumno.setApellidoPaterno(apellidoPaterno);
        alumno.setApellidoMaterno(apellidoMaterno);
        alumno.setGenero(genero);
        alumno.getDocumentoIdentidad().setTipoDocumento(tipoDocumento);
        alumno.getDocumentoIdentidad().setNumeroDocumento(numeroDocumento);
        alumno.setFechaNacimiento(fecha);
        alumno.setDepartamento(departamento);
        alumno.setProvincia(provincia);
        alumno.setDistrito(distrito);
        alumno.setDireccion(direccion);
        alumno.getApoderado().setId(id_apoderado);

        int respuesta = alumnoDao.modificar(alumno);

        if (respuesta == 1) {
            JOptionPane.showMessageDialog(viewAlumnos, "Alumno actualizado correctamente");
            limpiarCasilleros(); //limpiar casilleros textField
            limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
            listar(viewAlumnos.tablaResumen);

            viewAlumnos.jtpGestionAlumnos.setSelectedIndex(0);
            viewAlumnos.btnGuardar.setEnabled(false);
            viewAlumnos.btnNuevo.setEnabled(true);
            viewAlumnos.btnEditar.setEnabled(true);
            viewAlumnos.btnEliminar.setEnabled(true);
            viewAlumnos.btnActualizar.setEnabled(false);
            viewAlumnos.btnCancelar.setEnabled(false);
            viewAlumnos.txtBuscar.setEnabled(true);
            viewAlumnos.btnBuscar.setEnabled(true);
            viewAlumnos.txtBuscar.setText("");

        } else {
            JOptionPane.showMessageDialog(viewAlumnos, "Error al actualizar Alumno");
        }
    }

    public void eliminar() {
        int fila = viewAlumnos.tablaResumen.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(viewAlumnos, "Debe seleccionar un docente");
            viewAlumnos.jtpGestionAlumnos.setSelectedIndex(0);
        } else {
            int id = Integer.parseInt((String) viewAlumnos.tablaResumen.getValueAt(fila, 0).toString());
            alumnoDao.Eliminar(id);
            JOptionPane.showMessageDialog(viewAlumnos, "Alumno eliminado correctamente");
            limpiarCasilleros(); //limpiar casilleros textField
            limpiarTabla(); //Ambos son para que se limpie la tabla y se actualice
            listar(viewAlumnos.tablaResumen);
            viewAlumnos.txtBuscar.setEnabled(true);
            viewAlumnos.btnBuscar.setEnabled(true);
            viewAlumnos.txtBuscar.setText("");
        }
    }

    public void buscar(JTable tabla) {
        this.modeloTabla = (DefaultTableModel) tabla.getModel();
        String apeBuscar = viewAlumnos.txtBuscar.getText();
        if (apeBuscar.matches("^[a-zA-Z ]+$")) {
            // Solo contiene caracteres, puedes realizar la búsqueda
            List<Alumno> alumnos = alumnoDao.buscar(apeBuscar);

            if (alumnos == null || alumnos.isEmpty()) {
                JOptionPane.showMessageDialog(viewAlumnos, "No se encontró ningún alumno con ese apellido.");
                viewAlumnos.btnCancelar.setEnabled(true);
            } else {
                for (Alumno alumno : alumnos) {
                    Object[] objeto = new Object[14];

                    objeto[0] = alumno.getId();
                    objeto[1] = alumno.getPrimerNombre();
                    objeto[2] = alumno.getSegundoNombre();
                    objeto[3] = alumno.getApellidoPaterno();
                    objeto[4] = alumno.getApellidoMaterno();
                    objeto[5] = alumno.getGenero();
                    objeto[6] = alumno.getDocumentoIdentidad().getTipoDocumento();
                    objeto[7] = alumno.getDocumentoIdentidad().getNumeroDocumento();
                    objeto[8] = alumno.getFechaNacimiento();
                    objeto[9] = alumno.getDepartamento();
                    objeto[10] = alumno.getProvincia();
                    objeto[11] = alumno.getDistrito();
                    objeto[12] = alumno.getDireccion();
                    objeto[13] = alumno.getApoderado().getId();

                    modeloTabla.addRow(objeto);
                }
                viewAlumnos.tablaResumen.setModel(modeloTabla);
                viewAlumnos.btnCancelar.setEnabled(true);
            }
        } else {
            // Muestra un mensaje de error o toma la acción apropiada si se ingresan números
            JOptionPane.showMessageDialog(null, "Por favor, ingrese solo caracteres (letras).");
            viewAlumnos.btnCancelar.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewAlumnos.btnNuevo) {
            limpiarCasilleros();
            viewAlumnos.jtpGestionAlumnos.setSelectedIndex(1);

            viewAlumnos.btnGuardar.setEnabled(true);
            viewAlumnos.btnNuevo.setEnabled(false);
            viewAlumnos.btnEditar.setEnabled(false);
            viewAlumnos.btnEliminar.setEnabled(false);
            viewAlumnos.btnActualizar.setEnabled(false);
            viewAlumnos.txtBuscar.setEnabled(false);
            viewAlumnos.btnBuscar.setEnabled(false);
            viewAlumnos.btnCancelar.setEnabled(true);
            viewAlumnos.txtBuscar.setText("");
        }
        if (e.getSource() == viewAlumnos.btnGuardar) {
            guardar();
        }
        //Poner los datos que se selecciona en la tabla en los casilleros
        if (e.getSource() == viewAlumnos.btnEditar) {
            llenarCasillerosEditar();
        }
        if (e.getSource() == viewAlumnos.btnActualizar) {
            actualizar();
        }
        if (e.getSource() == viewAlumnos.btnEliminar) {
            eliminar();
        }
        if (e.getSource() == viewAlumnos.btnCancelar) {
            limpiarTabla();
            limpiarCasilleros();
            listar(viewAlumnos.tablaResumen);
            viewAlumnos.txtBuscar.setText("");
            viewAlumnos.jtpGestionAlumnos.setSelectedIndex(0);
            viewAlumnos.btnCancelar.setEnabled(false);
            viewAlumnos.btnGuardar.setEnabled(false);
            viewAlumnos.btnNuevo.setEnabled(true);
            viewAlumnos.btnEditar.setEnabled(true);
            viewAlumnos.btnEliminar.setEnabled(true);
            viewAlumnos.btnActualizar.setEnabled(false);
            viewAlumnos.txtBuscar.setEnabled(true);
            viewAlumnos.btnBuscar.setEnabled(true);
        }
        if (e.getSource() == viewAlumnos.btnBuscar) {
            limpiarTabla();
            buscar(viewAlumnos.tablaResumen);
        }
        /*if(e.getSource() == viewDocentes.btnSalir){
            viewDocentes.dispose();
        }*/
    }

    public void limpiarCasilleros() {
        viewAlumnos.txtId.setText("");
        viewAlumnos.txtPrimerNombre.setText("");
        viewAlumnos.txtSegundoNombre.setText("");
        viewAlumnos.txtApellidoPaterno.setText("");
        viewAlumnos.txtApellidoMaterno.setText("");
        viewAlumnos.cboGenero.setSelectedIndex(0);
        viewAlumnos.cboTipoDocumento.setSelectedIndex(0);
        viewAlumnos.txtNumeroDocumento.setText("");
        viewAlumnos.jdcFechaNacimiento.setDate(null);

        // Verificamos si los JComboBox de Departamento, Provincia y Distrito tienen elementos antes de seleccionar un índice
        if (viewAlumnos.cboDepartamento.getItemCount() > 0) {
            viewAlumnos.cboDepartamento.setSelectedIndex(0);
        }

        if (viewAlumnos.cboProvincia.getItemCount() > 0) {
            viewAlumnos.cboProvincia.setSelectedIndex(0);
        }

        if (viewAlumnos.cboDistrito.getItemCount() > 0) {
            viewAlumnos.cboDistrito.setSelectedIndex(0);
        }

        viewAlumnos.txtDireccion.setText("");
        viewAlumnos.txtApoderado.setText("");
    }

    public void limpiarTabla() {
        for (int i = 0; i < viewAlumnos.tablaResumen.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i = i - 1;
        }
    }

}
